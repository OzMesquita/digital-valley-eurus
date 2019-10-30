package com.example.encontrosuniversitarios.view.fragment;

import com.example.encontrosuniversitarios.model.Usuario;

public interface LoginListener {
    void onSuccess(Usuario usuario);
    void onFailure(String message);
    void onEmptyField(String field);
    void onInvalidPassword(String message);
    void onInvalidEmail(String message);
    void onUnregisteredEmail();
    void onWrongPassword();
    void onLoading();
    void onDone();
}
