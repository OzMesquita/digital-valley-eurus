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
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id_usuario",
                    childColumns = "usuario_fk"),
        @ForeignKey(entity = SalaEntidade.class,
                    parentColumns = "id_sala",
                    childColumns = "sala_fk")
})
public class FrequenciaEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_frequencia")
    private int id;
    @ColumnInfo(name = "usuario_fk")
    private Integer participante;
    @ColumnInfo(name = "sala_fk")
    private Integer sala;
    @ColumnInfo(name = "check_in")
    @TypeConverters({DateTimeConverter.class})
    private DateTime checkIn;
    @ColumnInfo(name = "check_out")
    @TypeConverters({DateTimeConverter.class})
    private DateTime checkOut;
}
