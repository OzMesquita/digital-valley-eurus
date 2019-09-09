package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DadosCheckIn;
import com.example.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import com.example.encontrosuniversitarios.model.DadosLogin;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
import com.example.encontrosuniversitarios.model.ValidacaoCheckInCheckOut;
import com.example.encontrosuniversitarios.model.ValidacaoLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {
    @POST("usuarios")
    Call<ValidacaoCadastro> cadastrarUsuario(@Body Usuario usuario);

    @POST("auth")
    Call<ValidacaoLogin> autenticarUsuario(@Body DadosLogin dados);

    @POST("/frequencia")
    Call<ValidacaoCheckInCheckOut> checkInCheckOut(@Body DadosCheckIn dadosCheckIn);

    @GET("usuario/{matricula}")
    Call<DadosFrequenciaUsuario> getUsuario(@Path("matricula") String matricula);
}
