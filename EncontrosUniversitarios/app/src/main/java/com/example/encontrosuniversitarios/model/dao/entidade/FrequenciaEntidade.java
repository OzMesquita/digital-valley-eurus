package com.example.encontrosuniversitarios.model.dao.entidade;

import com.example.encontrosuniversitarios.model.Sala;
import com.example.encontrosuniversitarios.model.Usuario;

import org.joda.time.DateTime;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "frequencia", foreignKeys = {
        @ForeignKey(entity = Usuario.class,
                    parentColumns = "id",
                    childColumns = "participante"),
        @ForeignKey(entity = SalaEntidade.class,
                    parentColumns = "id",
                    childColumns = "sala")
})
public class FrequenciaEntidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Integer participante;
    private Integer sala;
    @ColumnInfo(name = "checkin")
    @TypeConverters({DateTimeConverter.class})
    private DateTime checkIn;
    @ColumnInfo(name = "checkout")
    @TypeConverters({DateTimeConverter.class})
    private DateTime checkOut;
}
