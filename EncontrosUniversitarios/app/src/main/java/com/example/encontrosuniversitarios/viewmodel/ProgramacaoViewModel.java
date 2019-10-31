package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.ProgramacaoAtividades;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.view.fragment.AtividadesListener;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgramacaoViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private ProgramacaoAtividades programacaoAtividades;
    private MutableLiveData<List<Atividade>> atividades;
    private MutableLiveData<List<DiaEvento>> atividadesDiasEvento;
    private MutableLiveData<List<List<Atividade>>> atividadesDoDia;

    public ProgramacaoViewModel(){
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        atividadesDiasEvento = new MutableLiveData<>();
        atividadesDoDia = new MutableLiveData<>();
        atividades = new MutableLiveData<>();
        programacaoAtividades = new ProgramacaoAtividades();
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividades;
    }

    public LiveData<List<DiaEvento>> getDiasEvento(){
        return atividadesDiasEvento;
    }

    public MutableLiveData<List<List<Atividade>>> getAtividadesDoDia() {
        return atividadesDoDia;
    }

    public void carregarAtividades(final AtividadesListener listener){
        listener.onLoading();
        atividadeRepositorio.buscar(new ResponseListener<List<Atividade>>() {
            @Override
            public void onSuccess(List<Atividade> atividadesEvento) {
                listener.onDone();
                atividades.setValue(atividadesEvento);
                atividadesDiasEvento.setValue(programacaoAtividades.agruparAtividadesEmDias(atividadesEvento));
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                Log.i("AtvFailura:",message);
            }
        });
    }

    public void carregarAtividadesDoDia(final AtividadesListener listener){
        listener.onLoading();
        atividadeRepositorio.buscarAtividadesDoDia(new ResponseListener<List<Atividade>>() {
            @Override
            public void onSuccess(List<Atividade> atividades) {
                listener.onDone();
                atividadesDoDia.setValue(programacaoAtividades.dividirAtividadesEmEstados(atividades));
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                Log.i("AtvFailura:",message);
            }
        });
    }

}