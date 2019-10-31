package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    @SerializedName("id_sala")
    private Integer id;
    @SerializedName("numero")
    private int numero;
    @SerializedName("nome_sala")
    private String nome;
    private List<Atividade> atividades;
    private String descricao_sala;

    public String getDescricao_sala() {
        return descricao_sala;
    }
    public void setDescricao_sala(String descricao_sala) {
        this.descricao_sala = descricao_sala;
    }

    public Sala(int numero) {
        this.numero = numero;
    }

    public Sala(){
        this.atividades = new ArrayList<>();
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
