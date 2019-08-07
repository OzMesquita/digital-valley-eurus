package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AtividadesFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private List<Atividade> atividades;

    private static final String ATIVIDADES_ARGS = "ATIVIDADES";


    public static AtividadesFragment newInstance(List<Atividade> atividades) {
        AtividadesFragment fragment = new AtividadesFragment();
        Bundle args = new Bundle();
        Parcelable []atividadesParcelable = new Parcelable[atividades.size()];
        for(int i=0;i<atividades.size();i++){
            atividadesParcelable[i] = atividades.get(i);
        }
        args.putParcelableArray(ATIVIDADES_ARGS,atividadesParcelable);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            atividades = new ArrayList<>();
            Parcelable []atividadesParcelable = getArguments().getParcelableArray(ATIVIDADES_ARGS);
            for(Parcelable p:atividadesParcelable){
                atividades.add((Atividade) p);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividades, container, false);
        atividadesRecyclerView = view.findViewById(R.id.programacao_do_dia_recycler_view);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades);
        atividadesRecyclerView.setAdapter(programacaoDoDiaAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        programacaoDoDiaAdapter.notifyDataSetChanged();
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
        programacaoDoDiaAdapter.notifyDataSetChanged();
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
