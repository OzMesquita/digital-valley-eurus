package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.ValidacaoCadastro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsuarioService {
    @GET("usuarios/cadastro/")
    Call<ValidacaoCadastro> verificarEmailMatriculaJaCadastradas(@Query("email") String email, @Query("matricula") String matricula);
}
