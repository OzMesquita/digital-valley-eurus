package ufc.russas.encontrosuniversitarios.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificacaoMatricula {

    @Expose
    @SerializedName("data")
    private DadosValidacaoMatricula dadosValidacaoMatricula;
    private String status;
    private String mensagem;

    public VerificacaoMatricula() {
    }

    public VerificacaoMatricula(String mensagem, DadosValidacaoMatricula dadosValidacaoMatricula, String status) {
        this.mensagem = mensagem;
        this.dadosValidacaoMatricula = dadosValidacaoMatricula;
        this.status = status;
    }

    public DadosValidacaoMatricula getDadosValidacaoMatricula() {
        return dadosValidacaoMatricula;
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

    public void setDadosValidacaoMatricula(DadosValidacaoMatricula dadosValidacaoMatricula) {
        this.dadosValidacaoMatricula = dadosValidacaoMatricula;
    }
}


