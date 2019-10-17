package com.example.encontrosuniversitarios.view.activity;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.databinding.ActivityAtividadeDadosBinding;
import com.example.encontrosuniversitarios.helper.FormatadorData;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.view.fragment.AvaliacaoAtividadeFragment;
import com.example.encontrosuniversitarios.viewmodel.AtividadeDadosViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.DateTime;

public class AtividadeDadosActivity extends AppCompatActivity {
    private static final String ATIVIDADE = "atividade";
    private static final String COORDENADOR = "coordenador";
    private static final String AVALIACAO = "avaliacao";

    private AtividadeDadosViewModel atividadeDadosViewModel;
    private Button iniciarFinalizarAtividade;
    private Button avaliarAtividade;
    private TextView estadoAtividade;
    private View corEstado;
    private TextView horarioIniciado;
    private TextView horarioFinalizado;

    private boolean coordenador;
    private boolean isAvaliacao;
    private boolean avaliador;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("PROCURA","ATIVIDADEDADOSACTIVITY");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_atividade_dados);
        Intent intent = getIntent();
        MySharedPreferences preferences = MySharedPreferences.getInstance(this);
        avaliador = preferences.getUserAccessLevel() == 2;
        if (intent.getParcelableExtra(ATIVIDADE) != null) {
            atividadeDadosViewModel = ViewModelProviders.of(this).get(AtividadeDadosViewModel.class);
            Atividade atividade = intent.getParcelableExtra(ATIVIDADE);
            atividadeDadosViewModel.init(atividade);
            this.coordenador = intent.getBooleanExtra(COORDENADOR,false);
            this.isAvaliacao = intent.getBooleanExtra(AVALIACAO,false);

        }
        ActivityAtividadeDadosBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_atividade_dados);
        binding.setAtividade(atividadeDadosViewModel.getAtividade().getValue());
        binding.setFormatador(new FormatadorData());
        binding.setHorarioInicial(atividadeDadosViewModel.getHorarioInicio().getValue());
        binding.setHorarioFinal(atividadeDadosViewModel.getHorarioFinal().getValue());
        TextView horarioDataAtividade = binding.getRoot().findViewById(R.id.horario_data_atividade);
        horarioDataAtividade.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicialPrevisto()));
        estadoAtividade = binding.getRoot().findViewById(R.id.estado_atividade);

        corEstado = binding.getRoot().findViewById(R.id.atividade_estado_cor);
        avaliarAtividade = binding.getRoot().findViewById(R.id.avaliar_atividade);
        horarioIniciado = binding.getRoot().findViewById(R.id.horario_iniciado);
        horarioFinalizado = binding.getRoot().findViewById(R.id.horario_finalizado);
        iniciarFinalizarAtividade = binding.getRoot().findViewById(R.id.iniciar_finalizar_atividade);
        if(!coordenador){
            iniciarFinalizarAtividade.setVisibility(View.GONE);
        }else{
            iniciarFinalizarAtividade.setVisibility(View.VISIBLE);
        }
        configurarIniciarFinalizarAtividade();
        iniciarHorarios();
        atividadeDadosViewModel.verificarAtividadeJaAvaliada(this);

        atividadeDadosViewModel.getAtividadeAvaliada().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    avaliarAtividade.setEnabled(false);
                    avaliarAtividade.setBackgroundResource(R.drawable.round_gray_buttom);
                }else{
                    avaliarAtividade.setEnabled(true);
                }
            }
        });

        atividadeDadosViewModel.getHorarioInicio().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioInicio) {
                configurarIniciarFinalizarAtividade();
                horarioIniciado.setText(FormatadorData.formatarDataHorario(horarioInicio));
            }
        });

        atividadeDadosViewModel.getHorarioFinal().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioFinal) {
                horarioFinalizado.setText(FormatadorData.formatarDataHorario(horarioFinal));
                configurarIniciarFinalizarAtividade();
            }
        });

        iniciarFinalizarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividadeDadosViewModel.alterarHorarioAtividade(getApplicationContext());

            }
        });
        if(!avaliador){
            avaliarAtividade.setVisibility(View.GONE);
        }else if(avaliador && isAvaliacao){
            iniciarFinalizarAtividade.setVisibility(View.GONE);
        }
        avaliarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AvaliacaoAtividadeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("atividade",atividadeDadosViewModel.getAtividade().getValue());
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
    }

    private void iniciarHorarios(){
        if(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicio()!=null){
            horarioIniciado.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioInicio()));
        }
        if(atividadeDadosViewModel.getAtividade().getValue().getHorarioFinal()!=null){
            horarioFinalizado.setText(FormatadorData.formatarDataHorario(atividadeDadosViewModel.getAtividade().getValue().getHorarioFinal()));
        }
    }

    private void configurarAtividadeNaoIniciada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_green_button);
        iniciarFinalizarAtividade.setText(R.string.iniciar_atividade);
    }

    private void configurarAtividadeIniciada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_red_button);
        iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
    }

    private void configurarAtividadeFinalizada(){
        estadoAtividade.setText(atividadeDadosViewModel.getAtividade().getValue().getEstado());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_gray_buttom);
        iniciarFinalizarAtividade.setText(R.string.atividade_finalizada);

    }

    private void configurarIniciarFinalizarAtividade(){
        corEstado.setBackgroundColor(selecionarCorEstadoAtividade(atividadeDadosViewModel.getAtividade().getValue().getEstado()));
        iniciarFinalizarAtividade.setBackgroundColor(selecionarCorEstadoAtividade(atividadeDadosViewModel.getAtividade().getValue().getEstado()));
        switch (atividadeDadosViewModel.getAtividade().getValue().getEstado()){
            case Atividade.INICIADA:
                configurarAtividadeIniciada();
                break;
            case Atividade.NAO_INICIADA:
                configurarAtividadeNaoIniciada();
                break;
            case Atividade.FINALIZADA:
                configurarAtividadeFinalizada();
        }
    }

    private int selecionarCorEstadoAtividade(String estado){
        int cor = ContextCompat.getColor(this, R.color.future_activity);
        switch (estado){
            case Atividade.INICIADA:
                cor = ContextCompat.getColor(this, R.color.started_activity);
                break;
            case Atividade.FINALIZADA:
                cor = ContextCompat.getColor(this, R.color.finished_activity);
                break;
            case Atividade.NAO_INICIADA:
                cor = ContextCompat.getColor(this, R.color.future_activity);
                break;
        }
        return cor;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("PROCURA","RESULT");
        if( requestCode == 1 ){
            if( resultCode == RESULT_OK){
                Log.i("PROCURA","FINISH");
                finish();
            }
        }
    }
}
