package ufc.russas.encontrosuniversitarios.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.viewholder.ActivityViewHolder;
import ufc.russas.encontrosuniversitarios.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodaysScheduleAdapter extends RecyclerView.Adapter<ActivityViewHolder> implements Filterable {
    private List<Activity> activities;
    private List<Activity> atividadesFiltradas;
    private List<String> atividadesCoordenador;
    private boolean isAvaliacao;

    public TodaysScheduleAdapter(List<Activity> activities, Set<String> idsAtividadesCoordenador, boolean isAvaliacao){
        this.activities = activities;
        this.atividadesFiltradas = activities;
        this.isAvaliacao = isAvaliacao;
        if(idsAtividadesCoordenador != null) {
            this.atividadesCoordenador = new ArrayList<>();
            this.atividadesCoordenador.addAll(idsAtividadesCoordenador);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividade_item_list,parent,false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        holder.bind(atividadesFiltradas.get(position),isAvaliacao ? false : atividadesCoordenador == null ? false :
                atividadesCoordenador.contains(String.valueOf(atividadesFiltradas.get(position).getId())),
                isAvaliacao ? atividadesCoordenador.contains(String.valueOf(atividadesFiltradas.get(position).getId())) : false);
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
                    atividadesFiltradas = activities;
                }else{
                    List<Activity> listaFiltrada = new ArrayList<>();
                    for(Activity activity : activities){
                        if(activity.getName().toLowerCase().contains(textFilter.toLowerCase())){
                            listaFiltrada.add(activity);
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
                atividadesFiltradas = (ArrayList<Activity>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
