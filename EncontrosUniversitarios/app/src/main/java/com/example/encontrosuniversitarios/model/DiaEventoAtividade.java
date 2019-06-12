package com.example.encontrosuniversitarios.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class DiaEventoAtividade {

    public List<DiaEvento> agruparAtividadesEmDias(List<Atividade> atividades){
        List<DiaEvento> diasEvento = new ArrayList<>();
        List<DateTime> dias = new ArrayList<>();
        for(Atividade atividade:atividades){
            if(!dias.contains(atividade.getHorarioInicialPrevisto())){
                dias.add(atividade.getHorarioInicialPrevisto());
            }
        }

        for(DateTime dia:dias){
            List<Atividade> atividadesDia = new ArrayList<>();
            for(Atividade atividade:atividades){
                if(dia.equals(atividade.getHorarioInicialPrevisto())){
                    atividadesDia.add(atividade);
                }
            }
            diasEvento.add(new DiaEvento(formatarData(dia),atividadesDia));
        }
        return diasEvento;
    }

    private String formatarData(DateTime dia){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dia.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(dia.getMonthOfYear());
        stringBuilder.append("/");
        stringBuilder.append(dia.getYear());
        return stringBuilder.toString();
    }
}
