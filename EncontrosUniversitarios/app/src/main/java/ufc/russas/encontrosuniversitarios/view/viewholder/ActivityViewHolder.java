package ufc.russas.encontrosuniversitarios.view.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.activity.ActivityDataActivity;
import ufc.russas.encontrosuniversitarios.helper.DataFormatter;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ActivityViewHolder extends ChildViewHolder {
    private TextView nomeAtividadeTextView;
    private TextView nomeApresentador;
    private TextView horarioAtividadeTextView;
    private TextView localAtividadeTextView;
    public ActivityViewHolder(View itemView) {
        super(itemView);
        nomeAtividadeTextView = itemView.findViewById(R.id.nome_atividade_item_list);
        nomeApresentador = itemView.findViewById(R.id.nome_apresentador);
        horarioAtividadeTextView = itemView.findViewById(R.id.horario_atividade);
        localAtividadeTextView = itemView.findViewById(R.id.local_atividade);
    }

    public void bind(final Activity activity, final boolean coordenador, final boolean isAvaliacao){
        this.nomeAtividadeTextView.setText(activity.getName());
        this.nomeApresentador.setText(activity.getPresenter().getNome());
        this.horarioAtividadeTextView.setText(DataFormatter.formatarDataHorario(activity.getInicialForeseenTime()));
        this.localAtividadeTextView.setText(activity.getPlace().getLocalSala());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDataActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("activity", activity);
                bundle.putBoolean("coordenador",coordenador);
                bundle.putBoolean("avaliacao",isAvaliacao);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

}
