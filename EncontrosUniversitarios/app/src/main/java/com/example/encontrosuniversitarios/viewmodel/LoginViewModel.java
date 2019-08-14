package com.example.encontrosuniversitarios.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import com.example.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.example.encontrosuniversitarios.view.fragment.LoginListener;

public class LoginViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

//    public LoginViewModel(){
//        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
//    }

    public void logar(String matricula, String senha, final LoginListener listener) {

        try {
            this.usuario = new Usuario(matricula, senha);
           // this.usuarioRepositorio.cadastrarUsuario(new Lis);

        } catch (CampoVazioException e) {
            listener.onEmptyField(e.getMessage());
        } catch (SenhaInvalidaException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (MatriculaInvalidaException e) {
            listener.onInvalidMatricula(e.getMessage());
        }
    }
}

