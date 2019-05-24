package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class UsuarioEntidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String email;

    public UsuarioEntidade(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
