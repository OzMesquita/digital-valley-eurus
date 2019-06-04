package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.IDaoFactory;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceDatabase implements IDaoFactory {

    private static Retrofit retrofitService;

    public static Retrofit getRetrofitService() {
        if(retrofitService==null){
            retrofitService = new Retrofit.Builder().baseUrl("http://192.169.1.104:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
        }
        return retrofitService;
    }

    @Override
    public IAtividadeBaseDao getAtividadeDao() {
        return null;
    }
}
