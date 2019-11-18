package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesFragment.java

import ufc.russas.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Atividade;

=======
import ufc.russas.encontrosuniversitarios.view.adapter.TodaysScheduleAdapter;
import ufc.russas.encontrosuniversitarios.ScheduleListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Activity;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ActivitiesFragment.java
import java.util.ArrayList;
import java.util.List;

public class ActivitiesFragment extends Fragment implements ScheduleListInterface {
    private RecyclerView atividadesRecyclerView;
    private TodaysScheduleAdapter todaysScheduleAdapter;
    private List<Activity> activities;

    private static final String ATIVIDADES_ARGS = "ATIVIDADES";
    private static final String ATIVIDADES_EMPTY = "EMPTY";


<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesFragment.java
    public static AtividadesFragment newInstance(List<Atividade> atividades,int index) {
        AtividadesFragment fragment = new AtividadesFragment();
        Bundle args = new Bundle();
        Parcelable []atividadesParcelable = new Parcelable[atividades.size()];
        for(int i=0;i<atividades.size();i++){
            atividadesParcelable[i] = atividades.get(i);
=======
    public static ActivitiesFragment newInstance(List<Activity> activities, int index) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        Parcelable[] atividadesParcelable = new Parcelable[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            atividadesParcelable[i] = activities.get(i);
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ActivitiesFragment.java
        }
        args.putParcelableArray(ATIVIDADES_ARGS,atividadesParcelable);
        String emptyText = "";
        switch (index){
            case 0:
                emptyText = "Não existem activities ocorrendo no momento";
                break;
            case 1:
                emptyText = "Todas as activities de hoje já ocorreram";
                break;
            case 2:
                emptyText = "Não existem activities finalizadas";
                break;
        }
        args.putString(ATIVIDADES_EMPTY,emptyText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesFragment.java
            atividades = new ArrayList<>();
            Parcelable []atividadesParcelable = getArguments().getParcelableArray(ATIVIDADES_ARGS);
            for(Parcelable p:atividadesParcelable){
                atividades.add((Atividade) p);
=======
            activities = new ArrayList<>();
            Parcelable[] atividadesParcelable = getArguments().getParcelableArray(ATIVIDADES_ARGS);
            for (Parcelable p : atividadesParcelable) {
                activities.add((Activity) p);
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ActivitiesFragment.java
            }
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividades, container, false);
        TextView textView = view.findViewById(R.id.empty_list);
        atividadesRecyclerView = view.findViewById(R.id.programacao_do_dia_recycler_view);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesFragment.java
        programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades,null,false);
        atividadesRecyclerView.setAdapter(programacaoDoDiaAdapter);

        if(atividades.size()== 0){
=======
        todaysScheduleAdapter = new TodaysScheduleAdapter(activities, null, false);
        atividadesRecyclerView.setAdapter(todaysScheduleAdapter);

        if (activities.size() == 0) {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/ActivitiesFragment.java
            String emptyListMessage = getArguments().getString(ATIVIDADES_EMPTY);
            textView.setText(emptyListMessage);
            textView.clearAnimation();
            textView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todaysScheduleAdapter.notifyDataSetChanged();
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
        todaysScheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public Filterable getScheduleAdapter() {
        return todaysScheduleAdapter;
    }
}
