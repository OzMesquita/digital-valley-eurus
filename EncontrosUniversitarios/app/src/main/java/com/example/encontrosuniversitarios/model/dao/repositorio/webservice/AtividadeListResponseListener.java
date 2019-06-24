package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.os.Parcelable;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.ArrayList;
import java.util.List;

public interface AtividadeListResponseListener {
    void onSuccess(List<Atividade> atividades);
    void onFailure(String message);
}
