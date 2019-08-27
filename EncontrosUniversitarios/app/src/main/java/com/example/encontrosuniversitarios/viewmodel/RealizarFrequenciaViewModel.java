package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DadosCheckIn;
import com.example.encontrosuniversitarios.model.ValidacaoCheckInCheckOut;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.view.fragment.CheckInCheckOutListener;

import java.util.List;

public class RealizarFrequenciaViewModel extends ViewModel {
    private AtividadeRepositorio atividadeRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private MutableLiveData<List<Atividade>> atividadesFrequencia;

    public RealizarFrequenciaViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        atividadesFrequencia = new MutableLiveData<>();
    }

    public void carregarAtividadesFrequencia(Context context){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int userId = preferences.getUserId();
        if(userId != -1){
            atividadeRepositorio.buscarAtividadesFrequencia(new ResponseListener<List<Atividade>>() {
                @Override
                public void onSuccess(List<Atividade> response) {
                    atividadesFrequencia.setValue(response);
                }

                @Override
                public void onFailure(String message) {
                    Log.i("Falha",message);
                }
            },userId);
        }else{
            Toast.makeText(context,"Id invalido",Toast.LENGTH_LONG).show();
        }
    }

    public void realizarCheckInCheckOut(CheckInCheckOutListener listener, int idUsuario, int numeroSala) {
        usuarioRepositorio.checkInCheckOut(new ResponseListener() {
            ValidacaoCheckInCheckOut validacaoCheckInCheckOut;
            @Override
            public void onSuccess(Object response) {
                validacaoCheckInCheckOut = (ValidacaoCheckInCheckOut) response;

            }

            @Override
            public void onFailure(String message) {

            }
        }, new DadosCheckIn(idUsuario,numeroSala));
    }

    public LiveData<List<Atividade>> getAtividadesFrequencia() {
        return atividadesFrequencia;
    }
}
