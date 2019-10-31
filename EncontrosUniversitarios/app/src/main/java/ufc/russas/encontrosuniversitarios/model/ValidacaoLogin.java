package ufc.russas.encontrosuniversitarios.model;

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

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public boolean isUnregisteredEmail() {
        return unregisteredEmail;
    }

    public void setUnregisteredEmail(boolean unregisteredEmail) {
        this.unregisteredEmail = unregisteredEmail;
    }

    public boolean isWrongPassword() {
        return wrongPassword;
    }

    public void setWrongPassword(boolean wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

}

