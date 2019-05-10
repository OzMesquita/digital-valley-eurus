package com.example.encontrosuniversitarios.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Atividade implements Parcelable {
    private String nome;
    private String local;
    private Date data;

    public Atividade(String nome,String local,Date data){
        this.nome = nome;
        this.local = local;
        this.data = data;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
