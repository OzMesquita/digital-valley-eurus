package com.example.encontrosuniversitarios.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.Date;

public class Atividade implements Parcelable {
    private String nome;
    private String descricao;
    private Categoria categoria;
    private DateTime horarioInicialPrevisto;
    private DateTime horarioInicio;
    private DateTime horarioFinal;
    private Trabalho trabalho;
    private Usuario apresentador;
    private Local local;

    public Atividade(String nome){
        this.nome = nome;
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
