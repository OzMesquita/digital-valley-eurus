package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/AtividadesAlunoViewModel.java
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;
=======
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityRepository;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserRepository;
import ufc.russas.encontrosuniversitarios.view.fragment.ActivitiesListener;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/viewmodel/StudentAcivityViewModel.java

import java.util.List;

public class StudentAcivityViewModel extends ViewModel {
    private ActivityRepository activityRepository;
    private UserRepository userRepository;
    private MutableLiveData<List<Activity>> atividades;

    public StudentAcivityViewModel() {
        this.activityRepository = ActivityRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
        atividades = new MutableLiveData<>();
    }

    public void carregarAtividades(Context context, final ActivitiesListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        listener.onLoading();
        activityRepository.searchParticipatedActivities(new ResponseListener<List<Activity>>() {
            @Override
            public void onSuccess(List<Activity> atividadesEvento) {
                listener.onDone();
                atividades.setValue(atividadesEvento);
            }

            @Override
            public void onFailure(String message) {
                Log.i("AtvFailura:", message);
                listener.onDone();
            }
        }, preferences.getUserId());
    }

    public LiveData<List<Activity>> getAtividades() {
        return atividades;
    }

}
