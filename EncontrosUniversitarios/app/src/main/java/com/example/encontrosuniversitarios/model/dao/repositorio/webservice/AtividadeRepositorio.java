package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.util.Log;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.AvaliacaoAtividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.model.ResultadoAvaliacao;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtividadeRepositorio{

    private static AtividadeRepositorio atividadeRepositorio;
    private AtividadeService atividadeService;


    private AtividadeRepositorio(){
        atividadeService = WebServiceDatabase.getInstance().getAtividadeService();
    }

    public static AtividadeRepositorio getInstance(){
        if(atividadeRepositorio==null){
            atividadeRepositorio = new AtividadeRepositorio();
        }
        return atividadeRepositorio;
    }

    public void buscar(final ResponseListener listener) {
        atividadeService.getAtividades().enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void buscarAtividadesDoDia(final ResponseListener listener){
        atividadeService.getAtividadesDoDia().enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void buscarAtividadesFrequencia(final ResponseListener listener, int idCoordenador) {
        atividadeService.getAtividadesFrequencia(idCoordenador).enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void buscarAtividadesParticipadas(final ResponseListener listener, int idUsuario) {
        Log.i("Matheus",""+idUsuario);
        atividadeService.getAtividadesParticipadas(idUsuario).enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void atualizarAtividade(Atividade atividade, boolean isHorarioInicio, final ResponseListener listener){
        DateTime horario = isHorarioInicio ? atividade.getHorarioInicio() : atividade.getHorarioFinal();
        atividadeService.atualizarAtividade(atividade.getId(),new Inicio(isHorarioInicio,horario)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void getMomento(final ResponseListener listener){
        atividadeService.getMomento().enqueue(new Callback<DateTime>() {
            @Override
            public void onResponse(Call<DateTime> call, Response<DateTime> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DateTime> call, Throwable t) {
                listener.onFailure("Erro momento");
            }
        });
    }

    public  void getCriterios(final ResponseListener listener){
        atividadeService.getCriterios().enqueue(new Callback<List<CriterioAtividade>>() {
            @Override
            public void onResponse(Call<List<CriterioAtividade>> call, Response<List<CriterioAtividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<CriterioAtividade>> call, Throwable t) {
                listener.onFailure("Erro ao buscar critérios do banco");
            }
        });
    }

    public void avaliarAtividade(final ResponseListener listener, AvaliacaoAtividade avaliacaoAtividade){
        atividadeService.avaliarAtividade(avaliacaoAtividade).enqueue(new Callback<ResultadoAvaliacao>() {
            @Override
            public void onResponse(Call<ResultadoAvaliacao> call, Response<ResultadoAvaliacao> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResultadoAvaliacao> call, Throwable t) {
                listener.onFailure("Erro ao avaliar atividade");
            }
        });
    }

}
