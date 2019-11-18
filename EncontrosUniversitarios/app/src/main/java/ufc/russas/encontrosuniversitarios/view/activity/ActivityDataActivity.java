package ufc.russas.encontrosuniversitarios.view.activity;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.databinding.ActivityAtividadeDadosBinding;
import ufc.russas.encontrosuniversitarios.helper.DataFormatter;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.viewmodel.ActivityDataViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
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

public class ActivityDataActivity extends AppCompatActivity {
    private static final String ATIVIDADE = "atividade";
    private static final String COORDENADOR = "coordenador";
    private static final String AVALIACAO = "avaliacao";

    private ActivityDataViewModel activityDataViewModel;
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
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(8,intent);

        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
            activityDataViewModel = ViewModelProviders.of(this).get(ActivityDataViewModel.class);
            Activity activity = intent.getParcelableExtra(ATIVIDADE);
            activityDataViewModel.init(activity);
            this.coordenador = intent.getBooleanExtra(COORDENADOR,false);
            this.isAvaliacao = intent.getBooleanExtra(AVALIACAO,false);

        }
        ActivityAtividadeDadosBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_atividade_dados);
        binding.setAtividade(activityDataViewModel.getActivity().getValue());
        binding.setFormatador(new DataFormatter());
        binding.setHorarioInicial(activityDataViewModel.getHorarioInicio().getValue());
        binding.setHorarioFinal(activityDataViewModel.getHorarioFinal().getValue());
        TextView horarioDataAtividade = binding.getRoot().findViewById(R.id.horario_data_atividade);
        horarioDataAtividade.setText(DataFormatter.formatarDataHorario(activityDataViewModel.getActivity().getValue().getInicialForeseenTime()));
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
        activityDataViewModel.verificarAtividadeJaAvaliada(this);

        activityDataViewModel.getAtividadeAvaliada().observe(this, new Observer<Boolean>() {
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

        activityDataViewModel.getHorarioInicio().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioInicio) {
                configurarIniciarFinalizarAtividade();
                horarioIniciado.setText(DataFormatter.formatarDataHorario(horarioInicio));
            }
        });

        activityDataViewModel.getHorarioFinal().observe(this, new Observer<DateTime>() {
            @Override
            public void onChanged(DateTime horarioFinal) {
                horarioFinalizado.setText(DataFormatter.formatarDataHorario(horarioFinal));
                configurarIniciarFinalizarAtividade();
            }
        });

        iniciarFinalizarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityDataViewModel.alterarHorarioAtividade(getApplicationContext());

            }
        });
        if(!isAvaliacao){
            avaliarAtividade.setVisibility(View.GONE);
        }else{
            avaliarAtividade.setVisibility(View.VISIBLE);
        }

        avaliarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityEvaluationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("atividade", activityDataViewModel.getActivity().getValue());
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
    }

    private void iniciarHorarios(){
        if(activityDataViewModel.getActivity().getValue().getInitialTime()!=null){
            horarioIniciado.setText(DataFormatter.formatarDataHorario(activityDataViewModel.getActivity().getValue().getInitialTime()));
        }
        if(activityDataViewModel.getActivity().getValue().getFinalTime()!=null){
            horarioFinalizado.setText(DataFormatter.formatarDataHorario(activityDataViewModel.getActivity().getValue().getFinalTime()));
        }
    }

    private void configurarAtividadeNaoIniciada(){
        estadoAtividade.setText(activityDataViewModel.getActivity().getValue().getActivityState());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_green_button);
        iniciarFinalizarAtividade.setText(R.string.iniciar_atividade);
    }

    private void configurarAtividadeIniciada(){
        estadoAtividade.setText(activityDataViewModel.getActivity().getValue().getActivityState());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_red_button);
        iniciarFinalizarAtividade.setText(R.string.finalizar_atividade);
    }

    private void configurarAtividadeFinalizada(){
        estadoAtividade.setText(activityDataViewModel.getActivity().getValue().getActivityState());
        iniciarFinalizarAtividade.setBackgroundResource(R.drawable.round_gray_buttom);
        iniciarFinalizarAtividade.setText(R.string.atividade_finalizada);

    }

    private void configurarIniciarFinalizarAtividade(){
        corEstado.setBackgroundColor(selecionarCorEstadoAtividade(activityDataViewModel.getActivity().getValue().getActivityState()));
        iniciarFinalizarAtividade.setBackgroundColor(selecionarCorEstadoAtividade(activityDataViewModel.getActivity().getValue().getActivityState()));
        switch (activityDataViewModel.getActivity().getValue().getActivityState()){
            case Activity.STARTED:
                configurarAtividadeIniciada();
                break;
            case Activity.NOT_STARTED:
                configurarAtividadeNaoIniciada();
                break;
            case Activity.FINISHED:
                configurarAtividadeFinalizada();
        }
    }

    private int selecionarCorEstadoAtividade(String estado){
        int cor = ContextCompat.getColor(this, R.color.future_activity);
        switch (estado){
            case Activity.STARTED:
                cor = ContextCompat.getColor(this, R.color.started_activity);
                break;
            case Activity.FINISHED:
                cor = ContextCompat.getColor(this, R.color.finished_activity);
                break;
            case Activity.NOT_STARTED:
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
