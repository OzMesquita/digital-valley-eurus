package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "sala")
public class SalaEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_sala",index = true)
    private int id;
    private int numero;
    private String nome;

    public SalaEntidade(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }
}
