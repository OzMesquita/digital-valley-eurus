package ufc.russas.encontrosuniversitarios.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ufc.russas.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;
import ufc.russas.encontrosuniversitarios.model.exceptions.CampoVazioException;
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

    public static final String INICIADA = "Iniciada";
    public static final String FINALIZADA = "Finalizada";
    public static final String NAO_INICIADA = "Não iniciada";

    public Atividade(String nome){
        this.nome = nome;
    }

    public Atividade(String nome,DateTime horarioInicialPrevisto,Local local, Usuario apresentador){
        this.nome = nome;
        this.horarioInicialPrevisto = horarioInicialPrevisto;
        this.local = local;
        this.apresentador = apresentador;
    }

    public Atividade(String nome, DateTime horarioInicialPrevisto, DateTime horarioInicio, DateTime horarioFinal){
        this(nome);
        this.horarioInicialPrevisto = horarioInicialPrevisto;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public Atividade(String nome, DateTime horarioInicialPrevisto){
        this(nome);
        this.horarioInicialPrevisto = horarioInicialPrevisto;
    }

    public Boolean iniciar(DateTime momento){
        if(this.horarioInicio==null){
            this.horarioInicio = momento;
            return true;
        }
        return false;
    }

    public Boolean finalizar(DateTime momento) throws AtividadeFinalizadaAntesDoHorarioIniciadoException {
        if(this.horarioFinal==null && this.horarioInicio!=null){
            if(horarioInicio.getMillis() <= momento.getMillis()){
                this.horarioFinal = momento;
                return true;
            }else{
                throw new AtividadeFinalizadaAntesDoHorarioIniciadoException("Atividade não pode ser finalizada, o horário final é menor que o inicial");
            }
        }
        return false;
    }

    public String getEstado(){
        if(atividadeIniciada()) return INICIADA;
        if(atividadeFinalizada()) return FINALIZADA;
        return NAO_INICIADA;
    }

    public boolean atividadeIniciada(){
        return this.horarioInicio!=null && this.horarioFinal==null;
    }

    public boolean atividadeFinalizada(){
        return this.horarioInicio!=null && this.horarioFinal!=null;
    }

    protected Atividade(Parcel in){
        Bundle bundle = in.readBundle();
        this.nome = bundle.getString("nome");
        long hInicio = bundle.getLong("horario_inicio");
        long hFinal = bundle.getLong("horario_final");
        this.horarioInicio = hInicio != 0 ? new DateTime(hInicio) : null;
        this.horarioFinal = hFinal != 0 ? new DateTime(hFinal) : null;
        this.horarioInicialPrevisto = new DateTime(bundle.getLong("horario_inicio_previsto"));
        this.descricao = bundle.getString("descricao");
        this.id = bundle.getInt("id");
        Local local = new Local();
        local.setNome(bundle.getString("local"));
        local.setPontoReferencia(bundle.getString("ponto"));
        local.setLocalCompleto(bundle.getString("local_completo"));
        Usuario usuario = new Usuario();
        try {
            usuario.setNome(bundle.getString("apresentador"));

        } catch (CampoVazioException e) {
            e.printStackTrace();
        }
        this.local = local;
        this.apresentador = usuario;
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

    public DateTime getHorarioInicialPrevisto() {
        return horarioInicialPrevisto;
    }

    public DateTime getHorarioInicio() {
        return horarioInicio;
    }

    public DateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(DateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Usuario getApresentador() {
        return apresentador;
    }

    public Local getLocal() {    return local;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        if(this.getHorarioInicio()!=null)bundle.putLong("horario_inicio",this.getHorarioInicio().getMillis());
        if(this.getHorarioFinal()!=null)bundle.putLong("horario_final",this.getHorarioFinal().getMillis());
        if(this.getHorarioInicialPrevisto()!=null)bundle.putLong("horario_inicio_previsto",this.getHorarioInicialPrevisto().getMillis());
        bundle.putString("nome",this.nome);
        bundle.putString("descricao",this.descricao);
        if(this.getLocal()!=null){
            if (this.getLocal().getSala()!= null) {
                if(this.local.getSala().getNumero()==0){
                    bundle.putString("local_completo", this.local.getNome()+ ", "+this.local.getAndar()+"º andar");
                }else {
                    bundle.putString("local_completo", this.local.getNome() + " " + this.local.getSala().getNumero() + ", " + this.local.getAndar() + "º andar");
                }
            }
            bundle.putString("local", this.local.getNome());
            bundle.putString("ponto", this.local.getPontoReferencia());
        }
        if(this.getApresentador()!=null)bundle.putString("apresentador",this.apresentador.getNome());

        bundle.putInt("id",this.id);
        dest.writeBundle(bundle);
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
