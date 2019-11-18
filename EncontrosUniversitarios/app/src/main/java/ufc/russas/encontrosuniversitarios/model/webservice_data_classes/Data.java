package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

public class Data{
    private String matricula;
    private String name;

    public Data(String matricula, String name) {
        this.matricula = matricula;
        this.name = name;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

