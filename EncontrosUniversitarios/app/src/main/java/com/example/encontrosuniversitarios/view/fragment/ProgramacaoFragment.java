package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoAdapter programacaoAdapter;

    private ProgramacaoViewModel programacaoViewModel;
    private List<DiaEvento> diasEventos;
    public ProgramacaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programacao, container, false);
        programacaoViewModel = ViewModelProviders.of(this).get(ProgramacaoViewModel.class);
        atividadesRecyclerView = view.findViewById(R.id.programacao_recycler_view);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        diasEventos = new ArrayList<>();
        programacaoAdapter = new ProgramacaoAdapter(diasEventos);


        programacaoViewModel.getAtividades().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                Log.i("Lista",String.valueOf(atividades.size()));
                diasEventos.add(new DiaEvento("04/04/1998",atividades));
                programacaoAdapter.notifyDataSetChanged();
            }
        });
        atividadesRecyclerView.setAdapter(programacaoAdapter);
       // programacaoViewModel.carregarAtividades();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public ProgramacaoAdapter getProgramacaoAdapter() {
        return programacaoAdapter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        programacaoAdapter.notifyDataSetChanged();
    }

    private ProgramacaoAdapter getExpandableAtividadesAdapter(){
        ArrayList<DiaEvento> diasEvento = new ArrayList<>();

        ArrayList<Atividade> atividadesPrimeiroDia = new ArrayList<>();
        atividadesPrimeiroDia.add(new Atividade("Projetos Ageis"));
        atividadesPrimeiroDia.add(new Atividade("Projetos Gerenciais"));
        atividadesPrimeiroDia.add(new Atividade("Projetos Errados"));

        DiaEvento dia1 = new DiaEvento("05/04/2019",atividadesPrimeiroDia);
        diasEvento.add(dia1);

        ArrayList<Atividade> atividadesSegundoDia = new ArrayList<>();
        atividadesSegundoDia.add(new Atividade("Fisica"));
        atividadesSegundoDia.add(new Atividade("Fisica mecanica"));
        atividadesSegundoDia.add(new Atividade("Entropia"));

        DiaEvento dia2 = new DiaEvento("06/04/2019",atividadesSegundoDia);
        diasEvento.add(dia2);

        ArrayList<Atividade> atividadesTerceiroDia = new ArrayList<>();
        atividadesTerceiroDia.add(new Atividade("Docker"));
        atividadesTerceiroDia.add(new Atividade("IA com docker"));
        atividadesTerceiroDia.add(new Atividade("Deep Docker"));

        DiaEvento dia3 = new DiaEvento("07/04/2019",atividadesTerceiroDia);
        diasEvento.add(dia3);

        ArrayList<Atividade> atividadesQuartoDia = new ArrayList<>();
        atividadesQuartoDia.add(new Atividade("Ingles"));
        atividadesQuartoDia.add(new Atividade("Chines"));
        atividadesQuartoDia.add(new Atividade("Bahasa"));

        DiaEvento dia4 = new DiaEvento("08/04/2019",atividadesQuartoDia);
        diasEvento.add(dia4);

        return new ProgramacaoAdapter(diasEvento);
    }
}
