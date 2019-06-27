package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;

import org.joda.time.DateTime;

public class AtividadeDadosViewModel extends ViewModel {
    private MutableLiveData<Atividade> atividadeMutableLiveData;
    private MutableLiveData<DateTime> horarioInicioAtividade;
    private MutableLiveData<DateTime> horarioFinalAtividade;
    private AtividadeRepositorio atividadeRepositorio;
    private Atividade atividade;

    public AtividadeDadosViewModel(){
        atividadeRepositorio = AtividadeRepositorio.getInstance();
        atividadeMutableLiveData = new MutableLiveData<>();
        horarioFinalAtividade = new MutableLiveData<>();
        horarioInicioAtividade = new MutableLiveData<>();
    }

    public void init(Atividade atividade){
        this.atividade = atividade;
        this.atividadeMutableLiveData.setValue(atividade);
    }

    public void alterarEstadoAtividade(){
        if(!atividade.atividadeIniciada() && !atividade.atividadeFinalizada()){
            iniciarAtividade();
        }else if(atividade.atividadeIniciada()){
            try {
                finalizarAtividade();
            } catch (AtividadeFinalizadaAntesDoHorarioIniciadoException e) {
            }
        }
    }

    private boolean iniciarAtividade(){
        boolean resultado = this.atividade.iniciar();
        if(resultado){
            atualizarAtividade();
            return true;
        }
        return false;
    }

    private boolean finalizarAtividade() throws AtividadeFinalizadaAntesDoHorarioIniciadoException{
        boolean resultado = this.atividade.finalizar();
        if(resultado){
            atualizarAtividade();
            return true;
        }
        return false;
    }

    public void atualizarAtividade(){
        atividadeRepositorio.atualizarAtividade(this.atividade, new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {

                atividadeMutableLiveData.setValue(atividade);
                horarioInicioAtividade.setValue(atividade.getHorarioInicio());
                horarioFinalAtividade.setValue(atividade.getHorarioFinal());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    public LiveData<Atividade> getAtividade() {
        return atividadeMutableLiveData;
    }

    public LiveData<DateTime> getHorarioInicio(){
        return horarioInicioAtividade;
    }

    public LiveData<DateTime> getHorarioFinal(){
        return horarioFinalAtividade;
    }
}
