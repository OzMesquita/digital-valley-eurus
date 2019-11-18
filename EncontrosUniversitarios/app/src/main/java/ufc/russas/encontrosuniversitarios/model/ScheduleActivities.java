package ufc.russas.encontrosuniversitarios.model;

import ufc.russas.encontrosuniversitarios.helper.DataFormatter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivities {

    public List<EventDay> agruparAtividadesEmDias(List<Activity> activities){
        List<EventDay> diasEvento = new ArrayList<>();
        List<DateTime> dias = new ArrayList<>();
        for(Activity activity : activities){
            DateTime diaPrevisto = new DateTime(activity.getInicialForeseenTime().getYear(),
                    activity.getInicialForeseenTime().getMonthOfYear(), activity.getInicialForeseenTime().getDayOfMonth(),0, 0);
            if(!dias.contains(diaPrevisto)){
                dias.add(diaPrevisto);
            }
        }

        for(DateTime dia:dias){
            List<Activity> atividadesDia = new ArrayList<>();

            for(Activity activity : activities){
                DateTime diaPrevisto = new DateTime(activity.getInicialForeseenTime().getYear(),
                        activity.getInicialForeseenTime().getMonthOfYear(), activity.getInicialForeseenTime().getDayOfMonth(),0, 0);
                if(dia.equals(diaPrevisto)){
                    atividadesDia.add(activity);
                }
            }
            diasEvento.add(new EventDay(DataFormatter.formatarData(dia),atividadesDia));
        }
        return diasEvento;
    }



    public List<List<Activity>> dividirAtividadesEmEstados(List<Activity> activities){
        List<List<Activity>> listas = new ArrayList<>();
        List<Activity> atividadesIniciadas = new ArrayList<>();
        List<Activity> atividadesSeguintes = new ArrayList<>();
        List<Activity> atividadesFinalizadas = new ArrayList<>();

        for(Activity activity : activities){
            if(activity.isStartedActivity()){
                atividadesIniciadas.add(activity);
            }else if(activity.isFinishedActivity()){
                atividadesFinalizadas.add(activity);
            }else{
                atividadesSeguintes.add(activity);
            }
        }
        listas.add(atividadesIniciadas);
        listas.add(atividadesSeguintes);
        listas.add(atividadesFinalizadas);
        return listas;
    }
}
