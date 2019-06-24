package com.example.encontrosuniversitarios.model;

import com.example.encontrosuniversitarios.view.helper.FormatadorData;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoAtividades {

    public List<DiaEvento> agruparAtividadesEmDias(List<Atividade> atividades){
        List<DiaEvento> diasEvento = new ArrayList<>();
        List<DateTime> dias = new ArrayList<>();
        for(Atividade atividade:atividades){
            DateTime diaPrevisto = new DateTime(atividade.getHorarioInicialPrevisto().getYear(),
                    atividade.getHorarioInicialPrevisto().getMonthOfYear(), atividade.getHorarioInicialPrevisto().getDayOfMonth(),0, 0);
            if(!dias.contains(diaPrevisto)){
                dias.add(diaPrevisto);
            }
        }

        for(DateTime dia:dias){
            List<Atividade> atividadesDia = new ArrayList<>();

            for(Atividade atividade:atividades){
                DateTime diaPrevisto = new DateTime(atividade.getHorarioInicialPrevisto().getYear(),
                        atividade.getHorarioInicialPrevisto().getMonthOfYear(), atividade.getHorarioInicialPrevisto().getDayOfMonth(),0, 0);
                if(dia.equals(diaPrevisto)){
                    atividadesDia.add(atividade);
                }
            }
            diasEvento.add(new DiaEvento(FormatadorData.formatarData(dia),atividadesDia));
        }
        return diasEvento;
    }

    public List<List<Atividade>> dividirAtividadesEmEstados(List<Atividade> atividades){
        List<List<Atividade>> listas = new ArrayList<>();
        List<Atividade> atividadesIniciadas = new ArrayList<>();
        List<Atividade> atividadesSeguintes = new ArrayList<>();
        List<Atividade> atividadesFinalizadas = new ArrayList<>();

        for(Atividade atividade:atividades){
            if(atividade.atividadeIniciada()){
                atividadesIniciadas.add(atividade);
            }else if(atividade.atividadeFinalizada()){
                atividadesFinalizadas.add(atividade);
            }else{
                atividadesSeguintes.add(atividade);
            }
        }
        listas.add(atividadesIniciadas);
        listas.add(atividadesSeguintes);
        listas.add(atividadesFinalizadas);
        return listas;
    }
}
