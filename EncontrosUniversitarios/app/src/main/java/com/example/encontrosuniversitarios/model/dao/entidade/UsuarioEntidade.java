package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class UsuarioEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String matricula;
    @ColumnInfo(name = "nivel_acesso")
    private String nivelAcesso;

    public UsuarioEntidade(String nome, String email,String senha, String cpf,String matricula,String nivelAcesso) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.matricula = matricula;
        this.nivelAcesso = nivelAcesso;
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

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }
}
