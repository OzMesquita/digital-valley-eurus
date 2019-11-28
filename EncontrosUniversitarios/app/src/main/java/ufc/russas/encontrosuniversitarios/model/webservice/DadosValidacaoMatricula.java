package ufc.russas.encontrosuniversitarios.model.webservice;

public class DadosValidacaoMatricula {
    private String matricula;
    private String nome;

    public DadosValidacaoMatricula(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

