package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.helper.FormatadorData;
import com.example.encontrosuniversitarios.viewmodel.AtividadeDadosViewModel;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

public class AtividadeDadosFragment extends Fragment {
    private static final String ATIVIDADE = "atividade";

    private AtividadeDadosViewModel atividadeDadosViewModel;
    private Button iniciarFinalizarAtividade;
    private TextView estadoAtividade;
    private View corEstado;
    private TextView horarioIniciado;
    private TextView horarioFinalizado;

    public AtividadeDadosFragment() {
        // Required empty public constructor
    }

    public static AtividadeDadosFragment newInstance(Atividade atividade) {
        AtividadeDadosFragment fragment = new AtividadeDadosFragment();
        Bundle args = new Bundle();
        args.putParcelable(ATIVIDADE,atividade);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            atividadeDadosViewModel = ViewModelProviders.of(this).get(AtividadeDadosViewModel.class);
            Atividade atividade = getArguments().getParcelable(ATIVIDADE);
            atividadeDadosViewModel.init(atividade);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividade_dados, container, false);
        TextView nomeAtv = view.findViewById(R.id.nome_atividade);
        nomeAtv.setText(atividadeDadosViewModel.getAtividade().getValue().getNome());
        TextView horarioDataAtividade = view.findViewById(R.id.horario_data_atividade);
        horarioDataAtividade.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicialPrevisto()));
        estadoAtividade = view.findViewById(R.id.estado_atividade);
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        corEstado = view.findViewById(R.id.atividade_estado_cor);
        TextView localAtividade = view.findViewById(R.id.local_atividade);
        localAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getLocal().getNome());
        TextView descricaoAtividade = view.findViewById(R.id.descricao_atividade);
        descricaoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getDescricao());
        TextView apresentadorAtividade = view.findViewById(R.id.apresentador_atividade);
        apresentadorAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getApresentador().getNome());
        horarioIniciado = view.findViewById(R.id.horario_iniciado);
        horarioFinalizado = view.findViewById(R.id.horario_finalizado);
        iniciarFinalizarAtividade = view.findViewById(R.id.iniciar_finalizar_atividade);
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
                atividadeDadosViewModel.alterarHorarioAtividade();
            }
        });
        return view;
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
        int cor = getResources().getColor(R.color.future_activity);
        switch (estado){
            case Atividade.INICIADA:
                cor = getResources().getColor(R.color.started_activity);
                break;
            case Atividade.FINALIZADA:
                cor = getResources().getColor(R.color.finished_activity);
                break;
            case Atividade.NAO_INICIADA:
                cor = getResources().getColor(R.color.future_activity);
                break;
        }
        return cor;
    }

}
