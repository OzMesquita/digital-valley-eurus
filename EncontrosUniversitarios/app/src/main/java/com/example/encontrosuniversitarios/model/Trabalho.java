package com.example.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trabalho {
    @SerializedName("id_trabalho")
    private Integer id;
    private String titulo;

    private Categoria modalidade;
    private Usuario autorPrincipal;
    private String orientador;
    private List<Usuario> coAutores;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getModalidade() {
        return modalidade;
    }

    public void setModalidade(Categoria modalidade) {
        this.modalidade = modalidade;
    }

    public Usuario getAutorPrincipal() {
        return autorPrincipal;
    }

    public void setAutorPrincipal(Usuario autorPrincipal) {
        this.autorPrincipal = autorPrincipal;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public List<Usuario> getCoAutores() {
        return coAutores;
    }

    public void setCoAutores(List<Usuario> coAutores) {
        this.coAutores = coAutores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
