package ufc.russas.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.ActivityCriteria;
import ufc.russas.encontrosuniversitarios.view.fragment.CriteriaListener;

public class ActivityEvaluationViewHolder extends RecyclerView.ViewHolder {
    private TextView txtCriterio;
    private TextView txtCategoria;
    private ActivityCriteria activityCriteria;
    private NumberPicker numberPicker;

    public ActivityEvaluationViewHolder(View itemView) {
        super(itemView);
        txtCriterio = itemView.findViewById(R.id.criterio);
        txtCategoria = itemView.findViewById(R.id.categoria);
        numberPicker = itemView.findViewById(R.id.nota);
    }

    public void bind(final ActivityCriteria criterio, final CriteriaListener listener){
        this.activityCriteria = criterio;
        txtCriterio.setText(activityCriteria.getCriterio());
        txtCategoria.setText(activityCriteria.getCategoria());
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setValue(criterio.getNota());
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                activityCriteria.setNota(newVal);
                listener.onNotaChanged();
            }
        });
    }

}