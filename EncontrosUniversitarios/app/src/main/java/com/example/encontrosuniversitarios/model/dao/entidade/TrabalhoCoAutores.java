package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "coautores",
        primaryKeys = {"trabalho","coautor"},
        foreignKeys = {
                @ForeignKey(entity = TrabalhoEntidade.class,
                        parentColumns = "id",
                        childColumns = "trabalho"),
                @ForeignKey(entity = UsuarioEntidade.class,
                        parentColumns = "id",
                        childColumns = "coautor")
        })
public class TrabalhoCoAutores {
    private int trabalho;
    @ColumnInfo(name = "coautor")
    private int coAutor;

    public TrabalhoCoAutores(int trabalho, int coAutor) {
        this.trabalho = trabalho;
        this.coAutor = coAutor;
    }

    public int getTrabalho() {
        return trabalho;
    }

    public int getCoAutor() {
        return coAutor;
    }
}
