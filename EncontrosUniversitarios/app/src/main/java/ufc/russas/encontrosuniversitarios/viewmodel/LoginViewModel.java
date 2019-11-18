package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
<<<<<<< Updated upstream
import ufc.russas.encontrosuniversitarios.model.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.Validador;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.model.exceptions.CampoVazioException;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import ufc.russas.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import ufc.russas.encontrosuniversitarios.view.fragment.AlterarSenhaListener;
=======
import ufc.russas.encontrosuniversitarios.model.User;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.PasswordUpdateResponse;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.PasswordUpdateData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.LoginData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.LoginValidation;
import ufc.russas.encontrosuniversitarios.model.Validator;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserRepository;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmptyFieldException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidEmailException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidPasswordException;
import ufc.russas.encontrosuniversitarios.view.fragment.ChangePasswordListener;
>>>>>>> Stashed changes
import ufc.russas.encontrosuniversitarios.view.fragment.LoginListener;
import ufc.russas.encontrosuniversitarios.view.fragment.LogoutListener;
import ufc.russas.encontrosuniversitarios.view.fragment.PasswordRedefinitionListener;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    private User user;

    public LoginViewModel() {
        this.userRepository = UserRepository.getInstance();
    }

    public void realizarLogin(String email, String senha, final LoginListener listener) {
        try {
            this.user = new User(email, senha);
            LoginData loginData = new LoginData(this.user.getEmail(), this.user.getSenha());
            listener.onLoading();
            this.userRepository.login(new ResponseListener() {
                LoginValidation validacao;

                @Override
                public void onSuccess(Object response) {
                    listener.onDone();
                    validacao = (LoginValidation) response;
                    if (validacao.isUnregisteredEmail()) listener.onUnregisteredEmail();
                    if (validacao.isWrongPassword()) listener.onWrongPassword();
                    if ((!validacao.isWrongPassword() && !validacao.isUnregisteredEmail())
                            && validacao.isLoginSuccessful() && validacao.getLoggedUser() != null) {
                        user = validacao.getLoggedUser();
                        listener.onSuccess(user);
                    }
                }

                @Override
                public void onFailure(String message) {
                    listener.onDone();
                    listener.onFailure(message);
                }
            }, loginData);
        } catch (EmptyFieldException e) {
            listener.onEmptyField(e.getMessage());
        } catch (InvalidPasswordException e) {
            listener.onInvalidPassword(e.getMessage());
        } catch (InvalidEmailException e) {
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

    public void recuperacaoSenha(String email, final PasswordRedefinitionListener listener){
        if (email.equals("") || !Validator.validarEmail(email)) {
            listener.onInvalidField();
        } else {
            listener.onLoading();
            this.userRepository.recoverPassword(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    listener.onSuccess();
                    listener.onDone();
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure(message);
                    listener.onDone();
                }
            }, email);
        }
    }

    public void alterarSenha(String token, String password, String confirmPassword, final ChangePasswordListener listener){
        if(token.isEmpty()){
            listener.onEmptyField("TOKEN");
        }else if(password.isEmpty()){
            listener.onEmptyField("PASSWORD");
        }else if(confirmPassword.isEmpty()){
            listener.onEmptyField("CPASSWORD");
        }else if(!password.equals(confirmPassword)) {
            listener.onPasswordsDoesntMatch();
        }else if(password.length() < 6 || confirmPassword.length() < 6){
            listener.onShortPassword();
        }else{
            listener.onLoading();
            this.userRepository.updatePassword(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    PasswordUpdateResponse passwordUpdateResponse = (PasswordUpdateResponse) response;
                    if(passwordUpdateResponse.isInvalidToken()){
                        listener.onInvalidToken();
                    }else{
                        listener.onSuccess();
                    }
                    listener.onDone();
                }

                @Override
                public void onFailure(String message) {
                    listener.onFailure(message);
                    listener.onDone();
                }
            },new PasswordUpdateData(token,password));
        }
    }
}


