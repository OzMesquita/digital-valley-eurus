package com.example.encontrosuniversitarios.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.view.fragment.EstadoAtividadeAtualizavelInterface;

public class AtividadeDadosViewModel extends ViewModel {
    private MutableLiveData<Atividade> atividadeMutableLiveData;
    private AtividadeRepositorio atividadeRepositorio;
    private Atividade atividade;

    public AtividadeDadosViewModel(){
        atividadeRepositorio = AtividadeRepositorio.getInstance();
        atividadeMutableLiveData = new MutableLiveData<>();
    }

    public void init(Atividade atividade){
        this.atividade = atividade;
        this.atividadeMutableLiveData.setValue(atividade);
    }

    public void alterarEstadoAtividade(EstadoAtividadeAtualizavelInterface anInterface){
        if(!atividade.atividadeIniciada() && !atividade.atividadeFinalizada()){
            iniciarAtividade();
            anInterface.atualizarAtividadeIniciada();
        }else if(atividade.atividadeIniciada()){
            finalizarAtividade();
            anInterface.atualizarAtividadeNaoIniciada();
        }
    }

    private boolean iniciarAtividade(){
        boolean resultado = this.atividade.iniciar();
        return true;
    }

    private boolean finalizarAtividade(){
        return true;
    }

    public LiveData<Atividade> getAtividade() {
        return atividadeMutableLiveData;
    }
}
