package com.example.encontrosuniversitarios.model;

public class ValidacaoCadastro {
    private boolean alreadyTakenEmail;
    private boolean alreadyTakenMatricula;

    public ValidacaoCadastro(boolean alreadyTakenEmail, boolean alreadyTakenMatricula) {
        this.alreadyTakenEmail = alreadyTakenEmail;
        this.alreadyTakenMatricula = alreadyTakenMatricula;
    }

    public boolean isAlreadyTakenEmail() {
        return alreadyTakenEmail;
    }

    public void setAlreadyTakenEmail(boolean alreadyTakenEmail) {
        this.alreadyTakenEmail = alreadyTakenEmail;
    }

    public boolean isAlreadyTakenMatricula() {
        return alreadyTakenMatricula;
    }

    public void setAlreadyTakenMatricula(boolean alreadyTakenMatricula) {
        this.alreadyTakenMatricula = alreadyTakenMatricula;
    }
}
