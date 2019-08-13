package com.example.encontrosuniversitarios.model.exceptions;

public class CampoVazioException extends Exception{
    private String campo;
    public CampoVazioException(String campo){
        this.campo = campo;
    }

    @Override
    public String getMessage() {
        String message = "O campo"+this.campo+" está vazio.";
        return message;
    }
}
