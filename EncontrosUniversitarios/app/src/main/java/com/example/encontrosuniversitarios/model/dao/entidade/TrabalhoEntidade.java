package com.example.encontrosuniversitarios.model.dao.entidade;

import com.example.encontrosuniversitarios.model.Usuario;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "trabalho",foreignKeys = {
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id_usuario",
                    childColumns = "autor_fk"),
        @ForeignKey(entity = CategoriaEntidade.class,
                parentColumns = "id_categoria",
                childColumns = "modalidade_fk")
})
public class TrabalhoEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_trabalho",index = true)
    private int id;
    private String titulo;
    @ColumnInfo(name = "modalidade_fk")
    private Integer modalidade;
    @ColumnInfo(name = "autor_fk")
    private Integer autorPrincipal;
    private String orientador;

    public TrabalhoEntidade(String titulo, Integer modalidade, Integer autorPrincipal, String orientador) {
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

    public Integer getModalidade() {
        return modalidade;
    }

    public Integer getAutorPrincipal() {
        return autorPrincipal;
    }

    public String getOrientador() {
        return orientador;
    }
}
