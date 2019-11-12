package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosCheckIn;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.QRCodeValidator;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;
import ufc.russas.encontrosuniversitarios.view.fragment.CheckInCheckOutListener;

import java.util.List;

public class RealizarFrequenciaViewModel extends ViewModel {
    private AtividadeRepositorio atividadeRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private MutableLiveData<List<Atividade>> atividadesFrequencia;
    private MutableLiveData<DadosFrequenciaUsuario> usuarioFrequencia;

    public RealizarFrequenciaViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        this.usuarioFrequencia = new MutableLiveData<>();
        atividadesFrequencia = new MutableLiveData<>();
    }

    public void carregarAtividadesFrequencia(Context context, final AtividadesListener listener){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int userId = preferences.getUserId();
        if(userId != -1){
            listener.onLoading();
            atividadeRepositorio.buscarAtividadesFrequencia(new ResponseListener<List<Atividade>>() {
                @Override
                public void onSuccess(List<Atividade> response) {
                    listener.onDone();
                    atividadesFrequencia.setValue(response);
                }

                @Override
                public void onFailure(String message) {
                    listener.onDone();
                    Log.i("Falha",message);
                }
            },userId);
        }else{
            Toast.makeText(context,"Id invalido",Toast.LENGTH_LONG).show();
        }
    }

    public void buscarUsuarioPorMatricula(final ResponseListener listener, String matricula) {
        usuarioRepositorio.buscarUsuario(listener, matricula);
    }

    public void realizarCheckInCheckOut(final CheckInCheckOutListener listener, String qrcodeMessage,Context context) {
        int roomId = MySharedPreferences.getInstance(context).getRoomId();
        final QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean isQRCodeValid = qrCodeValidator.validateQRCode(qrcodeMessage);
        if(roomId != -1 && isQRCodeValid){
            usuarioRepositorio.checkInCheckOut(new ResponseListener() {

                @Override
                public void onSuccess(Object response) {
                    ValidacaoCheckInCheckOut validacaoCheckInCheckOut = (ValidacaoCheckInCheckOut) response;
                    if(validacaoCheckInCheckOut.isCheckedInOnDifferentRoom()) {
                        listener.onCheckedInOnDifferentRoom(validacaoCheckInCheckOut.getMessage()+" : Usuário: "+qrCodeValidator.getNomeUsuario());
                    }else if(validacaoCheckInCheckOut.isSuccessful()){
                        listener.onSuccess(validacaoCheckInCheckOut.getMessage()+", Usuário: "+qrCodeValidator.getNomeUsuario());
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure("Ocorreu uma falha ao tentar realizar esta operação");
                }
            }, new DadosCheckIn(qrCodeValidator.getIdUsuario(),roomId));
        }else{
            if(!isQRCodeValid){
                listener.onInvalidQRCode("O QRCode lido é inválido");
            }else{
                listener.onFailure("Ocorreu uma falha ao tentar realizar esta operação");
            }
        }
    }

    public void realizarCheckInCheckOut(final CheckInCheckOutListener listener, Context context) {
        int roomId = MySharedPreferences.getInstance(context).getRoomId();
        if(roomId != -1) {
            usuarioRepositorio.checkInCheckOut(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    ValidacaoCheckInCheckOut validacaoCheckInCheckOut = (ValidacaoCheckInCheckOut) response;
                    if(validacaoCheckInCheckOut.isCheckedInOnDifferentRoom()) {
                        listener.onCheckedInOnDifferentRoom(validacaoCheckInCheckOut.getMessage()+" : Usuário: "+usuarioFrequencia.getValue().getNome());
                    }else if(validacaoCheckInCheckOut.isSuccessful()){
                        listener.onSuccess(validacaoCheckInCheckOut.getMessage()+", Usuário: "+usuarioFrequencia.getValue().getNome());
                        usuarioFrequencia.setValue(null);
                    }
                }
                @Override
                public void onFailure(String message) {
                    listener.onFailure("Ocorreu uma falha ao tentar realizar esta operação");
                }
            }, new DadosCheckIn(usuarioFrequencia.getValue().getId(),roomId));
        }else{
            listener.onFailure("Você não possui permissão para realizar esta operação");
        }
    }

    public LiveData<List<Atividade>> getAtividadesFrequencia() {
        return atividadesFrequencia;
    }

    public LiveData<DadosFrequenciaUsuario> getUsuarioFrequencia() {
        return usuarioFrequencia;
    }

    public void initDadosFrequencia(DadosFrequenciaUsuario dadosFrequenciaUsuario) {
        this.usuarioFrequencia.setValue(dadosFrequenciaUsuario);
    }
}
