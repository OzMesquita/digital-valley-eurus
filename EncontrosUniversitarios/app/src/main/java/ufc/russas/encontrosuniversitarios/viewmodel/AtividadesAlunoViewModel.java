package ufc.russas.encontrosuniversitarios.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.AtividadeRepositorio;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioRepositorio;
import ufc.russas.encontrosuniversitarios.view.fragment.AtividadesListener;

import java.util.List;

public class AtividadesAlunoViewModel extends ViewModel {
    private AtividadeRepositorio atividadeRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private MutableLiveData<List<Atividade>> atividades;

    public AtividadesAlunoViewModel() {
        this.atividadeRepositorio = AtividadeRepositorio.getInstance();
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
        atividades = new MutableLiveData<>();
    }

    /**
     * Este método busca todas as atividades que o usuário logado participou
     * @param context
     * @param listener
     */
    public void carregarAtividades(Context context, final AtividadesListener listener) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(context);
        listener.onLoading();
        atividadeRepositorio.buscarAtividadesParticipadas(new ResponseListener<List<Atividade>>() {
            @Override
            public void onSuccess(List<Atividade> atividadesEvento) {
                listener.onDone();
                atividades.setValue(atividadesEvento);
            }

            @Override
            public void onFailure(String message) {
                listener.onDone();
            }
        }, preferences.getUserId());
    }

    public LiveData<List<Atividade>> getAtividades() {
        return atividades;
    }

}
