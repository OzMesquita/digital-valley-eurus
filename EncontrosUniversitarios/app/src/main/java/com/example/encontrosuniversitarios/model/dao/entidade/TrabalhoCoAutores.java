package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "coautores",
        primaryKeys = {"trabalho_fk","coautor_fk"},
        foreignKeys = {
                @ForeignKey(entity = TrabalhoEntidade.class,
                        parentColumns = "id_trabalho",
                        childColumns = "trabalho_fk"),
                @ForeignKey(entity = UsuarioEntidade.class,
                        parentColumns = "id_usuario",
                        childColumns = "coautor_fk")
        })
public class TrabalhoCoAutores {
    @ColumnInfo(name = "trabalho_fk")
    private int trabalho;
    @ColumnInfo(name = "usuario_fk")
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
