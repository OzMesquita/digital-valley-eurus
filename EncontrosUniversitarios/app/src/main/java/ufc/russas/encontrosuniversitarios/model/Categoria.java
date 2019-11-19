package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

public class Categoria {
    @SerializedName("id_categoria")
    private Integer id;
    @SerializedName("nome_categoria")
    private String nome;
    @SerializedName("descricao")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
