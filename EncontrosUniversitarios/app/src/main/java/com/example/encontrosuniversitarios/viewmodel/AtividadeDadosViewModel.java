package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
        horarioInicioAtividade.setValue(atividade.getHorarioInicio());
        horarioFinalAtividade.setValue(atividade.getHorarioFinal());
    }

    public void alterarHorarioAtividade(Context context) {
        if(!atividade.atividadeIniciada() && !atividade.atividadeFinalizada()){
            iniciarAtividade();
        }else if(atividade.atividadeIniciada()){
            finalizarAtividade(context);
        }
    }

    private void iniciarAtividade(){
        atividadeRepositorio.getMomento(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                DateTime momento = (DateTime) response;
                boolean resultado = atividade.iniciar(momento);
                if(resultado){
                    atualizarHorariosAtividade(true);
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void finalizarAtividade(final Context context){

        atividadeRepositorio.getMomento(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                try {
                    DateTime momento = (DateTime) response;
                    boolean resultado = atividade.finalizar(momento);
                    if(resultado){
                        atualizarHorariosAtividade(false);
                    }
                }catch (AtividadeFinalizadaAntesDoHorarioIniciadoException e){
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();;
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void atualizarHorariosAtividade(final boolean isHorarioInicio){
        atividadeRepositorio.atualizarAtividade(this.atividade, isHorarioInicio, new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                atividadeMutableLiveData.setValue(atividade);
                if(isHorarioInicio) {
                    horarioInicioAtividade.setValue(atividade.getHorarioInicio());
                }else {
                    horarioFinalAtividade.setValue(atividade.getHorarioFinal());
                }
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

//    private

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
