package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.SerializedName;

public class Grade {
    @SerializedName("criterio")
    private int idCriterio;
    private int nota;

    public Grade(){}

    public Grade(int idCriterio, int nota) {
        this.idCriterio = idCriterio;
        this.nota = nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
