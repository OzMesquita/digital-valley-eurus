package ufc.russas.encontrosuniversitarios.view.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.ActivityCriteria;
import ufc.russas.encontrosuniversitarios.view.fragment.CriteriaListener;
import ufc.russas.encontrosuniversitarios.view.viewholder.ActivityEvaluationViewHolder;

import java.util.List;

public class ActivityEvaluationAdapter extends RecyclerView.Adapter<ActivityEvaluationViewHolder> {
    private List<ActivityCriteria> activityCriteria;
    private CriteriaListener criteriaListener;

    public ActivityEvaluationAdapter(List<ActivityCriteria> activityCriteria, CriteriaListener listener){
        this.activityCriteria = activityCriteria;
        this.criteriaListener = listener;
    }

    @NonNull
    @Override
    public ActivityEvaluationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_criterios,parent,false);
        return new ActivityEvaluationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityEvaluationViewHolder holder, int position) {
        Log.i("oiii", ""+position);
        holder.bind(activityCriteria.get(position), criteriaListener);

    }

    @Override
    public int getItemCount() {
        return activityCriteria.size();
    }
}
