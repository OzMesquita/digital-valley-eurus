package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.view.adapter.AvaliacaoAtividadeAdapter;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoAtividadeFragment extends Fragment {
    private List<NumberPicker> ns;
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    public AvaliacaoAtividadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avaliacao_atividade, container, false);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        final RecyclerView criteriosRec = view.findViewById(R.id.lista_criterios);
        avaliacaoAtividadeViewModel.getCriterios().observe(this, new Observer<List<CriterioAtividade>>() {
            @Override
            public void onChanged(List<CriterioAtividade> criterioAtividades) {
                if(criterioAtividades!=null){
                    AvaliacaoAtividadeAdapter adapter = new AvaliacaoAtividadeAdapter(criterioAtividades);
                    criteriosRec.setAdapter(adapter);
                }
            }
        });
        avaliacaoAtividadeViewModel.listarCriterios();
        return view;
    }
}
