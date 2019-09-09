package com.example.encontrosuniversitarios.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.helper.QRCodeHelper;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
        Button btnReadQRCode = view.findViewById(R.id.btnReadQRCode);
        Button btnMatricula = view.findViewById(R.id.freq_matricula);
        realizarFrequenciaViewModel = ViewModelProviders.of(this).get(RealizarFrequenciaViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_frequencia);
        txtSala = view.findViewById(R.id.sala);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        btnReadQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanHelper scanHelper = new ScanHelper(0, getActivity(),getResources().getString(R.string.qrcode_title));
                scanHelper.showScan();
            }
        });

        btnMatricula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMatriculaDialog();
            }
        });

        realizarFrequenciaViewModel.getAtividadesFrequencia().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                if(atividades!=null && atividades.size()>=1){
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                    MySharedPreferences.getInstance(getContext()).setRoom(atividades.get(0).getLocal().getSala().getId());
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

    public void showMatriculaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View customLayout = getLayoutInflater().inflate(R.layout.matricula_dialog, null);
        Button getUser = customLayout.findViewById(R.id.get_user);
        Button frequencia = customLayout.findViewById(R.id.frequencia);
        final TextView userName = customLayout.findViewById(R.id.nome_usuario_matricula);
        final EditText matricula = customLayout.findViewById(R.id.matricula_frequencia);
        realizarFrequenciaViewModel.getUsuarioFrequencia().observe(this, new Observer<DadosFrequenciaUsuario>() {
            @Override
            public void onChanged(DadosFrequenciaUsuario usuario) {
                if(usuario!=null) {
                    userName.setText(usuario.getNome());
                }else{
                    userName.setText("");
                    matricula.setText("");
                }
            }
        });
        getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(matricula.getText().toString().length() == 6) {
                    realizarFrequenciaViewModel.buscarUsuarioPorMatricula(new ResponseListener() {
                        @Override
                        public void onSuccess(Object response) {
                            realizarFrequenciaViewModel.initDadosFrequencia((DadosFrequenciaUsuario) response);
                        }

                        @Override
                        public void onFailure(String message) {
                            matricula.setError("Não foi possível encontrar essa matrícula.");
                        }
                    },matricula.getText().toString());
                }else{
                    matricula.setError("Matrícula inválida");
                }
            }
        });
        frequencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(realizarFrequenciaViewModel.getUsuarioFrequencia().getValue() != null) {
                    realizarFrequenciaViewModel.realizarCheckInCheckOut(new CheckInCheckOutListener() {
                        @Override
                        public void onSuccess(String message) {
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCheckedInOnDifferentRoom(String message) {
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onInvalidQRCode(String message) {
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(String message) {
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }
                    },getContext());
                }else{
                    matricula.requestFocus();
                    Toast.makeText(getContext(),"Verifique a matrícula antes de realizar a frequência",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setView(customLayout);
        builder.show();
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
