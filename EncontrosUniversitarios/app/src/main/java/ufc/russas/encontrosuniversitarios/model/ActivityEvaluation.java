package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityEvaluation {
    @SerializedName("atividade")
    private int idAtividade;
    @SerializedName("avaliador")
    private int idAvaliador;
    private String comentarios;
    private List<Grade> grades;

<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/model/AvaliacaoAtividade.java
    public AvaliacaoAtividade(){}

    public AvaliacaoAtividade(int idAtividade, int idAvaliador, String comentarios, List<Nota> notas) {
=======
    public ActivityEvaluation(int idAtividade, int idAvaliador, String comentarios, List<Grade> grades) {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/model/ActivityEvaluation.java
        this.idAtividade = idAtividade;
        this.idAvaliador = idAvaliador;
        this.comentarios = comentarios;
        this.grades = grades;
    }
}