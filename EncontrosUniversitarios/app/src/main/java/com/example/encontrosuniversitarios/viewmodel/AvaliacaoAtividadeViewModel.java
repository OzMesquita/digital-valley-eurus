package com.example.encontrosuniversitarios.viewmodel;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.AvaliacaoAtividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.model.Nota;
import com.example.encontrosuniversitarios.model.ResultadoAvaliacao;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeRepositorio;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioRepositorio;
import com.example.encontrosuniversitarios.view.fragment.AvaliacaoListener;
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

    public void listarCriterios(){
        atividadeRepositorio.getCriterios(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                criterios.setValue((List<CriterioAtividade>) response);
            }

            @Override
            public void onFailure(String message) {
                criterios.setValue(null);
            }
        });
    }

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

    public void carregarAtividades(Context context) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        atividadeRepositorio.getAtividadesProfessor(new ResponseListener() {
            @Override
            public void onSuccess(Object response) {
                atividadesAvaliacao.setValue((List<Atividade>)response);
            }

            @Override
            public void onFailure(String message) {
                Log.i("AtvFailura:", message);
            }
        }, preferences.getUserId());
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public LiveData<List<Atividade>> getAtividadesAvaliação() {
        return atividadesAvaliacao;}


}
