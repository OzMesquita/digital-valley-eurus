package com.example.encontrosuniversitarios.model;

public class VerificacaoMatricula {
    private String message;
    private Data data;
    private String status;

    public VerificacaoMatricula() {
    }

    public VerificacaoMatricula(String message, Data data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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


