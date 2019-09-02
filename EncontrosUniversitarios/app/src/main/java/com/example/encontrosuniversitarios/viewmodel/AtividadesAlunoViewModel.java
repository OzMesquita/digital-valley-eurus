package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.ProgramacaoAtividades;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;

import java.util.List;

public class AtividadesAlunoViewModel extends ViewModel {
    private AtividadeRepositorio atividadeRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private MutableLiveData<List<Atividade>> atividades;

    public AtividadesAlunoViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        atividades = new MutableLiveData<>();
    }

    public void carregarAtividades(Context context){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        atividadeRepositorio.buscarAtividadesParticipadas(new ResponseListener<List<Atividade>>() {
            @Override
            public void onSuccess(List<Atividade> atividadesEvento) {
                atividades.setValue(atividadesEvento);
            }

            @Override
            public void onFailure(String message) {
                Log.i("AtvFailura:",message);
            }
        },preferences.getUserId());
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividades;
    }

}
