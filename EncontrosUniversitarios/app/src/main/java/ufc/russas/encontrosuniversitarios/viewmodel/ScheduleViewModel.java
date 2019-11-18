package ufc.russas.encontrosuniversitarios.viewmodel;

import android.util.Log;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.EventDay;
import ufc.russas.encontrosuniversitarios.model.ScheduleActivities;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityRepository;
import ufc.russas.encontrosuniversitarios.view.fragment.ActivitiesListener;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleViewModel extends ViewModel {

    private ActivityRepository activityRepository;
    private ScheduleActivities scheduleActivities;
    private MutableLiveData<List<Activity>> atividades;
    private MutableLiveData<List<EventDay>> atividadesDiasEvento;
    private MutableLiveData<List<List<Activity>>> atividadesDoDia;

    public ScheduleViewModel(){
        this.activityRepository = ActivityRepository.getInstance();
        atividadesDiasEvento = new MutableLiveData<>();
        atividadesDoDia = new MutableLiveData<>();
        atividades = new MutableLiveData<>();
        scheduleActivities = new ScheduleActivities();
    }

    public LiveData<List<Activity>> getAtividades() {
        return atividades;
    }

    public LiveData<List<EventDay>> getDiasEvento(){
        return atividadesDiasEvento;
    }

    public MutableLiveData<List<List<Activity>>> getAtividadesDoDia() {
        return atividadesDoDia;
    }

    public void carregarAtividades(final ActivitiesListener listener){
        listener.onLoading();
        activityRepository.search(new ResponseListener<List<Activity>>() {
            @Override
            public void onSuccess(List<Activity> atividadesEvento) {
                listener.onDone();
                atividades.setValue(atividadesEvento);
                atividadesDiasEvento.setValue(scheduleActivities.agruparAtividadesEmDias(atividadesEvento));
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                Log.i("AtvFailura:",message);
            }
        });
    }

    public void carregarAtividadesDoDia(final ActivitiesListener listener){
        listener.onLoading();
        activityRepository.searchTodaysActivities(new ResponseListener<List<Activity>>() {
            @Override
            public void onSuccess(List<Activity> activities) {
                listener.onDone();
                atividadesDoDia.setValue(scheduleActivities.dividirAtividadesEmEstados(activities));
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                Log.i("AtvFailura:",message);
            }
        });
    }

}