package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.databinding.FragmentAtividadeDadosBinding;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.helper.FormatadorData;
import com.example.encontrosuniversitarios.viewmodel.AtividadeDadosViewModel;

import org.joda.time.DateTime;

public class AtividadeDadosFragment extends Fragment {
    private static final String ATIVIDADE = "atividade";
    private static final String COORDENADOR = "coordenador";

    private AtividadeDadosViewModel atividadeDadosViewModel;
    private Button iniciarFinalizarAtividade;
    private Button avaliarAtividade;
    private TextView estadoAtividade;
    private View corEstado;
    private TextView horarioIniciado;
    private TextView horarioFinalizado;

    private boolean coordenador;
    private boolean avaliador;

    public AtividadeDadosFragment() {
        // Required empty public constructor
    }

    public static AtividadeDadosFragment newInstance(Atividade atividade,boolean coordenador) {
        AtividadeDadosFragment fragment = new AtividadeDadosFragment();
        Bundle args = new Bundle();
        args.putParcelable(ATIVIDADE,atividade);
        args.putBoolean(COORDENADOR,coordenador);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MySharedPreferences preferences = MySharedPreferences.getInstance(getContext());
        avaliador = preferences.getUserAccessLevel() == 2;
        if (getArguments() != null) {
            atividadeDadosViewModel = ViewModelProviders.of(this).get(AtividadeDadosViewModel.class);
            Atividade atividade = getArguments().getParcelable(ATIVIDADE);
            atividadeDadosViewModel.init(atividade);
            this.coordenador = getArguments().getBoolean(COORDENADOR);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentAtividadeDadosBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_atividade_dados,container,false);
        binding.setAtividade(atividadeDadosViewModel.getAtividade().getValue());
        binding.setFormatador(new FormatadorData());
        binding.setHorarioInicial(atividadeDadosViewModel.getHorarioInicio().getValue());
        binding.setHorarioFinal(atividadeDadosViewModel.getHorarioFinal().getValue());
        TextView horarioDataAtividade = binding.getRoot().findViewById(R.id.horario_data_atividade);
        horarioDataAtividade.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicialPrevisto()));
        estadoAtividade = binding.getRoot().findViewById(R.id.estado_atividade);

        corEstado = binding.getRoot().findViewById(R.id.atividade_estado_cor);
        avaliarAtividade = binding.getRoot().findViewById(R.id.avaliar_atividade);
        horarioIniciado = binding.getRoot().findViewById(R.id.horario_iniciado);
        horarioFinalizado = binding.getRoot().findViewById(R.id.horario_finalizado);
        iniciarFinalizarAtividade = binding.getRoot().findViewById(R.id.iniciar_finalizar_atividade);
        if(!coordenador){
            iniciarFinalizarAtividade.setVisibility(View.GONE);
        }else{
            iniciarFinalizarAtividade.setVisibility(View.VISIBLE);
        }
        configurarIniciarFinalizarAtividade();
        iniciarHorarios();
        atividadeDadosViewModel.getHorarioInicio().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioInicio) {
                configurarIniciarFinalizarAtividade();
                horarioIniciado.setText(FormatadorData.formatarDataHorario(horarioInicio));
            }
        });

        atividadeDadosViewModel.getHorarioFinal().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioFinal) {
                horarioFinalizado.setText(FormatadorData.formatarDataHorario(horarioFinal));
                configurarIniciarFinalizarAtividade();
            }
        });

        iniciarFinalizarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividadeDadosViewModel.alterarHorarioAtividade(getContext());

            }
        });
        if(!avaliador){
            avaliarAtividade.setVisibility(View.GONE);
        }
        avaliarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                transaction.replace(R.id.fragment_container,AvaliacaoAtividadeFragment.newInstance(atividadeDadosViewModel.getAtividade().getValue()));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return binding.getRoot();
    }

    private void iniciarHorarios(){
        if(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicio()!=null){
            horarioIniciado.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicio()));
        }
        if(atividadeDadosViewModel.getAtividade().getValue().getHorarioFinal()!=null){
            horarioFinalizado.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioFinal()));
        }
    }

    private void configurarAtividadeNaoIniciada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_green_button);
        iniciarFinalizarAtividade.setText(R.string.iniciar_atividade);
    }

    private void configurarAtividadeIniciada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_red_button);
        iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
    }

    private void configurarAtividadeFinalizada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_gray_buttom);
        iniciarFinalizarAtividade.setText(R.string.atividade_finalizada);
    }

    private void configurarIniciarFinalizarAtividade(){
        corEstado.setBackgroundColor(selecionarCorEstadoAtividade(atividadeDadosViewModel.getAtividade().getValue().getEstado()));
        iniciarFinalizarAtividade.setBackgroundColor(selecionarCorEstadoAtividade(atividadeDadosViewModel.getAtividade().getValue().getEstado()));
        switch (atividadeDadosViewModel.getAtividade().getValue().getEstado()){
            case Atividade.INICIADA:
                configurarAtividadeIniciada();
                break;
            case Atividade.NAO_INICIADA:
                configurarAtividadeNaoIniciada();
                break;
            case Atividade.FINALIZADA:
                configurarAtividadeFinalizada();
        }
    }

    private int selecionarCorEstadoAtividade(String estado){
        int cor = ContextCompat.getColor(getContext(), R.color.future_activity);
        switch (estado){
            case Atividade.INICIADA:
                cor = ContextCompat.getColor(getContext(), R.color.started_activity);
                break;
            case Atividade.FINALIZADA:
                cor = ContextCompat.getColor(getContext(), R.color.finished_activity);
                break;
            case Atividade.NAO_INICIADA:
                cor = ContextCompat.getColor(getContext(), R.color.future_activity);
                break;
        }
        return cor;
    }

}
