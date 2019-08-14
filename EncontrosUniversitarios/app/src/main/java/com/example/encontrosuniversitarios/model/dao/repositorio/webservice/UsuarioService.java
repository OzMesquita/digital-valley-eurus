package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
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

//    @GET("usuarios")
//    Call<Boolean> autenticarUsuario(@Path("id") int idUsuario, @Body Usuario usuario);

    @POST("auth")
    Call<ValidacaoLogin> autenticarUsuario(String matricula, String senha);
}
