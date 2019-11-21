package ufc.russas.encontrosuniversitarios.model;

import org.joda.time.DateTime;

public class HorarioAtividade {
    private boolean isHorarioInicio;
    private DateTime horario;

    public HorarioAtividade(boolean isHorarioInicio, DateTime horario) {
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
