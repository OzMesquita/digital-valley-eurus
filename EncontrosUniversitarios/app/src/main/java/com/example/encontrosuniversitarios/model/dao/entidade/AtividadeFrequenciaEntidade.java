package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "atividades_frequencia")
public class AtividadeFrequenciaEntidade{
    @ColumnInfo(name = "frequencia_fk")
    private Integer frequencia;
    @ColumnInfo(name = "atividade_fk")
    private Integer atividade;

    public AtividadeFrequenciaEntidade(Integer frequencia, Integer atividade) {
        this.frequencia = frequencia;
        this.atividade = atividade;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public Integer getAtividade() {
        return atividade;
    }
}
