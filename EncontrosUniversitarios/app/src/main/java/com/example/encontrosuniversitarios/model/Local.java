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
    private String localCompleto;

    public String getLocalCompleto() {
        return localCompleto;
    }

    public void setLocalCompleto(String localCompleto) {
        this.localCompleto = localCompleto;
    }

    public Local(String nome, Sala sala) {
        this.nome = nome;
        this.sala = sala;
    }

    public Local() {
    }

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

    public String getLocalSala() {
        String numero = sala.getNumero() == 0 ? "" : " " + sala.getNumero();
        if (andar == null) {
            andar = "";
        } else if (pontoReferencia == null) {
            pontoReferencia = "";
        } else if (nome == null) {
            pontoReferencia = "";
        }
        return nome + numero + ", " + andar + "º andar, " + pontoReferencia;
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
