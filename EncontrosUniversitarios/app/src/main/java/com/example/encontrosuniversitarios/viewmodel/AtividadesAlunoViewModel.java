package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.view.fragment.AtividadesListener;

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

    public void carregarAtividades(Context context, final AtividadesListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        listener.onLoading();
        atividadeRepositorio.buscarAtividadesParticipadas(new ResponseListener<List<Atividade>>() {
            @Override
            public void onSuccess(List<Atividade> atividadesEvento) {
                listener.onDone();
                atividades.setValue(atividadesEvento);
            }

            @Override
            public void onFailure(String message) {
                Log.i("AtvFailura:", message);
                listener.onDone();
            }
        }, preferences.getUserId());
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividades;
    }

}
