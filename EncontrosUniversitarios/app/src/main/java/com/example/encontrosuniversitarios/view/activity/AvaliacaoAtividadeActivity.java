package com.example.encontrosuniversitarios.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class AvaliacaoAtividadeActivity extends AppCompatActivity {
    private final static String ATIVIDADE = "atividade";
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private List<CriterioAtividade> criterios;
    private TextView txtMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_atividade);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        if(getIntent().getParcelableExtra(ATIVIDADE) != null){
            Atividade atividade = getIntent().getParcelableExtra(ATIVIDADE);
            avaliacaoAtividadeViewModel.init(atividade);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}
