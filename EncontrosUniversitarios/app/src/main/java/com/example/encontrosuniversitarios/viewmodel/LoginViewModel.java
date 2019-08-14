package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
import com.example.encontrosuniversitarios.model.ValidacaoLogin;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import com.example.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioListener;
import com.example.encontrosuniversitarios.view.fragment.LoginListener;

public class LoginViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

//    public LoginViewModel(){
//        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
//    }

    public void logar(String matricula, String senha, final LoginListener listener ) {

        try {
            this.usuario = new Usuario(senha, matricula);

            this.usuarioRepositorio.validacaoLogin(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    ValidacaoLogin validacaoLogin = (ValidacaoLogin) response;
//                    if(validacaoLogin.isAlreadyTakenSenha()) listener.onAlreadyTakenSenha();
//                    if(validacaoLogin.isAlreadyTakenMatricula()) listener.onAlreadyTakenMatricula();
                    if(!validacaoLogin.isAlreadyTakenMatricula() && !validacaoLogin.isAlreadyTakenSenha()){
                        listener.onSuccess(validacaoLogin.getMessage());
                    }
                }
                @Override
                public void onFailure(String message) {
                    Log.i("ErrorResposta",message);
                }
            }, usuario);
        } catch (CampoVazioException e) {
            listener.onEmptyField(e.getMessage());
        } catch (SenhaInvalidaException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (MatriculaInvalidaException e) {
            listener.onInvalidMatricula(e.getMessage());
        }
    }
}

