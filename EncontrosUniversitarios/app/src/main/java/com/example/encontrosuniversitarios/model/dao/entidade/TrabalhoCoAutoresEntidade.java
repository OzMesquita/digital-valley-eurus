package com.example.encontrosuniversitarios.model.dao.entidade;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Relation;

public class TrabalhoCoAutoresEntidade {

    @Embedded
    private TrabalhoEntidade trabalho;

    @Relation(parentColumn = "id_trabalho",
                entityColumn = "coautor_id")
    private List<CoAutorEntidade> coautores;


    public TrabalhoEntidade getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(TrabalhoEntidade trabalho) {
        this.trabalho = trabalho;
    }

    public List<CoAutorEntidade> getCoautores() {
        return coautores;
    }

    public void setCoautores(List<CoAutorEntidade> coautores) {
        this.coautores = coautores;
    }
}
