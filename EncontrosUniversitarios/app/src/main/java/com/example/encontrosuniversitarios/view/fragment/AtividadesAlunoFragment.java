package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.helper.QRCodeHelper;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoAdapter;
import com.example.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import com.example.encontrosuniversitarios.viewmodel.AtividadesAlunoViewModel;
import com.example.encontrosuniversitarios.viewmodel.ProgramacaoViewModel;

import java.util.List;

public class AtividadesAlunoFragment extends Fragment  implements ProgramacaoListInterface {

    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private AtividadesAlunoViewModel atividadesAlunoViewModel;
    private RecyclerView recyclerView;
    private TextView txtUlmimaSalaCheckin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades_aluno, container, false);
        Button btnGenerateQRCode = view.findViewById(R.id.btnGenerateQRCode);
        atividadesAlunoViewModel = ViewModelProviders.of(this).get(AtividadesAlunoViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_aluno);
        txtUlmimaSalaCheckin = view.findViewById(R.id.salaUltimoCheckin);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        btnGenerateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences p = MySharedPreferences.getInstance(getContext());
                QRCodeHelper qrCodeHelper = new QRCodeHelper(500,500);
                qrCodeHelper.generateUserQRCodeAlertDialog(getContext(),"EURUSSAS-"+p.getUserId()+"-"+p.getUserName());
            }
        });
        atividadesAlunoViewModel.getAtividades().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, null,false);
                recyclerView.setAdapter(programacaoDoDiaAdapter);
            }
        });
        atividadesAlunoViewModel.carregarAtividades(getContext());

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
