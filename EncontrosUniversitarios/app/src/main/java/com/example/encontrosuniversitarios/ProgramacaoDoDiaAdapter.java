package com.example.encontrosuniversitarios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.model.Atividade;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramacaoDoDiaAdapter extends RecyclerView.Adapter<AtividadeViewHolder> implements Filterable {
    private List<Atividade> atividades;
    private List<Atividade> atividadesFiltradas;

    public ProgramacaoDoDiaAdapter(List<Atividade> atividades){
        this.atividades = atividades;
        this.atividadesFiltradas = atividades;
    }

    @NonNull
    @Override
    public AtividadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividade_item_list,parent,false);

        return new AtividadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeViewHolder holder, int position) {
        holder.bind(atividadesFiltradas.get(position));
    }

    @Override
    public int getItemCount() {
        return atividadesFiltradas.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String textFilter = constraint.toString();
                Log.i("Filter",textFilter);
                if(textFilter.isEmpty()){
                    atividadesFiltradas = atividades;
                }else{
                    List<Atividade> listaFiltrada = new ArrayList<>();
                    for(Atividade atividade:atividades){
                        if(atividade.getNome().toLowerCase().contains(textFilter.toLowerCase())){
                            listaFiltrada.add(atividade);
                        }
                    }
                    atividadesFiltradas = listaFiltrada;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = atividadesFiltradas;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                atividadesFiltradas = (ArrayList<Atividade>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}