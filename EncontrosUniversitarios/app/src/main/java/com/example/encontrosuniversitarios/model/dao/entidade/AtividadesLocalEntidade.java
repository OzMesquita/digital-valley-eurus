package com.example.encontrosuniversitarios.model.dao.entidade;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Relation;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.List;

public class AtividadesLocalEntidade {
    @Embedded
    private SalaEntidade sala;

    @Relation(parentColumn = "id_sala",
            entityColumn = "id_atividade")
    private List<AtividadeEntidade> atividades;

    public SalaEntidade getSala() {
        return sala;
    }

    public void setSala(SalaEntidade sala) {
        this.sala = sala;
    }

    public List<AtividadeEntidade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeEntidade> atividades) {
        this.atividades = atividades;
    }
}
