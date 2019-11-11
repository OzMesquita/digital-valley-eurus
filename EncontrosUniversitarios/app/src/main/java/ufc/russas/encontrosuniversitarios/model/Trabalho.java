package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

public class Trabalho {
    @SerializedName("id_trabalho")
    private Integer id;
    private String titulo;
    private Categoria modalidade;
    private Usuario autor;
    private String orientador;

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
        return autor;
    }

    public void setAutorPrincipal(Usuario autorPrincipal) {
        this.autor = autorPrincipal;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
