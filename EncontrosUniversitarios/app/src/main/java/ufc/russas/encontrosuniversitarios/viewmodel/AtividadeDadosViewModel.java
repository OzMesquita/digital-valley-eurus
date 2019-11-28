package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.AvaliacaoAtividade;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;

import org.joda.time.DateTime;

public class AtividadeDadosViewModel extends ViewModel {
    private MutableLiveData<Atividade> atividadeMutableLiveData;
    private MutableLiveData<DateTime> horarioInicioAtividade;
    private MutableLiveData<DateTime> horarioFinalAtividade;
    private MutableLiveData<Boolean> atividadeAvaliada;
    private AtividadeRepositorio atividadeRepositorio;
    private Atividade atividade;

    public AtividadeDadosViewModel(){
        atividadeRepositorio = AtividadeRepositorio.getInstance();
        atividadeMutableLiveData = new MutableLiveData<>();
        horarioFinalAtividade = new MutableLiveData<>();
        horarioInicioAtividade = new MutableLiveData<>();
        atividadeAvaliada = new MutableLiveData<>();
    }

    public void init(Atividade atividade){
        this.atividade = atividade;
        this.atividadeMutableLiveData.setValue(atividade);
        horarioInicioAtividade.setValue(atividade.getHorarioInicio());
        horarioFinalAtividade.setValue(atividade.getHorarioFinal());
    }

    /**
     * Este método verifica se a atividade foi iniciada, finalidada, para então chamar os métodos
     * responsávéis por iniciar e finalizar a atividade.
     * @param context
     */
    public void alterarHorarioAtividade(Context context) {
        if(!atividade.atividadeIniciada() && !atividade.atividadeFinalizada()){
            iniciarAtividade();
        }else if(atividade.atividadeIniciada()){
            finalizarAtividade(context);
        }
    }

    /**
     * Este método inicia a atividade com a hora obtida do servidor
     */
    private void iniciarAtividade(){
        atividadeRepositorio.getMomento(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                DateTime momento = (DateTime) response;
                boolean resultado = atividade.iniciar(momento);
                if(resultado){
                    atualizarHorariosAtividade(true);
                }
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    /**
     * Este método finaliza a atividade com a hora obtida do servidor
     */
    private void finalizarAtividade(final Context context){
        atividadeRepositorio.getMomento(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                try {
                    DateTime momento = (DateTime) response;
                    boolean resultado = atividade.finalizar(momento);
                    if(resultado){
                        atualizarHorariosAtividade(false);
                    }
                }catch (AtividadeFinalizadaAntesDoHorarioIniciadoException e){
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();;
                }
            }
            @Override
            public void onFailure(String message) {
            }
        });
    }

    /**
     * Este método inicia ou finaliza uma atividade dependendo do valor da variável isHorarioInicio
     * @param isHorarioInicio Caso true, a atividade deve ser inicializada, finalizada caso
     *                        contrário
     */
    private void atualizarHorariosAtividade(final boolean isHorarioInicio){
        atividadeRepositorio.atualizarAtividade(this.atividade, isHorarioInicio, new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                atividadeMutableLiveData.setValue(atividade);
                if(isHorarioInicio) {
                    horarioInicioAtividade.setValue(atividade.getHorarioInicio());
                }else {
                    horarioFinalAtividade.setValue(atividade.getHorarioFinal());
                }
            }
            @Override
            public void onFailure(String message) {
            }
        });
    }

    /**
     * Este método faz uma requisição ao webservice para verificar se a atividade já foi avaliada
     * @param context
     */
    public void verificarAtividadeJaAvaliada(final Context context){
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int idUsuario = preferences.getUserId();
        atividadeRepositorio.verificarAtividadeJaAvaliada(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                Boolean value = (Boolean) response;
                atividadeAvaliada.setValue(value);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context,"Não foi possível realizar esta operação",Toast.LENGTH_LONG).show();
            }
        }, new AvaliacaoAtividade(atividade.getId(),idUsuario,null,null));
    }

    public LiveData<Atividade> getAtividade() {
        return atividadeMutableLiveData;
    }

    public LiveData<DateTime> getHorarioInicio(){
        return horarioInicioAtividade;
    }

    public LiveData<DateTime> getHorarioFinal(){
        return horarioFinalAtividade;
    }

    public LiveData<Boolean> getAtividadeAvaliada() {
        return atividadeAvaliada;
    }
}
