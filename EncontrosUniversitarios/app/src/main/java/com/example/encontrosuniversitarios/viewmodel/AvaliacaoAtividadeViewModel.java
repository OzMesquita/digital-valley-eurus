package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.location.Criteria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.AvaliacaoAtividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.model.Nota;
import com.example.encontrosuniversitarios.model.ResultadoAvaliacao;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.view.fragment.AvaliacaoListener;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class AvaliacaoAtividadeViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private MutableLiveData<List<CriterioAtividade>> criterios;
    private Atividade atividade;

    public AvaliacaoAtividadeViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        criterios = new MutableLiveData<>();
    }

    public void init(Atividade atividade){
        this.atividade = atividade;
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

    public void avaliarAtividade(String comentarios, Context context, final AvaliacaoListener listener){
        List<Nota> notas = new ArrayList<>();
        for(CriterioAtividade c: criterios.getValue()){
            notas.add(new Nota(c.getId(),c.getNota()));
        }
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int idAvaliador = preferences.getUserId();
        atividadeRepositorio.avaliarAtividade(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                ResultadoAvaliacao resultadoAvaliacao = (ResultadoAvaliacao) response;
                if(resultadoAvaliacao.getAlreadyEvaluatedActivity()){
                    listener.onAlreadyEvaluatedActivity();
                }else if(!resultadoAvaliacao.getAlreadyEvaluatedActivity() && !resultadoAvaliacao.getError()){
                    listener.onSuccess();
                }else if(resultadoAvaliacao.getError()) {
                    listener.onError("Não foi possível avaliar esta atividade");
                }
            }

            @Override
            public void onFailure(String message) {
                listener.onError("Houve um erro ao tentar realizar esta operação, verifique sua internet");
            }
        },new AvaliacaoAtividade(atividade.getId(),idAvaliador,comentarios,notas));
    }

    public Atividade getAtividade() {
        return atividade;
    }
}
