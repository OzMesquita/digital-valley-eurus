package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtividadeService {
    @GET("atividades")
    Call<List<Atividade>> getAtividades();

    @GET("hoje/atividades/")
    Call<List<Atividade>> getAtividadesDoDia();

    @PUT("atividades/{id}")
    Call<Boolean> atualizarAtividade(@Path("id") int idAtividade, @Body Atividade atividade);
}
