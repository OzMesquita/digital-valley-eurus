package ufc.russas.encontrosuniversitarios.model.webservice;

public class AlterarSenhaResponse {
    private boolean codigoInvalido;
    private String mensagem;

    public AlterarSenhaResponse(){}

    public AlterarSenhaResponse(boolean codigoInvalido, String mensagem) {
        this.codigoInvalido = codigoInvalido;
        this.mensagem = mensagem;
    }

    public boolean isCodigoInvalido() {
        return codigoInvalido;
    }

    public void setCodigoInvalido(boolean codigoInvalido) {
        this.codigoInvalido = codigoInvalido;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

