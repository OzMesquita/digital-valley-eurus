package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.helper.FormatadorData;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

public class AtividadeDadosFragment extends Fragment {
    private static final String ATIVIDADE = "atividade";

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
            atividade = getArguments().getParcelable(ATIVIDADE);

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
        iniciarFinalizarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(atividade.atividadeIniciada()){
                    horarioFinalizado.setText(FormatadorData.formatarDataHorario(DateTime.now()));
                    corEstado.setBackgroundColor(getResources().getColor(R.color.finished_activity));
                    estadoAtividade.setText(R.string.finished_activity);
                }else if(!atividade.atividadeIniciada() && !atividade.atividadeFinalizada()){
                    atividade.iniciar();
                    estadoAtividade.setText(R.string.started_activity);
                    corEstado.setBackgroundColor(getResources().getColor(R.color.started_activity));
                    iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_red_button);
                    iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
                    horarioIniciado.setText(FormatadorData.formatarDataHorario(DateTime.now()));
                }else if(atividade.atividadeFinalizada()){
                    iniciarFinalizarAtividade.setAlpha(.5f);
                    iniciarFinalizarAtividade.setClickable(false);
                    iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
                }
            }
        });
        return view;
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
