package com.example.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class AtividadeViewHolder extends ChildViewHolder {
    private TextView nomeAtividadeTextView;
    public AtividadeViewHolder(View itemView) {
        super(itemView);
        nomeAtividadeTextView = itemView.findViewById(R.id.nome_atividade_item_list);
    }

    public void bind(Atividade atividade){
        this.nomeAtividadeTextView.setText(atividade.getNome());
    }
}
