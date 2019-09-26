package com.example.encontrosuniversitarios.model;

public class VerificacaoMatricula {

    private String matricula;
    private String nome;

    public VerificacaoMatricula(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}