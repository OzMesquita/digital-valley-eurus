package com.example.encontrosuniversitarios.model;

public class CriterioAtividade {

    int id;
    String categoria, criterio;

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

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public CriterioAtividade(int id, String categoria, String criterio) {
        this.id = id;
        this.categoria = categoria;
        this.criterio = criterio;
    }

    public CriterioAtividade() {
    }
}
