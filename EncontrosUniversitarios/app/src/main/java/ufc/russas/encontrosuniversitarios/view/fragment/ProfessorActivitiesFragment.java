package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ProgressBar;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesProfessorFragment.java

import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
=======
import ufc.russas.encontrosuniversitarios.ScheduleListInterface;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProfessorActivitiesFragment.java
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.adapter.TodaysScheduleAdapter;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesProfessorFragment.java
import ufc.russas.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

=======
import ufc.russas.encontrosuniversitarios.viewmodel.ActivityEvaluationViewModel;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProfessorActivitiesFragment.java
import java.util.List;


public class ProfessorActivitiesFragment extends Fragment implements ScheduleListInterface {
    private TodaysScheduleAdapter todaysScheduleAdapter;
    private ActivityEvaluationViewModel activityEvaluationViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades_professor, container, false);
        recyclerView = view.findViewById(R.id.atividades_professor);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        activityEvaluationViewModel = ViewModelProviders.of(this).get(ActivityEvaluationViewModel.class);
        progressBar = view.findViewById(R.id.professor_progress);
        activityEvaluationViewModel.getAtividadesAvaliação().observe(this, new Observer<List<Activity>>() {
            @Override
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesProfessorFragment.java
            public void onChanged(List<Atividade> atividades) {
                if(atividades!=null && atividades.size()>=1){
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                    MySharedPreferences.getInstance(getContext()).setRoom(atividades.get(0).getLocal().getSala().getId());
                }
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities(),true);
                recyclerView.setAdapter(programacaoDoDiaAdapter);
=======
            public void onChanged(List<Activity> activities) {
                if (activities != null && activities.size() >= 1) {
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(activities);
                    MySharedPreferences.getInstance(getContext()).setRoom(activities.get(0).getPlace().getRoom().getId());
                }
                todaysScheduleAdapter = new TodaysScheduleAdapter(activities, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities(), true);
                recyclerView.setAdapter(todaysScheduleAdapter);
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProfessorActivitiesFragment.java
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        activityEvaluationViewModel.carregarAtividades(getContext(), new ActivitiesListener() {
            @Override
            public void onLoading() {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onDone() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public Filterable getScheduleAdapter() {
        return todaysScheduleAdapter;
    }
}


