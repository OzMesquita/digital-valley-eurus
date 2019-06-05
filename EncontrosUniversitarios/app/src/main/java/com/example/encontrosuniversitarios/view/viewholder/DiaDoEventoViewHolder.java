package com.example.encontrosuniversitarios.view.viewholder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class DiaDoEventoViewHolder extends GroupViewHolder {
    private TextView diaDoEvento;
    private TextView numeroItems;
    private ImageView arrow;
    private View divider;
    private boolean isItemClicked;
    public DiaDoEventoViewHolder(View itemView) {
        super(itemView);
        diaDoEvento = itemView.findViewById(R.id.text_view_dia_evento);
        arrow = itemView.findViewById(R.id.arrow);
        numeroItems = itemView.findViewById(R.id.item_count_dia_evento);
        divider = itemView.findViewById(R.id.divider);
    }

    public void bind(String diaEvento, int itemCount){
        this.diaDoEvento.setText(diaEvento);
        this.numeroItems.setText(String.valueOf(itemCount));
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
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
