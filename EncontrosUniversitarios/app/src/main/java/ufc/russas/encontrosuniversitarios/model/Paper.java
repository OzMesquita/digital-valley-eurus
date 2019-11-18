package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

public class Paper {
    @SerializedName("id_trabalho")
    private Integer id;
    private String titulo;
    private Category modalidade;
    private User autor;
    private String orientador;
    //private List<Usuario> coAutores;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Category getModalidade() {
        return modalidade;
    }

    public void setModalidade(Category modalidade) {
        this.modalidade = modalidade;
    }

    public User getAutorPrincipal() {
        return autor;
    }

    public void setAutorPrincipal(User autorPrincipal) {
        this.autor = autorPrincipal;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    /*
    public List<Usuario> getCoAutores() {
        return coAutores;
    }

    public void setCoAutores(List<Usuario> coAutores) {
        this.coAutores = coAutores;
    }
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
