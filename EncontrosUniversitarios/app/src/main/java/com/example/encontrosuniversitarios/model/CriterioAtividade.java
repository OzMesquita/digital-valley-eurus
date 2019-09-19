package com.example.encontrosuniversitarios.model;

public class CriterioAtividade {

    int id;
    String categoria, nomeCriterio;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeCriterio() {
        return nomeCriterio;
    }

    public void setNomeCriterio(String nomeCriterio) {
        this.nomeCriterio = nomeCriterio;
    }

    public CriterioAtividade(int id, String categoria, String nomeCriterio) {
        this.id = id;
        this.categoria = categoria;
        this.nomeCriterio = nomeCriterio;
    }
}
