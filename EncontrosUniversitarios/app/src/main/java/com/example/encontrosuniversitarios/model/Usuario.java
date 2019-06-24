package com.example.encontrosuniversitarios.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @Expose
    @SerializedName("id_usuario")
    private Integer id;
    @Expose
    @SerializedName("nome")
    private String nome;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("matricula")
    private String matricula;
    @Expose
    @SerializedName("senha")
    private String senha;
    @Expose
    @SerializedName("cpf")
    private String cpf;
    @Expose
    @SerializedName("nivel_acesso")
    private int nivelAcesso;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
