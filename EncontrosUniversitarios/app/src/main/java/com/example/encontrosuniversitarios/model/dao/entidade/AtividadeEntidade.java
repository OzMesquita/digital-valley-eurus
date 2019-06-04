package com.example.encontrosuniversitarios.model.dao.entidade;

import com.example.encontrosuniversitarios.model.Categoria;
import com.example.encontrosuniversitarios.model.Local;
import com.example.encontrosuniversitarios.model.Trabalho;
import com.example.encontrosuniversitarios.model.Usuario;

import org.joda.time.DateTime;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
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
                    childColumns = "apresentador_fk"),
        @ForeignKey(entity = LocalEntidade.class,
                    parentColumns = "id_local",
                    childColumns = "local_fk"),}
                    )
public class AtividadeEntidade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_atividade",index = true)
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
    @ColumnInfo(name = "apresentador_fk")
    private Integer apresentador;
    @ColumnInfo(name = "local_fk")
    private Integer local;

    public AtividadeEntidade(String nome, String descricao, DateTime horarioInicialPrevisto,
                             DateTime horarioInicio, DateTime horarioFinal, Integer categoria,
                             Integer trabalho, Integer apresentador, Integer local) {
        this.nome = nome;
        this.descricao = descricao;
        this.horarioInicialPrevisto = horarioInicialPrevisto;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.categoria = categoria;
        this.trabalho = trabalho;
        this.apresentador = apresentador;
        this.local = local;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public static class AtividadeData{
        public int atividadeId;
        public String atividadeNome;
        public String atividadeDescricao;
        public DateTime atividadeHorarioInicialPrevisto;
        public DateTime atividadeHorarioInicio;
        public DateTime atividadeHorarioFinal;
        public int categoriaId;
        public String categoriaNome;
        public String categoriaDescricao;
        public int trabalhoId;
        public String trabalhoTitulo;
        public Integer trabalhoModalidade;
        public Integer trabalhoAutorPrincipal;
        public String trabalhoOrientador;
        public int apresentadorId;
        public String apresentadorNome;
        public String apresentadorEmail;
        public int localId;
        public String localPontoReferencia;
        public String localAndar;
        public String localNome;
        public int salaId;
        public int salaNumero;
        public String salaNome;
    }
}
