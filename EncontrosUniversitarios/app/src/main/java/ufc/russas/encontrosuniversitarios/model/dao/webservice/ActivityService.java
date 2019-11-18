package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.ActivityEvaluation;
import ufc.russas.encontrosuniversitarios.model.ActivityCriteria;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.EvaluationResult;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ActivityService {
    @GET("atividades")
    Call<List<Activity>> getActivities();

    @GET("hoje/atividades/")
    Call<List<Activity>> getTodaysActivities();

    @PUT("atividades/{id}")
    Call<Boolean> updateActivity(@Path("id") int activityId, @Body StartingTime startingTime);

    @GET("atividades/coordenador/{id}")
    Call<List<Activity>> getFrequentedActivities(@Path("id") int coordinatorId);

    @GET("frequencia/{id}")
    Call<List<Activity>> getParticipatedActivities(@Path("id") int userId);

    @GET("momento/")
    Call<DateTime> getCurrentTime();

    @GET("criterios/")
    Call<List<ActivityCriteria>> getCriteria();

    @POST("avaliacao")
    Call<EvaluationResult> evaluateActivity(@Body ActivityEvaluation activityEvaluation);

    @POST("avaliada/")
    Call<Boolean> verifyAlreadyEvaluatedActivity(@Body ActivityEvaluation activityEvaluation);

    @GET("atividades/professor/{id}")
    Call<List<Activity>> getProfessorActivities(@Path  ("id") int professorId);

 }
