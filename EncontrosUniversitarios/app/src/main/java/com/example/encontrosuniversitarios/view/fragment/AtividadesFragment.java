package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;

import java.util.ArrayList;
import java.util.List;

public class AtividadesFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private List<Atividade> atividades;

    public AtividadesFragment() {
        // Required empty public constructor
        atividades = new ArrayList<>();
        atividades.add(new Atividade("Testes com docker",null,null));
        atividades.add(new Atividade("Engenharia de software",null,null));
        atividades.add(new Atividade("Projeto da dengue",null,null));
        atividades.add(new Atividade("Engenharia civil aplicada",null,null));
        atividades.add(new Atividade("Flutter",null,null));
        atividades.add(new Atividade("Gerencia de projetos",null,null));
        atividades.add(new Atividade("Ciencia",null,null));
        atividades.add(new Atividade("Profissões",null,null));
        atividades.add(new Atividade("Karaoke",null,null));
        atividades.add(new Atividade("Engenharia de telecomunicações",null,null));
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


    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}