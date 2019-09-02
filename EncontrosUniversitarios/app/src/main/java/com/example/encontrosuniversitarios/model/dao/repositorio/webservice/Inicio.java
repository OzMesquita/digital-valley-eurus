package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

public class Inicio {
    private boolean isHorarioInicio;

    public Inicio(boolean isHorarioInicio) {
        this.isHorarioInicio = isHorarioInicio;
    }

    public boolean isHorarioInicio() {
        return isHorarioInicio;
    }

    public void setHorarioInicio(boolean horarioInicio) {
        isHorarioInicio = horarioInicio;
    }
}
