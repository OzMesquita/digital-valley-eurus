package com.example.encontrosuniversitarios.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.view.adapter.AvaliacaoAtividadeAdapter;
import com.example.encontrosuniversitarios.view.fragment.AvaliacaoListener;
import com.example.encontrosuniversitarios.view.fragment.CriterioListener;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class AvaliacaoAtividadeActivity extends AppCompatActivity {
    private final static String ATIVIDADE = "atividade";
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private List<CriterioAtividade> criterios;
    private TextView txtMedia;
    Button confirmarAvaliacao;
    EditText comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_atividade);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        if (getIntent().getParcelableExtra(ATIVIDADE) != null) {
            Atividade atividade = getIntent().getParcelableExtra(ATIVIDADE);
            avaliacaoAtividadeViewModel.init(atividade);
        }

        this.getSupportActionBar().setTitle("Avaliação");
        TextView txtActivityName = findViewById(R.id.activity_name);

        txtMedia = findViewById(R.id.media);
        confirmarAvaliacao = findViewById(R.id.confirmar_avaliacao);
        comentarios = findViewById(R.id.comentarios);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        txtActivityName.setText(avaliacaoAtividadeViewModel.getAtividade().getNome());
        final RecyclerView criteriosRec = findViewById(R.id.lista_criterios);
        criteriosRec.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        avaliacaoAtividadeViewModel.getCriterios().observe(this, new Observer<List<CriterioAtividade>>() {
            @Override
            public void onChanged(List<CriterioAtividade> criterioAtividades) {
                if (criterioAtividades != null) {
                    criterios = criterioAtividades;
                    AvaliacaoAtividadeAdapter adapter = new AvaliacaoAtividadeAdapter(criterios, criterioListener);
                    criteriosRec.setAdapter(adapter);
                    criterioListener.onNotaChanged();
                }
            }
        });
        confirmarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showConfirmarAvaliacaoDialog();
            }
        });
        avaliacaoAtividadeViewModel.listarCriterios();
    }

    private CriterioListener criterioListener = new CriterioListener() {
        @Override
        public void onNotaChanged() {
            double total = 0;
            for (CriterioAtividade criterioAtividade : criterios) {
                Log.i("teste ", ""+criterioAtividade.getNota());
                total += criterioAtividade.getNota();
            }
            txtMedia.setText("Media: " + total / criterios.size());
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    public void showConfirmarAvaliacaoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View customLayout = getLayoutInflater().inflate(R.layout.confirmar_avaliacao_dialog, null);

        final TextView nomeAtividadeAvaliada = customLayout.findViewById(R.id.nome_atividade_avaliada);
        final TextView notaAtividadeAvaliada = customLayout.findViewById(R.id.nota_atividade_avaliada);
        nomeAtividadeAvaliada.setText(avaliacaoAtividadeViewModel.getAtividade().getNome());
        notaAtividadeAvaliada.setText(txtMedia.getText().toString());
        double media = Double.valueOf(txtMedia.getText().toString().replaceAll("Media: ",""));
        notaAtividadeAvaliada.setTextColor(media < 7 ? getResources().getColor(R.color.vermelho) : getResources().getColor(R.color.colorSecondary));
        builder.setNegativeButton("Não", null);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                avaliacaoAtividadeViewModel.avaliarAtividade(comentarios.getText().toString(),
                        getBaseContext(), new AvaliacaoListener() {
                            @Override
                            public void onAlreadyEvaluatedActivity() {
                                Toast.makeText(getBaseContext(), "Essa atividade já foi avaliada", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(String message) {
                                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onSuccess() {
                                Toast.makeText(getBaseContext(), "Avaliação realizada com sucesso", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        });
            }
        });
        builder.setView(customLayout);
        builder.show();
    }
}

