package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

import org.joda.time.DateTime;

public class Inicio {
    private boolean isHorarioInicio;
    private DateTime horario;

    public Inicio(boolean isHorarioInicio, DateTime horario) {
        this.isHorarioInicio = isHorarioInicio;
        this.horario = horario;
    }

    public boolean isHorarioInicio() {
        return isHorarioInicio;
    }

    public void setHorarioInicio(boolean horarioInicio) {
        isHorarioInicio = horarioInicio;
    }

    public void setHorario(DateTime horario) {
        this.horario = horario;
    }

    public DateTime getHorario() {
        return horario;
    }
}
