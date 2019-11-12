package ufc.russas.encontrosuniversitarios.model.dao.database;

import ufc.russas.encontrosuniversitarios.model.dao.webservice.AtividadeService;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.DateTimeJsonConverter;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

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
                .baseUrl("http://200.129.62.41:3000/")
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
