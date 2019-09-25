package com.example.encontrosuniversitarios.view.fragment;

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
import android.widget.Button;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.viewmodel.AtividadesAlunoViewModel;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import java.util.List;


public class AtividadesProfessorFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades_professor, container, false);
        recyclerView = view.findViewById(R.id.atividades_professor);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

//        avaliacaoAtividadeViewModel.getAtividadesAvaliação().observe(this, new Observer<List<Atividade>>() {
//            @Override
//            public void onChanged(List<Atividade> atividades) {
//                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, null);
//                recyclerView.setAdapter(programacaoDoDiaAdapter);
//            }
//        });
//        avaliacaoAtividadeViewModel.carregarAtividades(getContext());

        avaliacaoAtividadeViewModel.getAtividadesAvaliação().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                if(atividades!=null && atividades.size()>=1){
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                    MySharedPreferences.getInstance(getContext()).setRoom(atividades.get(0).getLocal().getSala().getId());
                }
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities());
                recyclerView.setAdapter(programacaoDoDiaAdapter);
            }
        });
        avaliacaoAtividadeViewModel.carregarAtividades(getContext());
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        return view;
    }


    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
