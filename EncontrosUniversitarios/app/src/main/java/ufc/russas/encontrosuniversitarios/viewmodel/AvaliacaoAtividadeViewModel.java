package ufc.russas.encontrosuniversitarios.viewmodel;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.AvaliacaoAtividade;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.model.Nota;
import ufc.russas.encontrosuniversitarios.model.webservice.ResultadoAvaliacao;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;
import ufc.russas.encontrosuniversitarios.view.fragment.AvaliacaoListener;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoAtividadeViewModel extends ViewModel {

    private AtividadeRepositorio atividadeRepositorio;
    private MutableLiveData<List<CriterioAtividade>> criterios;
    private Atividade atividade;
    private UsuarioRepositorio usuarioRepositorio;
    private MutableLiveData<List<Atividade>> atividadesAvaliacao;

    public AvaliacaoAtividadeViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        criterios = new MutableLiveData<>();
        atividadesAvaliacao = new MutableLiveData<>();
    }

    public void init(Atividade atividade){
        this.atividade = atividade;
    }

    public LiveData<List<CriterioAtividade>> getCriterios() {
        return criterios;
    }

    /**
     * Este método busca os critérios de avaliação de atividades cadastrados no banco de dados
     * @param listener
     */
    public void listarCriterios(final AtividadesListener listener){
        listener.onLoading();
        atividadeRepositorio.getCriterios(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                criterios.setValue((List<CriterioAtividade>) response);
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                criterios.setValue(null);
            }
        });
    }

    /**
     * Este método envia a avaliação de uma atividade feita por um avaliador ao banco de dados
     * @param comentarios
     * @param context
     * @param listener
     */
    public void avaliarAtividade(String comentarios, Context context, final AvaliacaoListener listener){
        List<Nota> notas = new ArrayList<>();
        for(CriterioAtividade c: criterios.getValue()){
            notas.add(new Nota(c.getId(),c.getNota()));
        }
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        int idAvaliador = preferences.getUserId();
        listener.onLoading();
        atividadeRepositorio.avaliarAtividade(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                ResultadoAvaliacao resultadoAvaliacao = (ResultadoAvaliacao) response;
                if(resultadoAvaliacao.getAlreadyEvaluatedActivity()){
                    listener.onAlreadyEvaluatedActivity();
                }else if(!resultadoAvaliacao.getAlreadyEvaluatedActivity() && !resultadoAvaliacao.getError()){
                    listener.onSuccess();
                }else if(resultadoAvaliacao.getError()) {
                    listener.onError("Não foi possível avaliar esta atividade");
                }
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
                listener.onError("Houve um erro ao tentar realizar esta operação, verifique sua internet");
            }
        },new AvaliacaoAtividade(atividade.getId(),idAvaliador,comentarios,notas));
    }

    /**
     * Este método busca todas as atividades que o avaliador pode avaliar
     * @param context
     * @param listener
     */
    public void carregarAtividades(Context context, final AtividadesListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        listener.onLoading();
        atividadeRepositorio.getAtividadesProfessor(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                listener.onDone();
                atividadesAvaliacao.setValue((List<Atividade>)response);
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
            }
        }, preferences.getUserId());
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public LiveData<List<Atividade>> getAtividadesAvaliação() {
        return atividadesAvaliacao;}


}
