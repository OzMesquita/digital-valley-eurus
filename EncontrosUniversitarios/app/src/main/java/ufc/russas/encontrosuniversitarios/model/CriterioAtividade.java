package ufc.russas.encontrosuniversitarios.model;

public class CriterioAtividade {

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

    public String getCriterio() {
        return criterio;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
