package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import ufc.russas.encontrosuniversitarios.model.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.DadosCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCadastro;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.webservice.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.webservice.VerificacaoMatricula;
import ufc.russas.encontrosuniversitarios.model.dao.database.WebServiceDatabase;

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

    /**
     * Este método cadastra um usuário no banco de dados
     * @param listener Encapsula o comportamento da view
     * @param usuario Dados de cadastro do usuário
     */
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

    /**
     * Este método é utilizado para validar os dados de login de um usuário
     * @param listener Encapsula o comportamento da view
     * @param dadosLogin Dados de login do usuário: Email e Senha
     */
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

    /**
     * Este método é utilizado para fazer o check in ou check out de um usuário em uma sala do
     * evento, a decisão se é check in ou check out é realizada no webservice
     * @param listener Encapsula o comportamento da view
     * @param dadosCheckInCheckOut Dados do usuário e a sala em que está realizando check in ou
     *                             check out
     */
    public void checkInCheckOut(final ResponseListener listener, DadosCheckInCheckOut dadosCheckInCheckOut) {
        usuarioService.checkInCheckOut(dadosCheckInCheckOut).enqueue(new Callback<ValidacaoCheckInCheckOut>() {
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

    /**
     * Este método é utilizado para buscar um usuário cadastrado no banco de dados de acordo com sua
     * matrícula
     * @param listener Encapsula o comportamento da view
     * @param matricula Matrícula para buscar o usuário
     */
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
     * Este método validará se a matricula passada possui pré cadastro no sistema Guardião.
     * @param listener Encapsula o comportamento da view
     * @param matricula Matrícula a ser verificada no sistema Guardião
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

    /**
     * Este método é utilizado para enviar um email de recuperação de senha para o email fornecido.
     * O email enviado conterá um código de verificação.
     * @param listener Encapsula o comportamento da view
     * @param email Email destinatário para enviar o email de verificação
     */
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

    /**
     * Este método é utilizado para alterar a senha de um usuário utilizando um código de validação
     * e a nova senha fornecida pelo usuário
     * @param listener Encapsula o comportamento da view
     * @param dadosAlterarSenha Objeto com o código de validação e nova senha do usuário
     */
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
