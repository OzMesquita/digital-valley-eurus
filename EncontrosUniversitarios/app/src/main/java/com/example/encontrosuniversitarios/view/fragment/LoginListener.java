package com.example.encontrosuniversitarios.view.fragment;

public interface LoginListener {
    void onSuccess();
    void onFailure(String message);
    void onEmptyField(String field);
    void onInvalidPassword(String message);
    void onInvalidEmail(String message);
    void onUnregisteredEmail();
    void onWrongPassword();
}
