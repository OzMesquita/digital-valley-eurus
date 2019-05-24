package com.example.encontrosuniversitarios.model.dao.entidade;

import org.joda.time.DateTime;

import androidx.room.TypeConverter;

public class DateTimeConverter {
    @TypeConverter
    public static DateTime fromTimestamp(Long valor){
        return valor == null ? null : new DateTime(valor);
    }

    @TypeConverter
    public static Long dateToTimestamp(DateTime data){
        return data == null ? null : data.getMillis();
    }
}
