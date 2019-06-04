package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoria")
public class CategoriaEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria",index = true)
    private int id;
    @ColumnInfo(name = "nome_categoria")
    private String nome;
    private String descricao;

    public CategoriaEntidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
