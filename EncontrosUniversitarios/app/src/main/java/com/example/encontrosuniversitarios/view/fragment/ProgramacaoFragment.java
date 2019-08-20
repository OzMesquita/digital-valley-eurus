package com.example.encontrosuniversitarios.view.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.SearchView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoAdapter programacaoAdapter;
    private SearchView searchView;
    private ProgramacaoViewModel programacaoViewModel;
    private List<DiaEvento> diasEventos;
    public ProgramacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_programacao, container, false);
        programacaoViewModel = ViewModelProviders.of(this).get(ProgramacaoViewModel.class);
        atividadesRecyclerView = view.findViewById(R.id.programacao_recycler_view);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        programacaoViewModel.getDiasEvento().observe(this, new Observer<List<DiaEvento>>() {
            @Override
            public void onChanged(List<DiaEvento> diaEventos) {
                diasEventos = diaEventos;
                programacaoAdapter = new ProgramacaoAdapter(diasEventos, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities());
                atividadesRecyclerView.setAdapter(programacaoAdapter);
            }
        });
        programacaoViewModel.carregarAtividades();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        updateSearchViewFragment();
    }

    @Override
    public void updateSearchViewFragment() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                getProgramacaoAdapter().getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getProgramacaoAdapter().getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public ProgramacaoAdapter getProgramacaoAdapter() {
        return programacaoAdapter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
