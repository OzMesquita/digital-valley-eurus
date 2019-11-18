package ufc.russas.encontrosuniversitarios.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.viewholder.ActivityViewHolder;
import ufc.russas.encontrosuniversitarios.view.viewholder.EventDayViewHolder;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.EventDay;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScheduleAdapter extends ExpandableRecyclerViewAdapter<EventDayViewHolder, ActivityViewHolder> implements Filterable {
    private List<ExpandableGroup> filteredGroups;
    private List<ExpandableGroup> originalList;
    private List<String> atividadesCoordenador;
    private  boolean isAvaliacao;
    public ScheduleAdapter(List<? extends ExpandableGroup> groups, Set<String> atividades, boolean isAvaliacao) {
        super(groups);
        filteredGroups = new ArrayList<>();
        originalList = new ArrayList<>();
        this.isAvaliacao = isAvaliacao;
        for(ExpandableGroup group:getGroups()){
            ExpandableGroup groupCopy = new ExpandableGroup(group.getTitle(),new ArrayList<>());
            groupCopy.getItems().addAll(group.getItems());
            originalList.add(groupCopy);
            ExpandableGroup groupCopy2 = new ExpandableGroup(group.getTitle(),new ArrayList<>());
            groupCopy2.getItems().addAll(group.getItems());
            filteredGroups.add(groupCopy2);
        }
        if(atividades!=null) {
            this.atividadesCoordenador = new ArrayList<>();
            this.atividadesCoordenador.addAll(atividades);
        }
    }

    @Override
    public EventDayViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_diaevento,parent,false);

        return new EventDayViewHolder(v);
    }

    @Override
    public ActivityViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividade_item_list,parent,false);

        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(ActivityViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Activity activity = (Activity) group.getItems().get(childIndex);
        holder.bind(activity,atividadesCoordenador == null ? false : atividadesCoordenador.contains(String.valueOf(activity.getId())),
                isAvaliacao ? atividadesCoordenador.contains(String.valueOf(activity.getId())) : false);
    }

    @Override
    public void onBindGroupViewHolder(EventDayViewHolder holder, int flatPosition, ExpandableGroup group) {
        final EventDay eventDay = (EventDay) group;
        holder.bind(eventDay.getTitle(), eventDay.getItemCount());
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
                        EventDay eventDayGroupFiltrado = new EventDay(diaEventoGroup.getTitle(),new ArrayList<Activity>());
                        for(Object item:diaEventoGroup.getItems()){
                            Activity activity = (Activity) item;
                            if(activity.getName().toLowerCase().contains(nomeAtividade.toLowerCase())){
                                eventDayGroupFiltrado.getItems().add(activity);
                            }
                        }
                        filteredList.add(eventDayGroupFiltrado);
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