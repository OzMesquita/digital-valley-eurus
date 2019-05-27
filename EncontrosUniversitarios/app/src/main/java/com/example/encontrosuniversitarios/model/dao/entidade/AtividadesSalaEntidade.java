package com.example.encontrosuniversitarios.model.dao.entidade;


import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.encontrosuniversitarios.model.Atividade;

@Entity(tableName = "atividades_sala",foreignKeys = {
        @ForeignKey(entity = AtividadeEntidade.class,
                parentColumns = "id",
                childColumns = "atividade"),
        @ForeignKey(entity = SalaEntidade.class,
                parentColumns = "id",
                childColumns = "sala"),
})
public class AtividadesSalaEntidade {
    private Integer atividade;
    private Integer sala;

    public Integer getAtividade() {
        return atividade;
    }
    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }
}
