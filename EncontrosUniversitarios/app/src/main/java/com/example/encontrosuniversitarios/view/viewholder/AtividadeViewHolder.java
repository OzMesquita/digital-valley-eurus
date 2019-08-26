package com.example.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.fragment.AtividadeDadosFragment;
import com.example.encontrosuniversitarios.helper.FormatadorData;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class AtividadeViewHolder extends ChildViewHolder {
    private TextView nomeAtividadeTextView;
    private TextView nomeApresentador;
    private TextView horarioAtividadeTextView;
    private TextView localAtividadeTextView;
    private AtividadeDadosFragment fragment;
    public AtividadeViewHolder(View itemView) {
        super(itemView);
        nomeAtividadeTextView = itemView.findViewById(R.id.nome_atividade_item_list);
        nomeApresentador = itemView.findViewById(R.id.nome_apresentador);
        horarioAtividadeTextView = itemView.findViewById(R.id.horario_atividade);
        localAtividadeTextView = itemView.findViewById(R.id.local_atividade);
    }

    public void bind(final Atividade atividade, final boolean coordenador){
        this.nomeAtividadeTextView.setText(atividade.getNome());
        this.nomeApresentador.setText(atividade.getApresentador().getNome());
        this.horarioAtividadeTextView.setText(FormatadorData.formatarDataHorario(atividade.getHorarioInicialPrevisto()));
        this.localAtividadeTextView.setText(atividade.getLocal().getLocalSala());
        fragment = AtividadeDadosFragment.newInstance(atividade,coordenador);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
