package com.example.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.CriterioAtividade;

public class AvaliacaoAtividadeViewHolder extends RecyclerView.ViewHolder {
    private TextView txtCriterio;
    private TextView txtCategoria;
    private NumberPicker numberPicker;

    public AvaliacaoAtividadeViewHolder( View itemView) {
        super(itemView);
        txtCriterio = itemView.findViewById(R.id.criterio);
        txtCategoria = itemView.findViewById(R.id.categoria);
        numberPicker = itemView.findViewById(R.id.nota);
    }

    public void bind(CriterioAtividade criterioAtividade){
        txtCriterio.setText(criterioAtividade.getCriterio());
        txtCategoria.setText(criterioAtividade.getCategoria());
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
    }

}