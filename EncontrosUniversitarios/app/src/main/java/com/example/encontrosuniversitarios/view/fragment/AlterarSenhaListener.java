package com.example.encontrosuniversitarios.view.fragment;

public interface AlterarSenhaListener {
    void onSuccess();
    void onFailure(String message);
    void onPasswordsDoesntMatch();
    void onInvalidToken();
    void onEmptyField(String fieldName);
    void onShortPassword();
    void onLoading();
    void onDone();
}
