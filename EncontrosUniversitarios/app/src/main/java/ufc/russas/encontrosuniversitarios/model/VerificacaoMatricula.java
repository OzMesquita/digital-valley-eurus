package ufc.russas.encontrosuniversitarios.model;

public class VerificacaoMatricula {
    private String mensagem;
    private Data data;
    private String status;

    public VerificacaoMatricula() {
    }

    public VerificacaoMatricula(String mensagem, Data data, String status) {
        this.mensagem = mensagem;
        this.data = data;
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Data data) {
        this.data = data;
    }
}


