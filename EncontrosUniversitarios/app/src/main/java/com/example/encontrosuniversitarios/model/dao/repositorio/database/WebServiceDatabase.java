package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.IDaoFactory;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.DateTimeJsonConverter;
import com.example.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceDatabase {

    private static WebServiceDatabase instance;

    private static Retrofit retrofitService;
    private static AtividadeService atividadeService;
    private static UsuarioService usuarioService;

    private WebServiceDatabase() {
    }

    public static WebServiceDatabase getInstance() {
        if (instance == null) {
            instance = new WebServiceDatabase();
            inicializarRetrofitService();
        }
        return instance;
    }

    private static void inicializarRetrofitService() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(DateTime.class, new DateTimeJsonConverter())
                .create();
        retrofitService = new Retrofit.Builder()
                .baseUrl("http://192.169.1.102:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public AtividadeService getAtividadeService() {
        if (atividadeService == null) {
            atividadeService = retrofitService.create(AtividadeService.class);
        }
        return atividadeService;
    }

    public UsuarioService getUsuarioService() {
        if(usuarioService == null){
            usuarioService = retrofitService.create(UsuarioService.class);
        }
        return usuarioService;
    }


}
