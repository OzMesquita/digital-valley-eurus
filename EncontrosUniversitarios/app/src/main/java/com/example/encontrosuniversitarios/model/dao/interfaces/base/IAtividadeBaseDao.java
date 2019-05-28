package com.example.encontrosuniversitarios.model.dao.interfaces.base;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IAtividadeBaseDao {
    public void inserir(Atividade atividade);
    public LiveData<List<Atividade>> buscar();
}
