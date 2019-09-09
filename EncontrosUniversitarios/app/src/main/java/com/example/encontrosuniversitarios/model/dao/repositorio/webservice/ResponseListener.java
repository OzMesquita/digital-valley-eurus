package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.os.Parcelable;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;

import java.util.ArrayList;
import java.util.List;

public interface ResponseListener<T> {
    void onSuccess(T response);
    void onFailure(String message);
}
