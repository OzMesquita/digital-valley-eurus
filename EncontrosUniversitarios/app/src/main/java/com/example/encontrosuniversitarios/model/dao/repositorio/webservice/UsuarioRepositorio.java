package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepositorio {

    private static UsuarioRepositorio usuarioRepositorio;
    private UsuarioService usuarioService;
    private UsuarioRepositorio(){
        usuarioService = WebServiceDatabase.getInstance().getUsuarioService();
    }

    public static UsuarioRepositorio getInstance() {
        if(usuarioRepositorio==null){
            usuarioRepositorio = new UsuarioRepositorio();
        }
        return usuarioRepositorio;
    }

    public void cadastrarUsuario(final ResponseListener listener, Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario)
                .enqueue(new Callback<ValidacaoCadastro>() {
                    @Override
                    public void onResponse(Call<ValidacaoCadastro> call, Response<ValidacaoCadastro> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ValidacaoCadastro> call, Throwable t) {
                        listener.onFailure(t.getMessage());
                    }
                });
    }

    public void validacaoLogin(final ResponseListener listener, Usuario usuario){

    }
}
