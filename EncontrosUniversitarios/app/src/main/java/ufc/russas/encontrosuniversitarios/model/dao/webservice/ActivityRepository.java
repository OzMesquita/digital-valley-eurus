package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.ActivityEvaluation;
import ufc.russas.encontrosuniversitarios.model.ActivityCriteria;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.EvaluationResult;
import ufc.russas.encontrosuniversitarios.model.dao.database.WebServiceDatabase;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRepository {

    private static ActivityRepository activityRepository;
    private ActivityService activityService;


    private ActivityRepository(){
        activityService = WebServiceDatabase.getInstance().getAtividadeService();
    }

    public static ActivityRepository getInstance(){
        if(activityRepository ==null){
            activityRepository = new ActivityRepository();
        }
        return activityRepository;
    }

    public void search(final ResponseListener listener) {
        activityService.getActivities().enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void searchTodaysActivities(final ResponseListener listener){
        activityService.getTodaysActivities().enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void searchFrequentedActivities(final ResponseListener listener, int coordinatorId) {
        activityService.getFrequentedActivities(coordinatorId).enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void searchParticipatedActivities(final ResponseListener listener, int userId) {
        activityService.getParticipatedActivities(userId).enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void updateActivity(Activity activity, boolean isStartingTime, final ResponseListener listener){
        DateTime time = isStartingTime ? activity.getInitialTime() : activity.getFinalTime();
        activityService.updateActivity(activity.getId(),new StartingTime(isStartingTime,time)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void getCurrentTime(final ResponseListener listener){
        activityService.getCurrentTime().enqueue(new Callback<DateTime>() {
            @Override
            public void onResponse(Call<DateTime> call, Response<DateTime> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DateTime> call, Throwable t) {
                listener.onFailure("Erro momento");
            }
        });
    }

    public  void getCriteria(final ResponseListener listener){
        activityService.getCriteria().enqueue(new Callback<List<ActivityCriteria>>() {
            @Override
            public void onResponse(Call<List<ActivityCriteria>> call, Response<List<ActivityCriteria>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ActivityCriteria>> call, Throwable t) {
                listener.onFailure("Erro ao search critérios do banco");
            }
        });
    }

    public void evaluateActivity(final ResponseListener listener, ActivityEvaluation activityEvaluation){
        activityService.evaluateActivity(activityEvaluation).enqueue(new Callback<EvaluationResult>() {
            @Override
            public void onResponse(Call<EvaluationResult> call, Response<EvaluationResult> response) {
                listener.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<EvaluationResult> call, Throwable t) {
                listener.onFailure("Erro ao avaliar atividade");
            }
        });
    }

    public void verifyAlreadyEvaluatedActivity(final ResponseListener listener, ActivityEvaluation activityEvaluation){
        activityService.verifyAlreadyEvaluatedActivity(activityEvaluation).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao verificar se a atividade já está avaliada");
            }
        });
    }
    public void getProfessorActivities(final ResponseListener listener, int professorId) {
        activityService.getProfessorActivities(professorId).enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                listener.onSuccess(response.body());
            }

            public void onFailure(Call<List<Activity>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }
}
