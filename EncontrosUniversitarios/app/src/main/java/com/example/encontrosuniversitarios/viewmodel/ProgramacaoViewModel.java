package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeListResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgramacaoViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private MutableLiveData<List<Atividade>> atividadesMLD;

    public ProgramacaoViewModel(){
        this.atividadeRepositorio = new AtividadeRepositorio();
        atividadesMLD = new MutableLiveData<>();
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividadesMLD;
    }

    public void carregarAtividades(){
        atividadeRepositorio.buscar(new AtividadeListResponseListener() {
            @Override
            public void onSuccess(List<Atividade> atividades) {
                atividadesMLD.setValue(atividades);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}
