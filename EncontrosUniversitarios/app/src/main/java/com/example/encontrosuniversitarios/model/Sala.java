package com.example.encontrosuniversitarios.model;

public class Sala {
    private int numero;
    private String nome;
    private String andar;
    private String bloco;

    public String getBloco() {return bloco; }

    public void setBloco(String bloco) {  this.bloco = bloco; }

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

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

}
