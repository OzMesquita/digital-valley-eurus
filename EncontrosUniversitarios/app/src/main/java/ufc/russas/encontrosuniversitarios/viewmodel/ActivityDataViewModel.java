package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/AtividadeDadosViewModel.java
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.AvaliacaoAtividade;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;
=======
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.ActivityEvaluation;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityRepository;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.exceptions.ActivityFinishedBeforeStartTimeException;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/ActivityDataViewModel.java

import org.joda.time.DateTime;

public class ActivityDataViewModel extends ViewModel {
    private MutableLiveData<Activity> atividadeMutableLiveData;
    private MutableLiveData<DateTime> horarioInicioAtividade;
    private MutableLiveData<DateTime> horarioFinalAtividade;
    private MutableLiveData<Boolean> atividadeAvaliada;
    private ActivityRepository activityRepository;
    private Activity activity;

    public ActivityDataViewModel(){
        activityRepository = ActivityRepository.getInstance();
        atividadeMutableLiveData = new MutableLiveData<>();
        horarioFinalAtividade = new MutableLiveData<>();
        horarioInicioAtividade = new MutableLiveData<>();
        atividadeAvaliada = new MutableLiveData<>();
    }

    public void init(Activity activity){
        this.activity = activity;
        this.atividadeMutableLiveData.setValue(activity);
        horarioInicioAtividade.setValue(activity.getInitialTime());
        horarioFinalAtividade.setValue(activity.getFinalTime());
    }

    public void alterarHorarioAtividade(Context context) {
        if(!activity.isStartedActivity() && !activity.isFinishedActivity()){
            iniciarAtividade();
        }else if(activity.isStartedActivity()){
            finalizarAtividade(context);
        }
    }

    private void iniciarAtividade(){
        activityRepository.getCurrentTime(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                DateTime momento = (DateTime) response;
                boolean resultado = activity.start(momento);
                if(resultado){
                    atualizarHorariosAtividade(true);
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void finalizarAtividade(final Context context){

        activityRepository.getCurrentTime(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                try {
                    DateTime momento = (DateTime) response;
                    boolean resultado = activity.finish(momento);
                    if(resultado){
                        atualizarHorariosAtividade(false);
                    }
                }catch (ActivityFinishedBeforeStartTimeException e){
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();;
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void atualizarHorariosAtividade(final boolean isHorarioInicio){
        activityRepository.updateActivity(this.activity, isHorarioInicio, new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                atividadeMutableLiveData.setValue(activity);
                if(isHorarioInicio) {
                    horarioInicioAtividade.setValue(activity.getInitialTime());
                }else {
                    horarioFinalAtividade.setValue(activity.getFinalTime());
                }
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void verificarAtividadeJaAvaliada(final Context context){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int idUsuario = preferences.getUserId();
        activityRepository.verifyAlreadyEvaluatedActivity(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                Boolean value = (Boolean) response;
                atividadeAvaliada.setValue(value);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context,"Não foi possível realizar essa operação",Toast.LENGTH_LONG).show();
            }
        }, new ActivityEvaluation(activity.getId(),idUsuario,null,null));
    }

    public LiveData<Activity> getActivity() {
        return atividadeMutableLiveData;
    }

    public LiveData<DateTime> getHorarioInicio(){
        return horarioInicioAtividade;
    }

    public LiveData<DateTime> getHorarioFinal(){
        return horarioFinalAtividade;
    }

    public LiveData<Boolean> getAtividadeAvaliada() {
        return atividadeAvaliada;
    }
}
