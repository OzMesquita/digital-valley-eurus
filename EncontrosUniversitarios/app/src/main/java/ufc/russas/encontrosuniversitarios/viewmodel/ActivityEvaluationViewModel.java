package ufc.russas.encontrosuniversitarios.viewmodel;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.ActivityEvaluation;
import ufc.russas.encontrosuniversitarios.model.ActivityCriteria;
import ufc.russas.encontrosuniversitarios.model.Grade;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.EvaluationResult;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityRepository;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserRepository;
import ufc.russas.encontrosuniversitarios.view.fragment.ActivitiesListener;
import ufc.russas.encontrosuniversitarios.view.fragment.EvaluationListener;
import java.util.ArrayList;
import java.util.List;

public class ActivityEvaluationViewModel extends ViewModel {

    private ActivityRepository activityRepository;
    private MutableLiveData<List<ActivityCriteria>> criterios;
    private Activity activity;
    private UserRepository userRepository;
    private MutableLiveData<List<Activity>> atividadesAvaliacao;

    public ActivityEvaluationViewModel() {
        this.activityRepository = ActivityRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
        criterios = new MutableLiveData<>();
        atividadesAvaliacao = new MutableLiveData<>();
    }

    public void init(Activity activity){
        this.activity = activity;
    }

    public LiveData<List<ActivityCriteria>> getCriterios() {
        return criterios;
    }

    public void listarCriterios(final ActivitiesListener listener){
        listener.onLoading();
        activityRepository.getCriteria(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                criterios.setValue((List<ActivityCriteria>) response);
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                criterios.setValue(null);
            }
        });
    }

    public void avaliarAtividade(String comentarios, Context context, final EvaluationListener listener){
        List<Grade> grades = new ArrayList<>();
        for(ActivityCriteria c: criterios.getValue()){
            grades.add(new Grade(c.getId(),c.getNota()));
        }
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int idAvaliador = preferences.getUserId();
        listener.onLoading();
        activityRepository.evaluateActivity(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                EvaluationResult evaluationResult = (EvaluationResult) response;
                if(evaluationResult.getAlreadyEvaluatedActivity()){
                    listener.onAlreadyEvaluatedActivity();
                }else if(!evaluationResult.getAlreadyEvaluatedActivity() && !evaluationResult.getError()){
                    listener.onSuccess();
                }else if(evaluationResult.getError()) {
                    listener.onError("Não foi possível avaliar esta activity");
                }
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                listener.onError("Houve um erro ao tentar realizar esta operação, verifique sua internet");
            }
        },new ActivityEvaluation(activity.getId(),idAvaliador,comentarios, grades));
    }

    public void carregarAtividades(Context context, final ActivitiesListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        listener.onLoading();
        activityRepository.getProfessorActivities(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                atividadesAvaliacao.setValue((List<Activity>)response);
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
            }
        }, preferences.getUserId());
    }

    public Activity getActivity() {
        return activity;
    }

    public LiveData<List<Activity>> getAtividadesAvaliação() {
        return atividadesAvaliacao;}


}
