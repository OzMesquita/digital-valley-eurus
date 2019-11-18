package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

import ufc.russas.encontrosuniversitarios.model.User;

public class LoginValidation {
    private boolean unregisteredEmail;
    private boolean wrongPassword;
    private boolean loginSuccessful;
    private User loggedUser;

    public LoginValidation(boolean unregisteredEmail, boolean wrongPassword, boolean loginSuccessful, User loggedUser) {
        this.unregisteredEmail = unregisteredEmail;
        this.wrongPassword = wrongPassword;
        this.loginSuccessful = loginSuccessful;
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
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

