package com.example.encontrosuniversitarios.model;

import java.util.List;

public class Trabalho {
    private String titulo;
    private String modalidade;
    private Usuario autorPrincipal;
    private Usuario orientador;
    private List<Usuario> coAutores;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Usuario getAutorPrincipal() {
        return autorPrincipal;
    }

    public void setAutorPrincipal(Usuario autorPrincipal) {
        this.autorPrincipal = autorPrincipal;
    }

    public Usuario getOrientador() {
        return orientador;
    }

    public void setOrientador(Usuario orientador) {
        this.orientador = orientador;
    }

    public List<Usuario> getCoAutores() {
        return coAutores;
    }

    public void setCoAutores(List<Usuario> coAutores) {
        this.coAutores = coAutores;
    }
}
