package com.example.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.DadosLogin;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoLogin;
import com.example.encontrosuniversitarios.model.Validador;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import com.example.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.example.encontrosuniversitarios.view.fragment.LoginListener;
import com.example.encontrosuniversitarios.view.fragment.LogoutListener;
import com.example.encontrosuniversitarios.view.fragment.RedefinicaoSenhaListener;

import okhttp3.internal.Util;

public class LoginViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    public LoginViewModel() {
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
    }

    public void realizarLogin(String email, String senha, final LoginListener listener) {
        try {
            this.usuario = new Usuario(email, senha);
            DadosLogin dadosLogin = new DadosLogin(this.usuario.getEmail(), this.usuario.getSenha());
            this.usuarioRepositorio.realizarLogin(new ResponseListener() {
                ValidacaoLogin validacao;

                @Override
                public void onSuccess(Object response) {
                    validacao = (ValidacaoLogin) response;
                    if (validacao.isUnregisteredEmail()) listener.onUnregisteredEmail();
                    if (validacao.isWrongPassword()) listener.onWrongPassword();
                    if ((!validacao.isWrongPassword() && !validacao.isUnregisteredEmail())
                            && validacao.isLoginSuccessful() && validacao.getUsuarioLogado() != null) {
                        usuario = validacao.getUsuarioLogado();
                        listener.onSuccess(usuario);
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure(message);
                }
            }, dadosLogin);
        } catch (CampoVazioException e) {
            listener.onEmptyField(e.getMessage());
        } catch (SenhaInvalidaException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (EmailInvalidoException e) {
            listener.onInvalidEmail(e.getMessage());
        }
    }

    public void realizarLogout(Context context, LogoutListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        boolean result = preferences.clearData();
        if (result) {
            listener.onSuccessfulLogout();
        } else {
            listener.onFailure();
        }
    }

    public void recuperacaoSenha(String email, final RedefinicaoSenhaListener listener){
        if (email.equals("") || !Validador.validarEmail(email)) {
            listener.onInvalidField();
        } else {
            this.usuarioRepositorio.recuperarSenha(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    listener.onSuccess();
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure(message);
                }
            }, email);
        }
    }
}


