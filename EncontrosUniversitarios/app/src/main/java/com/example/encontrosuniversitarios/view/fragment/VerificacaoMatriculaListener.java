package com.example.encontrosuniversitarios.view.fragment;

public interface VerificacaoMatriculaListener {

    void onInvalidMatricula();
    void onFailure();
    void onValidMatricula();
    void onUnregisteredMatricula();
    void onLoading();
    void onDone();
}
