package ufc.russas.encontrosuniversitarios.model;

public class ActivityCriteria {

    private int id;
    private String categoria, criterio;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCriterio() {
        return criterio;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public ActivityCriteria(int id, String categoria, String criterio) {
        this.id = id;
        this.categoria = categoria;
        this.criterio = criterio;
        this.nota = 1;
    }

    public ActivityCriteria() {
        this.nota = 1;
    }
}
