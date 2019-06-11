package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeListResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgramacaoViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private MutableLiveData<List<Atividade>> atividadesMLD;
    private MutableLiveData<List<DiaEvento>> atividadesDiasEvento;

    public ProgramacaoViewModel(){
        this.atividadeRepositorio = new AtividadeRepositorio();
        atividadesMLD = new MutableLiveData<>();
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividadesMLD;
    }

    public LiveData<List<DiaEvento>> getDiasEvento(){
        return atividadesDiasEvento;
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

    public List<DiaEvento> getAtividadesDosDiasEvento(List<Atividade> atividades){
        List<DiaEvento> diasEvento = new ArrayList<>();

        return diasEvento;
    }
}