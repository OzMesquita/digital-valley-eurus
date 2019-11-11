package ufc.russas.encontrosuniversitarios.model.webservice;

import ufc.russas.encontrosuniversitarios.model.Usuario;

public class ValidacaoLogin {
    private boolean unregisteredEmail;
    private boolean wrongPassword;
    private boolean loginSuccessful;
    private Usuario usuarioLogado;

    public ValidacaoLogin(boolean unregisteredEmail, boolean wrongPassword, boolean loginSuccessful, Usuario usuarioLogado) {
        this.unregisteredEmail = unregisteredEmail;
        this.wrongPassword = wrongPassword;
        this.loginSuccessful = loginSuccessful;
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public boolean isUnregisteredEmail() {
        return unregisteredEmail;
    }

    public boolean isWrongPassword() {
        return wrongPassword;
    }
}

