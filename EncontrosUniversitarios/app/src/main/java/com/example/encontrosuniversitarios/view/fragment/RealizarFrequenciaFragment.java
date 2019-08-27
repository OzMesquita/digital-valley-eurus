package com.example.encontrosuniversitarios.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.helper.ScanHelper;
import com.example.encontrosuniversitarios.viewmodel.RealizarFrequenciaViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class RealizarFrequenciaFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private RealizarFrequenciaViewModel realizarFrequenciaViewModel;
    private RecyclerView recyclerView;
    private TextView txtSala;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
        Button btnReadQRCode = view.findViewById(R.id.btnReadQRCode);
        realizarFrequenciaViewModel = ViewModelProviders.of(this).get(RealizarFrequenciaViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_frequencia);
        txtSala = view.findViewById(R.id.sala);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        btnReadQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanHelper scanHelper = new ScanHelper(0, getActivity(),"Ler matricula");
                scanHelper.showScan();
            }
        });

        realizarFrequenciaViewModel.getAtividadesFrequencia().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                if(atividades!=null && atividades.size()>=1){
                    txtSala.setText(getContext().getResources().getText(R.string.realizar_frequencia).toString()+" "+atividades.get(0).getLocal().getSala().getNumero());
                }
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
