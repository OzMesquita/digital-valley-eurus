package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.webservice.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.Validador;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.model.exceptions.CampoVazioException;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import ufc.russas.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import ufc.russas.encontrosuniversitarios.view.fragment.AlterarSenhaListener;
import ufc.russas.encontrosuniversitarios.view.fragment.LoginListener;
import ufc.russas.encontrosuniversitarios.view.fragment.LogoutListener;
import ufc.russas.encontrosuniversitarios.view.fragment.RedefinicaoSenhaListener;

public class LoginViewModel extends ViewModel {
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    public LoginViewModel() {
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
    }

    /**
     * Este método envia uma requisição ao webservice para validar os dados de login fornecidos,
     * caso sejam válidos ele loga o usuário no aplicativo
     * @param email
     * @param senha
     * @param listener
     */
    public void realizarLogin(String email, String senha, final LoginListener listener) {
        try {
            this.usuario = new Usuario(email, senha);
            DadosLogin dadosLogin = new DadosLogin(this.usuario.getEmail(), this.usuario.getSenha());
            listener.onLoading();
            this.usuarioRepositorio.realizarLogin(new ResponseListener() {
                ValidacaoLogin validacao;

                @Override
                public void onSuccess(Object response) {
                    listener.onDone();
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
                    listener.onDone();
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

    /**
     * Este método realiza o logout do usuário
     * @param context
     * @param listener
     */
    public void realizarLogout(Context context, LogoutListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        boolean result = preferences.clearData();
        if (result) {
            listener.onSuccessfulLogout();
        } else {
            listener.onFailure();
        }
    }

    /**
     * Este método envia um email de redefinição de senha para o email fornecido como parâmetro
     * @param email
     * @param listener
     */
    public void recuperacaoSenha(String email, final RedefinicaoSenhaListener listener){
        if (email.equals("") || !Validador.validarEmail(email)) {
            listener.onInvalidField();
        } else {
            listener.onLoading();
            this.usuarioRepositorio.recuperarSenha(new ResponseListener() {
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

    /**
     * Este método altera a senha do usuário enviando o código de validação e a nova senha para
     * redefinição
     * @param token
     * @param password
     * @param confirmPassword
     * @param listener
     */
    public void alterarSenha(String token, String password, String confirmPassword, final AlterarSenhaListener listener){
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
            this.usuarioRepositorio.alterarSenha(new ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    AlterarSenhaResponse alterarSenhaResponse = (AlterarSenhaResponse) response;
                    if(alterarSenhaResponse.isCodigoInvalido()){
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
            },new DadosAlterarSenha(token,password));
        }
    }
}


