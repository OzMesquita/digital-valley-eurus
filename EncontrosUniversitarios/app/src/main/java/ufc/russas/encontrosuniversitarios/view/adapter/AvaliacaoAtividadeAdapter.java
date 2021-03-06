package ufc.russas.encontrosuniversitarios.view.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.view.fragment.CriterioListener;
import ufc.russas.encontrosuniversitarios.view.viewholder.AvaliacaoAtividadeViewHolder;

import java.util.List;

public class AvaliacaoAtividadeAdapter extends RecyclerView.Adapter<AvaliacaoAtividadeViewHolder> {
    private List<CriterioAtividade> criterioAtividades;
    private CriterioListener criterioListener;

    public AvaliacaoAtividadeAdapter(List<CriterioAtividade> criterioAtividades, CriterioListener listener){
        this.criterioAtividades = criterioAtividades;
        this.criterioListener = listener;
    }

    @NonNull
    @Override
    public AvaliacaoAtividadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_criterios,parent,false);
        return new AvaliacaoAtividadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvaliacaoAtividadeViewHolder holder, int position) {
        Log.i("oiii", ""+position);
        holder.bind(criterioAtividades.get(position),criterioListener);

    }

    @Override
    public int getItemCount() {
        return criterioAtividades.size();
    }
}
