package com.example.encontrosuniversitarios.model.dao.repositorio.room;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.entidade.AtividadeEntidade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.room.IAtividadeRoomDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AtividadeRoomDaoAdapter implements IAtividadeBaseDao {
    public IAtividadeRoomDao iAtividadeRoomDao;

    public AtividadeRoomDaoAdapter(IAtividadeRoomDao iAtividadeRoomDaoImpl){
        this.iAtividadeRoomDao = iAtividadeRoomDaoImpl;
    }

    @Override
    public void inserir(Atividade atividade) {
        iAtividadeRoomDao.inserir(null);
    }

    @Override
    public LiveData<List<Atividade>> buscar() {
        LiveData<List<AtividadeEntidade>> atividades = iAtividadeRoomDao.buscar();
        return null;
    }
}
