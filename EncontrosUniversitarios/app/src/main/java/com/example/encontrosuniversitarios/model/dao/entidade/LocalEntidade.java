package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "local", foreignKeys = {
        @ForeignKey(entity = SalaEntidade.class,parentColumns = "id_sala",childColumns = "sala_fk")
})
public class LocalEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_local",index = true)
    private int id;
    @ColumnInfo(name = "ponto_referencia_local")
    private String pontoReferencia;
    @ColumnInfo(name = "andar_local")
    private String andar;
    @ColumnInfo(name = "sala_fk")
    private Integer sala;
    private String nome;

    private String bloco;

    public LocalEntidade(String nome, String pontoReferencia, String andar, Integer sala, String bloco) {
        this.nome = nome;
        this.pontoReferencia = pontoReferencia;
        this.andar = andar;
        this.sala = sala;
        this.bloco = bloco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getNome() {
        return nome;
    }
}
