package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.ValidacaoCadastro;
import com.example.encontrosuniversitarios.model.VerificacaoMatricula;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import com.example.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import com.example.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioListener;
import com.example.encontrosuniversitarios.view.fragment.VerificacaoMatriculaListener;

public class CadastroUsuarioViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;
    private MutableLiveData<VerificacaoMatricula>  verificacaoMatricula;

    public CadastroUsuarioViewModel(){
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        verificacaoMatricula = new MutableLiveData<>();
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

    public void realizarValidacao(final VerificacaoMatriculaListener listener, String matricula) {
        if(matricula!=null && matricula.length()==6){
            this.usuarioRepositorio.verificarMatricula(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    VerificacaoMatricula verMatricula = (VerificacaoMatricula) response;

                    if(!verMatricula.getStatus().equals("failure") && verMatricula.getData().getMatricula()!= null && verMatricula.getData().getNome()!=null){
                        listener.onValidMatricula();
                        verificacaoMatricula.setValue(verMatricula);
                    }else {
                        listener.onUnregisteredMatricula();
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure();
                }
            }, matricula);
        }else{
            listener.onInvalidMatricula();
        }
    }

    public LiveData<VerificacaoMatricula> getVerificacaoMatricula() {
        return verificacaoMatricula;
    }
}
