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
                    parentColumns = "id_categoria",
                    childColumns = "categoria_fk"),
        @ForeignKey(entity = TrabalhoEntidade.class,
                    parentColumns = "id_trabalho",
                    childColumns = "trabalho_fk"),
        @ForeignKey(entity = UsuarioEntidade.class,
                    parentColumns = "id_usuario",
                    childColumns = "apresentador"),
        @ForeignKey(entity = LocalEntidade.class,
                    parentColumns = "id_local",
                    childColumns = "local_fk"),
})
public class AtividadeEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_atividade")
    private int id;
    @ColumnInfo(name = "nome_atividade")
    private String nome;
    private String descricao;
    @ColumnInfo(name = "horario_previsto")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioInicialPrevisto;
    @ColumnInfo(name = "horario_inicial")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioInicio;
    @ColumnInfo(name = "horario_final")
    @TypeConverters({DateTimeConverter.class})
    private DateTime horarioFinal;
    @ColumnInfo(name = "categoria_fk")
    private Integer categoria;
    @ColumnInfo(name = "trabalho_fk")
    private Integer trabalho;
    private Integer apresentador;
    @ColumnInfo(name = "local_fk")
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
