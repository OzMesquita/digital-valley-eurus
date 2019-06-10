package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.content.Context;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtividadeRepositorio{

    private AtividadeService atividadeService;


    public AtividadeRepositorio(){
        atividadeService = WebServiceDatabase.getInstance().getAtividadeService();
    }


    public void buscar(final AtividadeListResponseListener listener) {
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
}
