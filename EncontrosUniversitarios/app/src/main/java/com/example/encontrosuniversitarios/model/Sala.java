package com.example.encontrosuniversitarios.model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int numero;
    private String nome;
    private List<Atividade> atividades;


    public Sala(){
        this.atividades = new ArrayList<>();
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
