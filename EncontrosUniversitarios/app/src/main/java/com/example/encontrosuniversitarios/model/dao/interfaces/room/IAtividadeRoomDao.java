package com.example.encontrosuniversitarios.model.dao.interfaces.room;

import com.example.encontrosuniversitarios.model.dao.entidade.AtividadeEntidade;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IAtividadeRoomDao {
    @Insert
    public void inserir(AtividadeEntidade atividadeEntidade);

    @Query("SELECT * FROM atividade")
    public LiveData<List<AtividadeEntidade>> buscar();
}
