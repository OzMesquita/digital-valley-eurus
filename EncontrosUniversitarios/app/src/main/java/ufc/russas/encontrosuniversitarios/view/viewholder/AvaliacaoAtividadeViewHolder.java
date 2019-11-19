package ufc.russas.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.view.fragment.CriterioListener;

public class AvaliacaoAtividadeViewHolder extends RecyclerView.ViewHolder {
    private TextView txtCriterio;
    private TextView txtCategoria;
    private CriterioAtividade criterioAtividade;
    private NumberPicker numberPicker;

    public AvaliacaoAtividadeViewHolder(View itemView) {
        super(itemView);
        txtCriterio = itemView.findViewById(R.id.criterio);
        txtCategoria = itemView.findViewById(R.id.categoria);
        numberPicker = itemView.findViewById(R.id.nota);
    }

    public void bind(final CriterioAtividade criterio, final CriterioListener listener){
        this.criterioAtividade = criterio;
        txtCriterio.setText(criterioAtividade.getCriterio());
        txtCategoria.setText(criterioAtividade.getCategoria());
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setValue(criterio.getNota());
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                criterioAtividade.setNota(newVal);
                listener.onNotaChanged();
            }
        });
    }

}