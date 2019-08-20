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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.Local;
import com.example.encontrosuniversitarios.model.Sala;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.viewmodel.RealizarFrequenciaViewModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class RealizarFrequenciaFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private RealizarFrequenciaViewModel realizarFrequenciaViewModel;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
        realizarFrequenciaViewModel = ViewModelProviders.of(this).get(RealizarFrequenciaViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_frequencia);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        realizarFrequenciaViewModel.getAtividadesFrequencia().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities());
                recyclerView.setAdapter(programacaoDoDiaAdapter);
            }
        });
        realizarFrequenciaViewModel.carregarAtividadesFrequencia(getContext());
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
