package com.example.encontrosuniversitarios.model;

import org.joda.time.DateTime;

import java.util.List;

public class Frequencia {
    private Usuario participante;
    private Sala sala;
    private DateTime checkIn;
    private DateTime checkOut;
    private List<Atividade> atividadesFrequentadas;

    public Usuario getParticipante() {
        return participante;
    }

    public void setParticipante(Usuario participante) {
        this.participante = participante;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public DateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(DateTime checkIn) {
        this.checkIn = checkIn;
    }

    public DateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(DateTime checkOut) {
        this.checkOut = checkOut;
    }

    public List<Atividade> getAtividadesFrequentadas() {
        return atividadesFrequentadas;
    }

    public void setAtividadesFrequentadas(List<Atividade> atividadesFrequentadas) {
        this.atividadesFrequentadas = atividadesFrequentadas;
    }
}
