package com.example.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.fragment.AtividadeDadosFragment;
import com.example.encontrosuniversitarios.view.helper.FormatadorData;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class AtividadeViewHolder extends ChildViewHolder {
    private TextView nomeAtividadeTextView;
    private TextView horarioAtividadeTextView;
    private TextView localAtividadeTextView;
    public AtividadeViewHolder(View itemView) {
        super(itemView);
        nomeAtividadeTextView = itemView.findViewById(R.id.nome_atividade_item_list);
        horarioAtividadeTextView = itemView.findViewById(R.id.horario_atividade);
        localAtividadeTextView = itemView.findViewById(R.id.local_atividade);
    }

    public void bind(final Atividade atividade){
        this.nomeAtividadeTextView.setText(atividade.getNome());
        this.horarioAtividadeTextView.setText(FormatadorData.formatarDataHorario(atividade.getHorarioInicialPrevisto()));
        this.localAtividadeTextView.setText(atividade.getLocal().getNome());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtividadeDadosFragment fragment = AtividadeDadosFragment.newInstance(atividade);
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
