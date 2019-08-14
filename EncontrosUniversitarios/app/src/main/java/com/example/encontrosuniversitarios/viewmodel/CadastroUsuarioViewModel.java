package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import com.example.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import com.example.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioListener;

public class CadastroUsuarioViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    public CadastroUsuarioViewModel(){
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
    }

    public void cadastrarUsuario(String nome, String matricula, String email, String senha, final CadastroUsuarioListener listener){
        try {
            this.usuario = new Usuario(nome,email,matricula,senha);
            this.usuario.setNivelAcesso(Usuario.NIVEL_ACESSO_PARTICIPANTE);
            this.usuarioRepositorio.cadastrarUsuario(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    ValidacaoCadastro validacaoCadastro = (ValidacaoCadastro) response;
                    if(validacaoCadastro.isAlreadyTakenEmail()) listener.onAlreadyTakenEmail();
                    if(validacaoCadastro.isAlreadyTakenMatricula()) listener.onAlreadyTakenMatricula();
                    if(!validacaoCadastro.isAlreadyTakenMatricula() && !validacaoCadastro.isAlreadyTakenEmail()){
                        listener.onSuccess(validacaoCadastro.getMessage());
                    }
                }
                @Override
                public void onFailure(String message) {
                    Log.i("ErrorResposta",message);
                }
            }, usuario);
        } catch (CampoVazioException e) {
            listener.onEmptyField(e.getMessage());
        } catch (EmailInvalidoException e) {
            listener.onInvalidEmail(e.getMessage());
        } catch (SenhaInvalidaException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (MatriculaInvalidaException e) {
            listener.onInvalidMatricula(e.getMessage());
        }
    }

    private void verificarEmailMatriculaJaCadastradas(final CadastroUsuarioListener listener, Usuario usuario){

    }
}
