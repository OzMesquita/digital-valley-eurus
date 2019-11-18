package ufc.russas.encontrosuniversitarios.model.dao.repositorio.database;

<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/model/dao/repositorio/database/WebServiceDatabase.java
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.AtividadeService;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.DateTimeJsonConverter;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.UsuarioService;
=======
import ufc.russas.encontrosuniversitarios.model.dao.webservice.ActivityService;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.DateTimeJsonConverter;
import ufc.russas.encontrosuniversitarios.model.dao.webservice.UserService;
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/model/dao/database/WebServiceDatabase.java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceDatabase {

    private static WebServiceDatabase instance;

    private static Retrofit retrofitService;
    private static ActivityService activityService;
    private static UserService userService;

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

    public ActivityService getAtividadeService() {
        if (activityService == null) {
            activityService = retrofitService.create(ActivityService.class);
        }
        return activityService;
    }

    public UserService getUsuarioService() {
        if(userService == null){
            userService = retrofitService.create(UserService.class);
        }
        return userService;
    }

}
