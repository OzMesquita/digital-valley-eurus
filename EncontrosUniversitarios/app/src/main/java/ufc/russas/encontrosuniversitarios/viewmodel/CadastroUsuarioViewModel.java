package ufc.russas.encontrosuniversitarios.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCadastro;
import ufc.russas.encontrosuniversitarios.model.webservice.VerificacaoMatricula;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.model.exceptions.CampoVazioException;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import ufc.russas.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import ufc.russas.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import ufc.russas.encontrosuniversitarios.view.fragment.CadastroUsuarioListener;
import ufc.russas.encontrosuniversitarios.view.fragment.VerificacaoMatriculaListener;

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
            listener.onLoading();
            this.usuarioRepositorio.cadastrarUsuario(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    ValidacaoCadastro validacaoCadastro = (ValidacaoCadastro) response;
                    listener.onDone();
                    if(validacaoCadastro.isAlreadyTakenEmail()) listener.onAlreadyTakenEmail();
                    if(validacaoCadastro.isAlreadyTakenMatricula()) listener.onAlreadyTakenMatricula();
                    if(!validacaoCadastro.isAlreadyTakenMatricula() && !validacaoCadastro.isAlreadyTakenEmail()){
                        listener.onSuccess(validacaoCadastro.getMessage());
                    }
                }
                @Override
                public void onFailure(String message) {
                    Log.i("ErrorResposta",message);
                    listener.onDone();
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
            listener.onLoading();
            this.usuarioRepositorio.verificarMatricula(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    VerificacaoMatricula verMatricula = (VerificacaoMatricula) response;
                    listener.onDone();
                    if(!verMatricula.getStatus().equals("failure") && verMatricula.getData().getMatricula()!= null && verMatricula.getData().getNome()!=null){
                        listener.onValidMatricula();
                        verificacaoMatricula.setValue(verMatricula);
                    }else {
                        listener.onUnregisteredMatricula();
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onDone();
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
