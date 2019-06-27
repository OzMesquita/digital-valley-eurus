package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;

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
                listener.onFailure(t.getMessage());
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
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void atualizarAtividade(Atividade atividade, final ResponseListener listener){
        atividadeService.atualizarAtividade(atividade.getId(),atividade).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

}
