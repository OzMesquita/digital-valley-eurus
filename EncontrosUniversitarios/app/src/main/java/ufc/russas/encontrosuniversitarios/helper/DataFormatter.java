package ufc.russas.encontrosuniversitarios.helper;

import org.joda.time.DateTime;

public class DataFormatter {

    public static String formatarData(DateTime day){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(day.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(day.getMonthOfYear());
        stringBuilder.append("/");
        stringBuilder.append(day.getYear());
        return stringBuilder.toString();
    }

    public static String formatarDataHorario(DateTime day){
        if(day==null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(day.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(day.getMonthOfYear());
        stringBuilder.append("/");
        stringBuilder.append(day.getYear());
        stringBuilder.append(" às ");
        stringBuilder.append(day.getHourOfDay());
        stringBuilder.append(":");
        stringBuilder.append(day.getMinuteOfHour());
        return stringBuilder.toString();
    }
}
