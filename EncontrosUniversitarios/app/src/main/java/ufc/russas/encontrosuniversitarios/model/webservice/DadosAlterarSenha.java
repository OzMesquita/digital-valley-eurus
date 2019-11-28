package ufc.russas.encontrosuniversitarios.model.webservice;

public class DadosAlterarSenha {
    private String codigo;
    private String senha;

    public DadosAlterarSenha(){}

    public DadosAlterarSenha(String codigo, String senha) {
        this.codigo = codigo;
        this.senha = senha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
