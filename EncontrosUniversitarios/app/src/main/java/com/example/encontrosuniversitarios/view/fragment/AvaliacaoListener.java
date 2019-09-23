package com.example.encontrosuniversitarios.view.fragment;

public interface AvaliacaoListener {
    void onAlreadyEvaluatedActivity();
    void onError(String message);
    void onSuccess();
}
