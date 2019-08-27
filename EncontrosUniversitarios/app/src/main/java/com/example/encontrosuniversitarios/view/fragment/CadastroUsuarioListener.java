package com.example.encontrosuniversitarios.view.fragment;

public interface CadastroUsuarioListener {
    void onSuccess(String message);
    void onEmptyField(String message);
    void onInvalidEmail(String message);
    void onInvalidPassword(String message);
    void onInvalidMatricula(String message);
    void onAlreadyTakenEmail();
    void onAlreadyTakenMatricula();
}