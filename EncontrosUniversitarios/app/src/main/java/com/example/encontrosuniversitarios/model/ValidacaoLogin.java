package com.example.encontrosuniversitarios.model;

public class ValidacaoLogin {

    private boolean alreadyTakenEmail;
    private boolean alreadyTakenMatricula;
    private String message;

    public ValidacaoLogin(boolean alreadyTakenSenha, boolean alreadyTakenMatricula, String message) {
        this.alreadyTakenEmail = alreadyTakenSenha;
        this.alreadyTakenMatricula = alreadyTakenMatricula;
        this.message = message;
    }

    public boolean isAlreadyTakenSenha() {
        return alreadyTakenEmail;
    }

    public void setAlreadyTakenSenha(boolean alreadyTakenEmail) {
        this.alreadyTakenEmail = alreadyTakenEmail;
    }

    public boolean isAlreadyTakenMatricula() {
        return alreadyTakenMatricula;
    }

    public void setAlreadyTakenMatricula(boolean alreadyTakenMatricula) {
        this.alreadyTakenMatricula = alreadyTakenMatricula;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
