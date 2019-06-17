package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;

import java.util.ArrayList;
import java.util.List;

public class RealizarFrequenciaFragment extends Fragment implements ProgramacaoListInterface {

    public RealizarFrequenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
    }


    @Override
    public Filterable getProgramacaoAdapter() {
        List<DiaEvento> diaEventos = new ArrayList<>();
        return new ProgramacaoAdapter(diaEventos);
    }
}
