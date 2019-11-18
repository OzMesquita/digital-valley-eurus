package ufc.russas.encontrosuniversitarios.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.model.User;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.RegisterValidation;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.MatriculaVerification;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserRepository;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmptyFieldException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidEmailException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidMatriculaException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidPasswordException;
import ufc.russas.encontrosuniversitarios.view.fragment.UserRegistrationListener;
import ufc.russas.encontrosuniversitarios.view.fragment.VerificacaoMatriculaListener;

public class UserRegistrationViewModel extends ViewModel {
    private UserRepository userRepository;
    private User user;
    private MutableLiveData<MatriculaVerification>  verificacaoMatricula;

    public UserRegistrationViewModel(){
        this.userRepository = UserRepository.getInstance();
        verificacaoMatricula = new MutableLiveData<>();
    }

    public void cadastrarUsuario(String nome, String matricula, String email, String senha, final UserRegistrationListener listener){
        try {
            this.user = new User(nome,email,matricula,senha);
            this.user.setNivelAcesso(User.NIVEL_ACESSO_PARTICIPANTE);
            listener.onLoading();
            this.userRepository.registerUser(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    RegisterValidation registerValidation = (RegisterValidation) response;
                    listener.onDone();
                    if(registerValidation.isAlreadyTakenEmail()) listener.onAlreadyTakenEmail();
                    if(registerValidation.isAlreadyTakenMatricula()) listener.onAlreadyTakenMatricula();
                    if(!registerValidation.isAlreadyTakenMatricula() && !registerValidation.isAlreadyTakenEmail()){
                        listener.onSuccess(registerValidation.getMessage());
                    }
                }
                @Override
                public void onFailure(String message) {
                    listener.onDone();
                }
            }, user);
        } catch (EmptyFieldException e) {
            listener.onEmptyField(e.getMessage());
        } catch (InvalidEmailException e) {
            listener.onInvalidEmail(e.getMessage());
        } catch (InvalidPasswordException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (InvalidMatriculaException e) {
            listener.onInvalidMatricula(e.getMessage());
        }
    }

    public void realizarValidacao(final VerificacaoMatriculaListener listener, String matricula) {
        if(matricula!=null && matricula.length()==6){
            listener.onLoading();
            this.userRepository.verifyMatricula(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    MatriculaVerification verMatricula = (MatriculaVerification) response;
                    listener.onDone();
                    if(!verMatricula.getStatus().equals("failure") && verMatricula.getData().getMatricula()!= null && verMatricula.getData().getName()!=null){
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

    public LiveData<MatriculaVerification> getVerificacaoMatricula() {
        return verificacaoMatricula;
    }
}
