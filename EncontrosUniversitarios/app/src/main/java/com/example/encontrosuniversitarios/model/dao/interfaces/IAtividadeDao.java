package com.example.encontrosuniversitarios.model.dao.interfaces;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.entidade.AtividadeEntidade;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface IAtividadeDao {
    @Insert
    void inserir(AtividadeEntidade atividade);

    @Delete
    void deletar(AtividadeEntidade atividade);

    @Update
    void atualizar(AtividadeEntidade atividade);

    @Query("")
    LiveData<List<Atividade>> buscarTodasAtividades();
}
