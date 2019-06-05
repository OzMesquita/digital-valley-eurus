package com.example.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.util.Log;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class AtividadeService implements IAtividadeBaseDao{

    @Override
    public void inserir(Atividade atividade) {
        AtividadePostgreeService apiService = WebServiceDatabase.getRetrofitService().create(AtividadePostgreeService.class);
        Single<Atividade> description = apiService.getDescription();
        description.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Atividade>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Atividade atividade) {
                Log.i("Atividade",atividade.getNome());
                Log.i("Atividade",atividade.getDescricao());
                Log.i("Atividade",atividade.getCategoria().getNome());
                Log.i("Atividade",atividade.getTrabalho().getTitulo());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Atividade","Error: "+e.getMessage());
            }
        });
    }

    @Override
    public LiveData<List<Atividade>> buscar() {
        return null;
    }

    private interface AtividadePostgreeService{
        @GET("/atividades")
        Single<Atividade> getDescription();
    }

    private class Info{
        @SerializedName("info")
        private String info;

        public Info(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}