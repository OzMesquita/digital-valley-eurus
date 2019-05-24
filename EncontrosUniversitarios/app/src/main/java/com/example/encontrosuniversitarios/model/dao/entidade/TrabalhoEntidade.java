package com.example.encontrosuniversitarios.model.dao.entidade;

import com.example.encontrosuniversitarios.model.Usuario;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "trabalho",foreignKeys = {
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id",
                    childColumns = "autor_principal"),
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id",
                    childColumns = "orientador")
})
public class TrabalhoEntidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String modalidade;
    @ColumnInfo(name = "autor_principal")
    private Integer autorPrincipal;
    private Integer orientador;

    public TrabalhoEntidade(String titulo, String modalidade, Integer autorPrincipal, Integer orientador) {
        this.titulo = titulo;
        this.modalidade = modalidade;
        this.autorPrincipal = autorPrincipal;
        this.orientador = orientador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getModalidade() {
        return modalidade;
    }

    public Integer getAutorPrincipal() {
        return autorPrincipal;
    }

    public Integer getOrientador() {
        return orientador;
    }
}
