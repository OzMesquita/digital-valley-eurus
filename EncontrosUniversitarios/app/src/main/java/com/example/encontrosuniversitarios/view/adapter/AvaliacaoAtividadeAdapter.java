package com.example.encontrosuniversitarios.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.AvaliacaoAtividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.view.viewholder.AtividadeViewHolder;
import com.example.encontrosuniversitarios.view.viewholder.AvaliacaoAtividadeViewHolder;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import java.util.List;

public class AvaliacaoAtividadeAdapter extends RecyclerView.Adapter<AvaliacaoAtividadeViewHolder> {
    private List<CriterioAtividade> criterioAtividades;

    public AvaliacaoAtividadeAdapter(List<CriterioAtividade> criterioAtividades){
        this.criterioAtividades = criterioAtividades;
    }

    @NonNull
    @Override
    public AvaliacaoAtividadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_criterios,parent,false);
        return new AvaliacaoAtividadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvaliacaoAtividadeViewHolder holder, int position) {
        holder.bind(criterioAtividades.get(position));

    }

    @Override
    public int getItemCount() {
        return criterioAtividades.size();
    }
}
