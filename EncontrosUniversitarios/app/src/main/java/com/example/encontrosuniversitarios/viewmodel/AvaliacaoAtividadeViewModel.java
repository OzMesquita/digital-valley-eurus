package com.example.encontrosuniversitarios.viewmodel;

import android.location.Criteria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;

import java.util.List;

public class AvaliacaoAtividadeViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private MutableLiveData<List<CriterioAtividade>> criterios;

    public AvaliacaoAtividadeViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        criterios = new MutableLiveData<>();
    }

    public LiveData<List<CriterioAtividade>> getCriterios() {
        return criterios;
    }

    public void listarCriterios(){
        atividadeRepositorio.getCriterios(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                criterios.setValue((List<CriterioAtividade>) response);
            }

            @Override
            public void onFailure(String message) {
                criterios.setValue(null);
            }
        });
    }

}
