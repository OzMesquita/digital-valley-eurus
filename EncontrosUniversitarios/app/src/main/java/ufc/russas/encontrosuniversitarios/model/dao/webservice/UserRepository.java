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
import ufc.russas.encontrosuniversitarios.model.dao.database.WebServiceDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository userRepository;
    private UserService userService;
    private UserRepository(){
        userService = WebServiceDatabase.getInstance().getUsuarioService();
    }

    public static UserRepository getInstance() {
        if(userRepository ==null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public void registerUser(final ResponseListener listener, User user) {
        userService.registerUser(user)
                .enqueue(new Callback<RegisterValidation>() {
                    @Override
                    public void onResponse(Call<RegisterValidation> call, Response<RegisterValidation> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<RegisterValidation> call, Throwable t) {
                        listener.onFailure("Erro ao executar requisição");
                    }
                });
    }

    public void login(final ResponseListener listener, LoginData loginData){
        userService.login(loginData).enqueue(new Callback<LoginValidation>() {
                    @Override
                    public void onResponse(Call<LoginValidation> call, Response<LoginValidation> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<LoginValidation> call, Throwable t) {
                        listener.onFailure("Erro ao executar requisição");
                    }
                });
    }

    public void checkInCheckOut(final ResponseListener listener, CheckInData checkInData) {
        userService.checkInCheckOut(checkInData).enqueue(new Callback<CheckInCheckOutValidation>() {
            @Override
            public void onResponse(Call<CheckInCheckOutValidation> call, Response<CheckInCheckOutValidation> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CheckInCheckOutValidation> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void searchUser(final ResponseListener listener, String matricula) {
        userService.getUser(matricula).enqueue(new Callback<UserAttendanceData>() {
            @Override
            public void onResponse(Call<UserAttendanceData> call, Response<UserAttendanceData> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserAttendanceData> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void verifyMatricula(final ResponseListener listener, String matricula){
        userService.verifyMatricula(matricula).enqueue(new Callback<MatriculaVerification>() {
            @Override
            public void onResponse(Call<MatriculaVerification> call, Response<MatriculaVerification> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MatriculaVerification> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
    public void recoverPassword(final ResponseListener listener, String email){
        userService.recoverPassword(email).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao solicitar redefinição de senha");
            }
        });
    }

    public void updatePassword(final ResponseListener listener, PasswordUpdateData passwordUpdateData){
        userService.updatePassword(passwordUpdateData).enqueue(new Callback<PasswordUpdateResponse>() {
            @Override
            public void onResponse(Call<PasswordUpdateResponse> call, Response<PasswordUpdateResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PasswordUpdateResponse> call, Throwable t) {
                listener.onFailure("Erro ao alterar a senha");
            }
        });
    }

}
