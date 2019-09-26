package com.example.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

public class Local {
    @SerializedName("id_local")
    private Integer id;
    @SerializedName("nome_local")
    private String nome;
    @SerializedName("ponto_referencia_local")
    private String pontoReferencia;
    @SerializedName("andar_local")
    private String andar;
    private Sala sala;

    public Local(String nome,Sala sala){
        this.nome = nome;
        this.sala=sala;
    }

    public Local(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getLocalSala(){
        String local;
       // local = sala.getNome().concat(nome).concat(" " + andar + " Andar");
       return local =  nome + " " + sala.getNumero() + ", "+ andar + " andar";
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
