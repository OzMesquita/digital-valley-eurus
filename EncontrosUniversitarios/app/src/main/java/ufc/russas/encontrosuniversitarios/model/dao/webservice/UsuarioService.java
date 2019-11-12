package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import ufc.russas.encontrosuniversitarios.model.webservice.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosCheckIn;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.webservice.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCadastro;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.webservice.VerificacaoMatricula;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {
    @POST("usuarios")
    Call<ValidacaoCadastro> cadastrarUsuario(@Body Usuario usuario);

    @POST("auth")
    Call<ValidacaoLogin> autenticarUsuario(@Body DadosLogin dados);

    @POST("/frequencia")
    Call<ValidacaoCheckInCheckOut> checkInCheckOut(@Body DadosCheckIn dadosCheckIn);

    @GET("usuario/{matricula}")
    Call<DadosFrequenciaUsuario> getUsuario(@Path("matricula") String matricula);

    @GET("/usuario/verificacao/{matricula}")
    Call<VerificacaoMatricula> getVerificacaoMatricula(@Path("matricula") String matricula);

    @GET("recuperarsenha/{email}")
    Call<Boolean> recuperarSenha(@Path("email")String email);

    @POST("alterarsenha")
    Call<AlterarSenhaResponse> alterarSenha(@Body DadosAlterarSenha dadosAlterarSenha);
}
