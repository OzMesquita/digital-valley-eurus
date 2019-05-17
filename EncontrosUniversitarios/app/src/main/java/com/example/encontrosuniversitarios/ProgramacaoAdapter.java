package com.example.encontrosuniversitarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoAdapter extends ExpandableRecyclerViewAdapter<DiaDoEventoViewHolder,AtividadeViewHolder> implements Filterable {
    private List<ExpandableGroup> filteredGroups;
    private List<ExpandableGroup> originalList;
    public ProgramacaoAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
        filteredGroups = new ArrayList<>();
        originalList = new ArrayList<>();
        for(ExpandableGroup group:getGroups()){
            ExpandableGroup groupCopy = new ExpandableGroup(group.getTitle(),new ArrayList<>());
            groupCopy.getItems().addAll(group.getItems());
            originalList.add(groupCopy);
            ExpandableGroup groupCopy2 = new ExpandableGroup(group.getTitle(),new ArrayList<>());
            groupCopy2.getItems().addAll(group.getItems());
            filteredGroups.add(groupCopy2);
        }
    }

    @Override
    public DiaDoEventoViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_diaevento,parent,false);

        return new DiaDoEventoViewHolder(v);
    }

    @Override
    public AtividadeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividade_item_list,parent,false);

        return new AtividadeViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(AtividadeViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Atividade atividade = (Atividade) group.getItems().get(childIndex);
        holder.bind(atividade);
    }

    @Override
    public void onBindGroupViewHolder(DiaDoEventoViewHolder holder, int flatPosition, ExpandableGroup group) {
        final DiaEvento diaEvento = (DiaEvento) group;
        holder.bind(diaEvento.getTitle());
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String nomeAtividade = constraint.toString();
                if(nomeAtividade.isEmpty()){
                    for(int i=0;i<originalList.size();i++){
                        getGroups().get(i).getItems().clear();
                        getGroups().get(i).getItems().addAll(originalList.get(i).getItems());
                        filteredGroups.get(i).getItems().clear();
                        filteredGroups.get(i).getItems().addAll(originalList.get(i).getItems());
                    }

                }else{
                    List<ExpandableGroup> filteredList = new ArrayList<>();
                    for(ExpandableGroup diaEventoGroup:originalList){
                        DiaEvento diaEventoGroupFiltrado = new DiaEvento(diaEventoGroup.getTitle(),new ArrayList<Atividade>());
                        for(Object item:diaEventoGroup.getItems()){
                            Atividade atividade = (Atividade) item;
                            if(atividade.getNome().toLowerCase().contains(nomeAtividade.toLowerCase())){
                                diaEventoGroupFiltrado.getItems().add(atividade);
                            }
                        }
                        filteredList.add(diaEventoGroupFiltrado);
                    }
                    filteredGroups = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredGroups;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredGroups = (ArrayList<ExpandableGroup>)results.values;
                for(int i=0;i<filteredGroups.size();i++){
                    getGroups().get(i).getItems().clear();
                    getGroups().get(i).getItems().addAll(filteredGroups.get(i).getItems());

                }
                notifyDataSetChanged();
            }
        };
    }

}