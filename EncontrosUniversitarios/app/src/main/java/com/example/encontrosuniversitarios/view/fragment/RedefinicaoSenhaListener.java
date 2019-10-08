package com.example.encontrosuniversitarios.view.fragment;

import com.example.encontrosuniversitarios.model.Usuario;

public interface RedefinicaoSenhaListener {
    void onSuccess();
    void onFailure(String message);
    void onEmptyField(String field);

}
