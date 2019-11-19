package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoAtividade {
    @SerializedName("atividade")
    private int idAtividade;
    @SerializedName("avaliador")
    private int idAvaliador;
    private String comentarios;
    private List<Nota> notas;

    public AvaliacaoAtividade(){}

    public AvaliacaoAtividade(int idAtividade, int idAvaliador, String comentarios, List<Nota> notas) {
        this.idAtividade = idAtividade;
        this.idAvaliador = idAvaliador;
        this.comentarios = comentarios;
        this.notas = notas;
    }
}