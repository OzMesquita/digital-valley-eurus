package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.IDaoFactory;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceDatabase{

    private static WebServiceDatabase instance;

    private static Retrofit retrofitService;
    private static AtividadeService atividadeService;

    private WebServiceDatabase(){}

    public static WebServiceDatabase getInstance(){
        if(instance==null){
            instance = new WebServiceDatabase();
            inicializarRetrofitService();
        }
        return instance;
    }

    private static void inicializarRetrofitService() {
        retrofitService = new Retrofit.Builder().baseUrl("http://192.169.1.104:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public AtividadeService getAtividadeService(){
        if(atividadeService==null){
            atividadeService = retrofitService.create(AtividadeService.class);
        }
        return atividadeService;
    }
}
