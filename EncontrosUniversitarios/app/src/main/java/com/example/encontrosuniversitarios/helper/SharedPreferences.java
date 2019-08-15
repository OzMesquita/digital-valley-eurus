package com.example.encontrosuniversitarios.helper;

public class SharedPreferences {

    private int userId;
    private String userNome;

    public int getEserNivelAcesso() {
        return eserNivelAcesso;
    }

    public void setEserNivelAcesso(int eserNivelAcesso) {
        this.eserNivelAcesso = eserNivelAcesso;
    }

    private int eserNivelAcesso;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
