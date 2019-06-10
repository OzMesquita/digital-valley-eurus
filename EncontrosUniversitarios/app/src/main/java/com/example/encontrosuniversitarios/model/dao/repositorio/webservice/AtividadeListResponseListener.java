package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.List;

public interface AtividadeListResponseListener {
    void onSuccess(List<Atividade> atividades);
    void onFailure(String message);
}
