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
    private Atividade atividade;

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
            atividade = getArguments().getParcelable(ATIVIDADE);
            atividadeDadosViewModel.init(atividade);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividade_dados, container, false);
        TextView nomeAtv = view.findViewById(R.id.nome_atividade);
        nomeAtv.setText(atividade.getNome());
        TextView horarioDataAtividade = view.findViewById(R.id.horario_data_atividade);
        horarioDataAtividade.setText(FormatadorData.formatarDataHorario(atividade.getHorarioInicialPrevisto()));
        final TextView estadoAtividade = view.findViewById(R.id.estado_atividade);
        estadoAtividade.setText(atividade.getEstado());
        final View corEstado = view.findViewById(R.id.atividade_estado_cor);
        corEstado.setBackgroundColor(selecionarCorEstadoAtividade(atividade.getEstado()));
        TextView localAtividade = view.findViewById(R.id.local_atividade);
        localAtividade.setText(atividade.getLocal().getNome());
        TextView descricaoAtividade = view.findViewById(R.id.descricao_atividade);
        descricaoAtividade.setText(atividade.getDescricao());
        TextView apresentadorAtividade = view.findViewById(R.id.apresentador_atividade);
        apresentadorAtividade.setText(atividade.getApresentador().getNome());
        final TextView horarioIniciado = view.findViewById(R.id.horario_iniciado);
        final TextView horarioFinalizado = view.findViewById(R.id.horario_finalizado);
        final Button iniciarFinalizarAtividade = view.findViewById(R.id.iniciar_finalizar_atividade);

        atividadeDadosViewModel.getHorarioInicio().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioInicio) {
                estadoAtividade.setText(R.string.started_activity);
                corEstado.setBackgroundColor(getResources().getColor(R.color.started_activity));
                iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_red_button);
                iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
                horarioIniciado.setText(FormatadorData.formatarDataHorario(horarioInicio));
            }
        });

        atividadeDadosViewModel.getHorarioFinal().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioFinal) {
                horarioFinalizado.setText(FormatadorData.formatarDataHorario(horarioFinal));
                corEstado.setBackgroundColor(getResources().getColor(R.color.finished_activity));
                estadoAtividade.setText(R.string.finished_activity);
                iniciarFinalizarAtividade.setBackgroundResource(R.color.colorBlue);
                iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
            }
        });

        iniciarFinalizarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividadeDadosViewModel.alterarEstadoAtividade();
            }
        });
        return view;
    }

    private int selecionarCorEstadoAtividade(String estado){
        int cor = getResources().getColor(R.color.future_activity);
        Log.i("Estado",estado);
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
