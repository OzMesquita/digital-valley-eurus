package com.example.encontrosuniversitarios.model.dao.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "co_autor")
public class CoAutorEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coautor_id")
    private Integer id;
    private String nome;
    private String email;
    @ForeignKey(entity = TrabalhoEntidade.class,
                parentColumns = "trabalho_fk",
                childColumns = "id_trabalho")
    private Integer trabalho;

    public CoAutorEntidade(String nome, String email, Integer trabalho) {
        this.nome = nome;
        this.email = email;
        this.trabalho = trabalho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Integer trabalho) {
        this.trabalho = trabalho;
    }
}
