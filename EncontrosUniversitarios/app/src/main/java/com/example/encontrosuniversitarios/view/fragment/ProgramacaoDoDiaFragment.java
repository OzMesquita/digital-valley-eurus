package com.example.encontrosuniversitarios.view.fragment;

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

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAbasAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class ProgramacaoDoDiaFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoAbasAdapter programacaoAbasAdapter;
    private TabLayout tabLayout;
    private SearchView searchView;
    private ProgramacaoViewModel programacaoViewModel;
    private List<Integer> nomesEstadoAtividade;
    private ProgressBar diaProgress;
    private ViewPager viewPager;

    public ProgramacaoDoDiaFragment(){
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
        View view = inflater.inflate(R.layout.fragment_programacao_do_dia,container,false);
        programacaoAbasAdapter = new ProgramacaoAbasAdapter(getFragmentManager());
        programacaoViewModel = ViewModelProviders.of(this).get(ProgramacaoViewModel.class);

        diaProgress = view.findViewById(R.id.dia_progress);
        programacaoViewModel.getAtividadesDoDia().observe(this, new Observer<List<List<Atividade>>>() {
            @Override
            public void onChanged(List<List<Atividade>> lists) {
                for (int i = 0; i < 3; i++) {
                    AtividadesFragment atividadesFragment = AtividadesFragment.newInstance(lists.get(i), i);
                    if(programacaoAbasAdapter.getCount() < 3){
                        programacaoAbasAdapter.addFragment(atividadesFragment, getString(nomesEstadoAtividade.get(i)));
                    }
                }
                programacaoAbasAdapter.notifyDataSetChanged();
            }
        });

        viewPager = view.findViewById(R.id.programacao_do_dia_view_pager);
        viewPager.setAdapter(programacaoAbasAdapter);

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
        programacaoViewModel.carregarAtividadesDoDia(new AtividadesListener() {
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
    public Filterable getProgramacaoAdapter() {
        AtividadesFragment programacaoFragment = (AtividadesFragment) this.programacaoAbasAdapter.getItem(tabLayout.getSelectedTabPosition());
        return programacaoFragment.getProgramacaoAdapter();
    }
}
