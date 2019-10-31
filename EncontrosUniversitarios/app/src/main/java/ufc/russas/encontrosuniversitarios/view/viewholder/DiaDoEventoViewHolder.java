package ufc.russas.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import ufc.russas.encontrosuniversitarios.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class DiaDoEventoViewHolder extends GroupViewHolder {
    private TextView diaDoEvento;
    private ImageView arrow;
    private TextView numeroAtividades;
    public DiaDoEventoViewHolder(View itemView) {
        super(itemView);
        diaDoEvento = itemView.findViewById(R.id.text_view_dia_evento);
        arrow = itemView.findViewById(R.id.arrow);
        numeroAtividades = itemView.findViewById(R.id.numero_atividades_dia);
    }

    public void bind(String diaEvento,int numeroAtividades){
        this.diaDoEvento.setText(diaEvento);
        this.numeroAtividades.setText(String.valueOf(numeroAtividades));
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.startAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.startAnimation(rotate);

    }
}
