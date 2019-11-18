package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/RealizarFrequenciaViewModel.java
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.DadosCheckIn;
import ufc.russas.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.QRCodeValidator;
import ufc.russas.encontrosuniversitarios.model.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;
=======
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.CheckInData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.UserAttendanceData;
import ufc.russas.encontrosuniversitarios.model.QRCodeValidator;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.CheckInCheckOutValidation;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityRepository;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserRepository;
import ufc.russas.encontrosuniversitarios.view.fragment.ActivitiesListener;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/RegisterAttendanceViewModel.java
import ufc.russas.encontrosuniversitarios.view.fragment.CheckInCheckOutListener;

import java.util.List;

public class RegisterAttendanceViewModel extends ViewModel {
    private ActivityRepository activityRepository;
    private UserRepository userRepository;
    private MutableLiveData<List<Activity>> atividadesFrequencia;
    private MutableLiveData<UserAttendanceData> usuarioFrequencia;

    public RegisterAttendanceViewModel() {
        this.activityRepository = ActivityRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
        this.usuarioFrequencia = new MutableLiveData<>();
        atividadesFrequencia = new MutableLiveData<>();
    }

    public void carregarAtividadesFrequencia(Context context, final ActivitiesListener listener){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int userId = preferences.getUserId();
        if(userId != -1){
            listener.onLoading();
            activityRepository.searchFrequentedActivities(new ResponseListener<List<Activity>>() {
                @Override
                public void onSuccess(List<Activity> response) {
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
        userRepository.searchUser(listener, matricula);
    }

    public void realizarCheckInCheckOut(final CheckInCheckOutListener listener, String qrcodeMessage,Context context) {
        int roomId = MySharedPreferences.getInstance(context).getRoomId();
        final QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean isQRCodeValid = qrCodeValidator.validateQRCode(qrcodeMessage);
        if(roomId != -1 && isQRCodeValid){
            userRepository.checkInCheckOut(new ResponseListener() {

                @Override
                public void onSuccess(Object response) {
                    CheckInCheckOutValidation checkInCheckOutValidation = (CheckInCheckOutValidation) response;
                    if(checkInCheckOutValidation.isCheckedInOnDifferentRoom()) {
                        listener.onCheckedInOnDifferentRoom(checkInCheckOutValidation.getMessage()+" : Usuário: "+qrCodeValidator.getNomeUsuario());
                    }else if(checkInCheckOutValidation.isSuccessful()){
                        listener.onSuccess(checkInCheckOutValidation.getMessage()+", Usuário: "+qrCodeValidator.getNomeUsuario());
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure("Ocorreu uma falha ao tentar realizar esta operação");
                }
            }, new CheckInData(qrCodeValidator.getIdUsuario(),roomId));
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
            userRepository.checkInCheckOut(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    CheckInCheckOutValidation checkInCheckOutValidation = (CheckInCheckOutValidation) response;
                    if(checkInCheckOutValidation.isCheckedInOnDifferentRoom()) {
                        listener.onCheckedInOnDifferentRoom(checkInCheckOutValidation.getMessage()+" : Usuário: "+usuarioFrequencia.getValue().getName());
                    }else if(checkInCheckOutValidation.isSuccessful()){
                        listener.onSuccess(checkInCheckOutValidation.getMessage()+", Usuário: "+usuarioFrequencia.getValue().getName());
                        usuarioFrequencia.setValue(null);
                    }
                }
                @Override
                public void onFailure(String message) {
                    listener.onFailure("Ocorreu uma falha ao tentar realizar esta operação");
                }
            }, new CheckInData(usuarioFrequencia.getValue().getId(),roomId));
        }else{
            listener.onFailure("Você não possui permissão para realizar esta operação");
        }
    }

    public LiveData<List<Activity>> getAtividadesFrequencia() {
        return atividadesFrequencia;
    }

    public LiveData<UserAttendanceData> getUsuarioFrequencia() {
        return usuarioFrequencia;
    }

    public void initDadosFrequencia(UserAttendanceData userAttendanceData) {
        this.usuarioFrequencia.setValue(userAttendanceData);
    }
}
