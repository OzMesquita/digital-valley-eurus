package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.PasswordUpdateResponse;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.PasswordUpdateData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.CheckInData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.UserAttendanceData;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.LoginData;
import ufc.russas.encontrosuniversitarios.model.User;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.RegisterValidation;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.CheckInCheckOutValidation;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.LoginValidation;
import ufc.russas.encontrosuniversitarios.model.webservice_data_classes.MatriculaVerification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("usuarios")
    Call<RegisterValidation> registerUser(@Body User user);

    @POST("auth")
    Call<LoginValidation> login(@Body LoginData dados);

    @POST("/frequencia")
    Call<CheckInCheckOutValidation> checkInCheckOut(@Body CheckInData checkInData);

    @GET("usuario/{matricula}")
    Call<UserAttendanceData> getUser(@Path("matricula") String matricula);

    @GET("/usuario/verificacao/{matricula}")
    Call<MatriculaVerification> verifyMatricula(@Path("matricula") String matricula);

    @GET("recuperarsenha/{email}")
    Call<Boolean> recoverPassword(@Path("email")String email);

    @POST("alterarsenha")
    Call<PasswordUpdateResponse> updatePassword(@Body PasswordUpdateData passwordUpdateData);
}
