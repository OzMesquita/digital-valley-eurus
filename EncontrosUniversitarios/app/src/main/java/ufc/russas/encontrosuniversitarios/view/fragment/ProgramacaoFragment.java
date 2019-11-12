package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import ufc.russas.encontrosuniversitarios.R;

import ufc.russas.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
import ufc.russas.encontrosuniversitarios.model.DiaEvento;
import ufc.russas.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;

import java.util.List;

public class ProgramacaoFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoAdapter programacaoAdapter;
    private ProgramacaoViewModel programacaoViewModel;
    private ProgressBar progressBar;
    private List<DiaEvento> diasEventos;

    public ProgramacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_programacao, container, false);
        programacaoViewModel = ViewModelProviders.of(this).get(ProgramacaoViewModel.class);
        atividadesRecyclerView = view.findViewById(R.id.programacao_recycler_view);
        progressBar = view.findViewById(R.id.programacao_progress);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        programacaoViewModel.getDiasEvento().observe(this, new Observer<List<DiaEvento>>() {
            @Override
            public void onChanged(List<DiaEvento> diaEventos) {
                diasEventos = diaEventos;
                programacaoAdapter = new ProgramacaoAdapter(diasEventos, null, false);
                atividadesRecyclerView.setAdapter(programacaoAdapter);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        programacaoViewModel.carregarAtividades(new AtividadesListener() {
            @Override
            public void onLoading() {
                atividadesRecyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDone() {
                atividadesRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
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
