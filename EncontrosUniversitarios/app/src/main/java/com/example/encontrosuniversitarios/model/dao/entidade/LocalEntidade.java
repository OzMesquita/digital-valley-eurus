package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "local")
public class LocalEntidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String pontoReferencia;
    private String andar;
    @ForeignKey(entity = SalaEntidade.class,parentColumns = "id",childColumns = "sala")
    private Integer sala;
    private String bloco;

    public LocalEntidade(String pontoReferencia, String andar, Integer sala, String bloco) {
        this.pontoReferencia = pontoReferencia;
        this.andar = andar;
        this.sala = sala;
        this.bloco = bloco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public String getAndar() {
        return andar;
    }

    public int getSala() {
        return sala;
    }

    public String getBloco() {
        return bloco;
    }
}
