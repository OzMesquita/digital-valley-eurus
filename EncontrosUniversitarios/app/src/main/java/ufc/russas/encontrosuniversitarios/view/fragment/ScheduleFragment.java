package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import ufc.russas.encontrosuniversitarios.R;

import ufc.russas.encontrosuniversitarios.view.adapter.ScheduleAdapter;
import ufc.russas.encontrosuniversitarios.ScheduleListInterface;
import ufc.russas.encontrosuniversitarios.model.EventDay;
import ufc.russas.encontrosuniversitarios.viewmodel.ScheduleViewModel;

import java.util.List;

public class ScheduleFragment extends Fragment implements ScheduleListInterface {
    private RecyclerView atividadesRecyclerView;
    private ScheduleAdapter scheduleAdapter;
    private ScheduleViewModel scheduleViewModel;
    private ProgressBar progressBar;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoFragment.java
    private List<DiaEvento> diasEventos;
    public ProgramacaoFragment() {
=======
    private List<EventDay> diasEventos;

    public ScheduleFragment() {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ScheduleFragment.java
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_programacao, container, false);
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        atividadesRecyclerView = view.findViewById(R.id.programacao_recycler_view);
        progressBar = view.findViewById(R.id.programacao_progress);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ProgramacaoFragment.java

        programacaoViewModel.getDiasEvento().observe(this, new Observer<List<DiaEvento>>() {
=======
        scheduleViewModel.getDiasEvento().observe(this, new Observer<List<EventDay>>() {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ScheduleFragment.java
            @Override
            public void onChanged(List<EventDay> eventDays) {
                diasEventos = eventDays;
                scheduleAdapter = new ScheduleAdapter(diasEventos, null, false);
                atividadesRecyclerView.setAdapter(scheduleAdapter);
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
        scheduleViewModel.carregarAtividades(new ActivitiesListener() {
            @Override
            public void onLoading() {
                atividadesRecyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDone() {
                atividadesRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public ScheduleAdapter getScheduleAdapter() {
        return scheduleAdapter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
