package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

import android.util.Log;

import ufc.russas.encontrosuniversitarios.model.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.DadosCheckIn;
import ufc.russas.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.ValidacaoCadastro;
import ufc.russas.encontrosuniversitarios.model.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.VerificacaoMatricula;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.database.WebServiceDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepositorio {

    private static UsuarioRepositorio usuarioRepositorio;
    private UsuarioService usuarioService;
    private UsuarioRepositorio(){
        usuarioService = WebServiceDatabase.getInstance().getUsuarioService();
    }

    public static UsuarioRepositorio getInstance() {
        if(usuarioRepositorio==null){
            usuarioRepositorio = new UsuarioRepositorio();
        }
        return usuarioRepositorio;
    }

    public void cadastrarUsuario(final ResponseListener listener, Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario)
                .enqueue(new Callback<ValidacaoCadastro>() {
                    @Override
                    public void onResponse(Call<ValidacaoCadastro> call, Response<ValidacaoCadastro> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ValidacaoCadastro> call, Throwable t) {
                        listener.onFailure("Erro ao executar requisição");
                    }
                });
    }

    public void realizarLogin(final ResponseListener listener, DadosLogin dadosLogin){
        usuarioService.autenticarUsuario(dadosLogin).enqueue(new Callback<ValidacaoLogin>() {
                    @Override
                    public void onResponse(Call<ValidacaoLogin> call, Response<ValidacaoLogin> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ValidacaoLogin> call, Throwable t) {
                        listener.onFailure("Erro ao executar requisição");
                    }
                });
    }

    public void checkInCheckOut(final ResponseListener listener, DadosCheckIn dadosCheckIn) {
        Log.i("CHECKIN",""+dadosCheckIn.getIdUsuario());
        usuarioService.checkInCheckOut(dadosCheckIn).enqueue(new Callback<ValidacaoCheckInCheckOut>() {
            @Override
            public void onResponse(Call<ValidacaoCheckInCheckOut> call, Response<ValidacaoCheckInCheckOut> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ValidacaoCheckInCheckOut> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    public void buscarUsuario(final ResponseListener listener, String matricula) {
        usuarioService.getUsuario(matricula).enqueue(new Callback<DadosFrequenciaUsuario>() {
            @Override
            public void onResponse(Call<DadosFrequenciaUsuario> call, Response<DadosFrequenciaUsuario> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DadosFrequenciaUsuario> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Este método validará se a matricula passada possui pré cadastro no sistema do guardião.
     * @param listener, serve para encapsular o comportamento da view
     * @param matricula
     */
    public void verificarMatricula(final ResponseListener listener, String matricula){
        usuarioService.getVerificacaoMatricula(matricula).enqueue(new Callback<VerificacaoMatricula>() {
            @Override
            public void onResponse(Call<VerificacaoMatricula> call, Response<VerificacaoMatricula> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<VerificacaoMatricula> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
    public void recuperarSenha(final ResponseListener listener, String email){
        usuarioService.recuperarSenha(email).enqueue(new Callback<Boolean>() {
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

    public void alterarSenha(final ResponseListener listener, DadosAlterarSenha dadosAlterarSenha){
        usuarioService.alterarSenha(dadosAlterarSenha).enqueue(new Callback<AlterarSenhaResponse>() {
            @Override
            public void onResponse(Call<AlterarSenhaResponse> call, Response<AlterarSenhaResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AlterarSenhaResponse> call, Throwable t) {
                listener.onFailure("Erro ao alterar a senha");
            }
        });
    }

}
