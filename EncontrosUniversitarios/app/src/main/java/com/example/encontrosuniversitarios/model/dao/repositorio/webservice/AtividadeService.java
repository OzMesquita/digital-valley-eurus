package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AtividadeService {
    @GET("atividades")
    Call<List<Atividade>> getAtividades();

    @GET("atividades/hoje")
    Call<List<Atividade>> getAtividadesDoDia();
}
