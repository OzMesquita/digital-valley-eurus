package ufc.russas.encontrosuniversitarios.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ufc.russas.encontrosuniversitarios.model.exceptions.ActivityFinishedBeforeStartTimeException;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmptyFieldException;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

public class Activity implements Parcelable {
    @SerializedName("id_atividade")
    private Integer id;
    @SerializedName("nome_atividade")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("categoria")
    private Category category;
    @SerializedName("horario_previsto")
    private DateTime inicialForeseenTime;
    @SerializedName("horario_inicial")
    private DateTime initialTime;
    @SerializedName("horario_final")
    private DateTime finalTime;
    @SerializedName("trabalho")
    private Paper paper;
    @SerializedName("presenter")
    private User presenter;
    @SerializedName("local")
    private Local place;

    public static final String STARTED = "Iniciada";
    public static final String FINISHED = "Finalizada";
    public static final String NOT_STARTED = "Não iniciada";

    public Activity(String name){
        this.name = name;
    }

    public Activity(String name, DateTime inicialForeseenTime, Local place, User presenter){
        this.name = name;
        this.inicialForeseenTime = inicialForeseenTime;
        this.place = place;
        this.presenter = presenter;
    }

    public Activity(String name, DateTime inicialForeseenTime, DateTime initialTime, DateTime finalTime){
        this(name);
        this.inicialForeseenTime = inicialForeseenTime;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public Activity(String name, DateTime inicialForeseenTime){
        this(name);
        this.inicialForeseenTime = inicialForeseenTime;
    }

    public Boolean start(DateTime time){
        if(this.initialTime ==null){
            this.initialTime = time;
            return true;
        }
        return false;
    }

    public Boolean finish(DateTime time) throws ActivityFinishedBeforeStartTimeException {
        if(this.finalTime ==null && this.initialTime !=null){
            if(initialTime.getMillis() <= time.getMillis()){
                this.finalTime = time;
                return true;
            }else{
                throw new ActivityFinishedBeforeStartTimeException("Activity não pode ser finalizada, o horário final é menor que o inicial");
            }
        }
        return false;
    }

    public String getActivityState(){
        if(isStartedActivity()) return STARTED;
        if(isFinishedActivity()) return FINISHED;
        return NOT_STARTED;
    }

    public boolean isStartedActivity(){
        return this.initialTime !=null && this.finalTime ==null;
    }

    public boolean isFinishedActivity(){
        return this.initialTime !=null && this.finalTime !=null;
    }

    protected Activity(Parcel in){
        Bundle bundle = in.readBundle();
        this.name = bundle.getString("name");
        long startingTime = bundle.getLong("horario_inicio");
        long finishingTime = bundle.getLong("horario_final");
        this.initialTime = startingTime != 0 ? new DateTime(startingTime) : null;
        this.finalTime = finishingTime != 0 ? new DateTime(finishingTime) : null;
        this.inicialForeseenTime = new DateTime(bundle.getLong("horario_inicio_previsto"));
        this.description = bundle.getString("description");
        this.id = bundle.getInt("id");
        Local place = new Local();
        place.setNome(bundle.getString("place"));
        place.setPontoReferencia(bundle.getString("ponto"));
        place.setLocalCompleto(bundle.getString("local_completo"));
        User user = new User();
        try {
            user.setNome(bundle.getString("presenter"));

        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
        this.place = place;
        this.presenter = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getInicialForeseenTime() {
        return inicialForeseenTime;
    }

    public DateTime getInitialTime() {
        return initialTime;
    }

    public DateTime getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(DateTime finalTime) {
        this.finalTime = finalTime;
    }

    public User getPresenter() {
        return presenter;
    }

    public Local getPlace() {    return place;
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
        return name;
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
        if(this.getInitialTime()!=null)bundle.putLong("horario_inicio",this.getInitialTime().getMillis());
        if(this.getFinalTime()!=null)bundle.putLong("horario_final",this.getFinalTime().getMillis());
        if(this.getInicialForeseenTime()!=null)bundle.putLong("horario_inicio_previsto",this.getInicialForeseenTime().getMillis());
        bundle.putString("name",this.name);
        bundle.putString("description",this.description);
        if(this.getPlace()!=null){
            if (this.getPlace().getRoom()!= null) {
                if(this.place.getRoom().getNumero()==0){
                    bundle.putString("local_completo", this.place.getNome()+ ", "+this.place.getAndar()+"º andar");
                }else {
                    bundle.putString("local_completo", this.place.getNome() + " " + this.place.getRoom().getNumero() + ", " + this.place.getAndar() + "º andar");
                }
            }
            bundle.putString("place", this.place.getNome());
            bundle.putString("ponto", this.place.getPontoReferencia());
        }
        if(this.getPresenter()!=null)bundle.putString("presenter",this.presenter.getNome());

        bundle.putInt("id",this.id);
        dest.writeBundle(bundle);
    }

    public final static Creator<Activity> CREATOR = new Creator<Activity>() {
        @Override
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        @Override
        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };

}
