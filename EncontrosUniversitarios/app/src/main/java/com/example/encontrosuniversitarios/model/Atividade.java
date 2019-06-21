package com.example.encontrosuniversitarios.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

public class Atividade implements Parcelable {
    @SerializedName("id_atividade")
    private Integer id;
    @SerializedName("nome_atividade")
    private String nome;
    @SerializedName("descricao")
    private String descricao;
    private Categoria categoria;
    @SerializedName("horario_previsto")
    private DateTime horarioInicialPrevisto;
    @SerializedName("horario_inicial")
    private DateTime horarioInicio;
    @SerializedName("horario_final")
    private DateTime horarioFinal;
    private Trabalho trabalho;
    private Usuario apresentador;
    private Local local;

    public Atividade(String nome){
        this.nome = nome;
    }

    public Atividade(String nome,DateTime horarioInicialPrevisto,Local local){
        this.nome = nome;
        this.horarioInicialPrevisto = horarioInicialPrevisto;
        this.local = local;
    }

    public Atividade(String nome, DateTime horarioInicialPrevisto, DateTime horarioInicio, DateTime horarioFinal){
        this(nome);
        this.horarioInicialPrevisto = horarioInicialPrevisto;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public Boolean verificarAtividadeOcorridaDentre(DateTime checkIn,DateTime checkOut){
        if(this.horarioInicio==null || this.horarioFinal==null) return null;
        return (this.horarioInicio.getMillis() >= checkIn.getMillis()) && (this.horarioFinal.getMillis() <= checkOut.getMillis());
    }

    public Boolean iniciar(){
        if(this.horarioInicio==null){
            this.horarioInicio = DateTime.now();
            return true;
        }
        return false;
    }

    public Boolean finalizar() throws AtividadeFinalizadaAntesDoHorarioIniciadoException {
        if(this.horarioFinal==null && this.horarioInicio!=null){
            DateTime agora = DateTime.now();
            if(horarioInicio.getMillis() <= agora.getMillis()){
                this.horarioFinal = DateTime.now();
                return true;
            }else{
                throw new AtividadeFinalizadaAntesDoHorarioIniciadoException("");
            }
        }
        return false;
    }

    public boolean atividadeIniciada(){
        return this.horarioInicio!=null && this.horarioFinal==null;
    }

    public boolean atividadeFinalizada(){
        return this.horarioInicio!=null && this.horarioFinal!=null;
    }

    protected Atividade(Parcel in){
        this.nome = in.readString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public DateTime getHorarioInicialPrevisto() {
        return horarioInicialPrevisto;
    }

    public void setHorarioInicialPrevisto(DateTime horarioInicialPrevisto) {
        this.horarioInicialPrevisto = horarioInicialPrevisto;
    }

    public DateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(DateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public DateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(DateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public Usuario getApresentador() {
        return apresentador;
    }

    public void setApresentador(Usuario apresentador) {
        this.apresentador = apresentador;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
    }

    public final static Creator<Atividade> CREATOR = new Creator<Atividade>() {
        @Override
        public Atividade createFromParcel(Parcel source) {
            return new Atividade(source);
        }

        @Override
        public Atividade[] newArray(int size) {
            return new Atividade[size];
        }
    };

}
