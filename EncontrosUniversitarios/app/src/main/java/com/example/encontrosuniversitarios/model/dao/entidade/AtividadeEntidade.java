package com.example.encontrosuniversitarios.model.dao.entidade;

import com.example.encontrosuniversitarios.model.Categoria;
import com.example.encontrosuniversitarios.model.Local;
import com.example.encontrosuniversitarios.model.Trabalho;
import com.example.encontrosuniversitarios.model.Usuario;

import org.joda.time.DateTime;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(tableName = "atividade",foreignKeys = {
        @ForeignKey(entity = CategoriaEntidade.class,
                    parentColumns = "id",
                    childColumns = "categoria"),
        @ForeignKey(entity = TrabalhoEntidade.class,
                    parentColumns = "id",
                    childColumns = "trabalho"),
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id",
                    childColumns = "apresentador"),
        @ForeignKey(entity = LocalEntidade.class,
                    parentColumns = "id",
                    childColumns = "local"),
})
public class AtividadeEntidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String descricao;
    @ColumnInfo(name = "horario_inicial_previsto")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioInicialPrevisto;
    @ColumnInfo(name = "horario_inicio")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioInicio;
    @ColumnInfo(name = "horario_final")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioFinal;
    private Integer categoria;
    private Integer trabalho;
    private Integer apresentador;
    private Integer local;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public DateTime getHorarioInicialPrevisto() {
        return horarioInicialPrevisto;
    }

    public DateTime getHorarioInicio() {
        return horarioInicio;
    }

    public DateTime getHorarioFinal() {
        return horarioFinal;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public Integer getTrabalho() {
        return trabalho;
    }

    public Integer getApresentador() {
        return apresentador;
    }

    public Integer getLocal() {
        return local;
    }
}
