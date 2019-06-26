package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.Local;
import com.example.encontrosuniversitarios.model.Sala;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class RealizarFrequenciaFragment extends Fragment implements ProgramacaoListInterface {
    private List<Atividade> atividades;
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    public RealizarFrequenciaFragment() {
        // Required empty public constructor
        atividades = new ArrayList<>();
        atividades.add(new Atividade("Tdd", DateTime.now(),new Local("Sala de Video", new Sala(1)), new Usuario("Mariana Carvalho")));
//        atividades.add(new Atividade("Testes",DateTime.now(),new Local("Sala de Video")));
//        atividades.add(new Atividade("Java",DateTime.now(),new Local("Hall de Entrada")));
//        atividades.add(new Atividade("Politica",DateTime.now(),new Local("Auditorio")));
//        atividades.add(new Atividade("Pacce",DateTime.now(),new Local("Sala do Pacce")));
//        atividades.add(new Atividade("Culinaria",DateTime.now(),new Local("Cantina")));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.atividades_frequencia);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades);
        recyclerView.setAdapter(programacaoDoDiaAdapter);
        return view;
    }


    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
