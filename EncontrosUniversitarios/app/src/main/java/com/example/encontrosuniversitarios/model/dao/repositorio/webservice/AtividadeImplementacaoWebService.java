package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AtividadeImplementacaoWebService implements IAtividadeBaseDao {
    @Override
    public void inserir(Atividade atividade) {

    }

    @Override
    public LiveData<List<Atividade>> buscar() {
        return null;
    }
}
