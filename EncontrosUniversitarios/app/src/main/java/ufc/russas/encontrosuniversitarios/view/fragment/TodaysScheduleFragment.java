package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.SearchView;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.adapter.ScheduleTabsAdapter;
import ufc.russas.encontrosuniversitarios.ScheduleListInterface;
import ufc.russas.encontrosuniversitarios.R;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoDoDiaFragment.java
import ufc.russas.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;
=======
import ufc.russas.encontrosuniversitarios.viewmodel.ScheduleViewModel;

>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/TodaysScheduleFragment.java
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class TodaysScheduleFragment extends Fragment implements ScheduleListInterface {
    private ScheduleTabsAdapter scheduleTabsAdapter;
    private TabLayout tabLayout;
    private SearchView searchView;
    private ScheduleViewModel scheduleViewModel;
    private List<Integer> nomesEstadoAtividade;
    private ProgressBar diaProgress;
    private ViewPager viewPager;

<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoDoDiaFragment.java
    public ProgramacaoDoDiaFragment(){
=======
    public TodaysScheduleFragment() {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/TodaysScheduleFragment.java
        nomesEstadoAtividade = new ArrayList<>();
        nomesEstadoAtividade.add(R.string.started_activities);
        nomesEstadoAtividade.add(R.string.next_activities);
        nomesEstadoAtividade.add(R.string.finished_activities);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoDoDiaFragment.java
        View view = inflater.inflate(R.layout.fragment_programacao_do_dia,container,false);
        programacaoAbasAdapter = new ProgramacaoAbasAdapter(getFragmentManager());
        programacaoViewModel = ViewModelProviders.of(this).get(ProgramacaoViewModel.class);
=======
        View view = inflater.inflate(R.layout.fragment_programacao_do_dia, container, false);
        scheduleTabsAdapter = new ScheduleTabsAdapter(getFragmentManager());
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/TodaysScheduleFragment.java

        diaProgress = view.findViewById(R.id.dia_progress);
        scheduleViewModel.getAtividadesDoDia().observe(this, new Observer<List<List<Activity>>>() {
            @Override
            public void onChanged(List<List<Activity>> lists) {
                for (int i = 0; i < 3; i++) {
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoDoDiaFragment.java
                    AtividadesFragment atividadesFragment = AtividadesFragment.newInstance(lists.get(i), i);
                    if(programacaoAbasAdapter.getCount() < 3){
                        programacaoAbasAdapter.addFragment(atividadesFragment, getString(nomesEstadoAtividade.get(i)));
=======
                    ActivitiesFragment activitiesFragment = ActivitiesFragment.newInstance(lists.get(i), i);
                    if (scheduleTabsAdapter.getCount() < 3) {
                        scheduleTabsAdapter.addFragment(activitiesFragment, getString(nomesEstadoAtividade.get(i)));
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/TodaysScheduleFragment.java
                    }
                }
                scheduleTabsAdapter.notifyDataSetChanged();
            }
        });

        viewPager = view.findViewById(R.id.programacao_do_dia_view_pager);
        viewPager.setAdapter(scheduleTabsAdapter);

        tabLayout = view.findViewById(R.id.programacao_do_dia_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        scheduleViewModel.carregarAtividadesDoDia(new ActivitiesListener() {
            @Override
            public void onLoading() {
                viewPager.setVisibility(View.GONE);
                diaProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDone() {
                viewPager.setVisibility(View.VISIBLE);
                diaProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public Filterable getScheduleAdapter() {
        ActivitiesFragment programacaoFragment = (ActivitiesFragment) this.scheduleTabsAdapter.getItem(tabLayout.getSelectedTabPosition());
        return programacaoFragment.getScheduleAdapter();
    }
}
