package ufc.russas.encontrosuniversitarios.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.view.adapter.AvaliacaoAtividadeAdapter;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;
import ufc.russas.encontrosuniversitarios.view.fragment.AvaliacaoListener;
import ufc.russas.encontrosuniversitarios.view.fragment.CriterioListener;
import ufc.russas.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AvaliacaoAtividadeActivity extends AppCompatActivity {
    private final static String ATIVIDADE = "atividade";
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private List<CriterioAtividade> criterios;
    private TextView txtMedia;
    private RecyclerView criteriosRec;
    Button confirmarAvaliacao;
    EditText comentarios;
    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.avaliacao_progresso);
        TextView txtActivityName = findViewById(R.id.activity_name);
        txtMedia = findViewById(R.id.media);
        confirmarAvaliacao = findViewById(R.id.confirmar_avaliacao);
        comentarios = findViewById(R.id.comentarios);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        txtActivityName.setText(avaliacaoAtividadeViewModel.getAtividade().getNome());
        criteriosRec = findViewById(R.id.lista_criterios);
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
        avaliacaoAtividadeViewModel.listarCriterios(new AtividadesListener() {
            @Override
            public void onLoading() {
                progressBar.setVisibility(View.VISIBLE);
                criteriosRec.setVisibility(View.GONE);
            }

            @Override
            public void onDone() {
                progressBar.setVisibility(View.GONE);
                criteriosRec.setVisibility(View.VISIBLE);
            }
        });
    }

    private CriterioListener criterioListener = new CriterioListener() {
        @Override
        public void onNotaChanged() {
            double total = 0;
            for (CriterioAtividade criterioAtividade : criterios) {
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
        notaAtividadeAvaliada.setTextColor(media < 7 ? getResources().getColor(R.color.note_presentation) : getResources().getColor(R.color.colorSecondary));
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

                            @Override
                            public void onLoading() {
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onDone() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
        builder.setView(customLayout);
        builder.show();
    }
}

