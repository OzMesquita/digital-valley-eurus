package com.example.encontrosuniversitarios.view.fragment;

public interface LoginListener {
    void onSuccess(String message);

    void onEmptyField(String message);
    void onInvalidPassword(String message);
    void onInvalidMatricula(String message);
}
