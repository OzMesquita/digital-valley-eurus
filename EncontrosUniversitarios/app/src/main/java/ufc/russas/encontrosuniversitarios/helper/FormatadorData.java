package ufc.russas.encontrosuniversitarios.helper;

import org.joda.time.DateTime;

public class FormatadorData {

    public static String formatarData(DateTime dia){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dia.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(dia.getMonthOfYear());
        stringBuilder.append("/");
        stringBuilder.append(dia.getYear());
        return stringBuilder.toString();
    }

    public static String formatarDataHorario(DateTime dia){
        if(dia==null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dia.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(dia.getMonthOfYear());
        stringBuilder.append("/");
        stringBuilder.append(dia.getYear());
        stringBuilder.append(" às ");
        stringBuilder.append(dia.getHourOfDay());
        stringBuilder.append(":");
        stringBuilder.append(dia.getMinuteOfHour());
        return stringBuilder.toString();
    }

    public void a(){}
}
