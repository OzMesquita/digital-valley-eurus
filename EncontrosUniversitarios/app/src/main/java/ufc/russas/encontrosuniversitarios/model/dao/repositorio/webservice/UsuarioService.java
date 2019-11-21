package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

import ufc.russas.encontrosuniversitarios.model.AlterarSenhaResponse;
import ufc.russas.encontrosuniversitarios.model.DadosAlterarSenha;
import ufc.russas.encontrosuniversitarios.model.DadosCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.DadosLogin;
import ufc.russas.encontrosuniversitarios.model.Usuario;
import ufc.russas.encontrosuniversitarios.model.ValidacaoCadastro;
import ufc.russas.encontrosuniversitarios.model.ValidacaoCheckInCheckOut;
import ufc.russas.encontrosuniversitarios.model.ValidacaoLogin;
import ufc.russas.encontrosuniversitarios.model.VerificacaoMatricula;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {
    /**
     * Esta rota é utilizada para cadastrar usuários no banco de dados.
     *
     * @param usuario Dados de cadastro do usuário: Nome, Matrícula, Email e Senha.
     * @return ValidacaoCadastro Este objeto representa a resposta que o webservice dará ao tentar
     * cadastrar o referente usuário. Essa resposta pode ser sucesso, quando o cadastro é realizado
     * sem problemas, email já utilizado ou matrícula já utilizada, ambos os dois últimos casos não
     * possibilitam o cadastro do usuário
     */
    @POST("usuarios")
    Call<ValidacaoCadastro> cadastrarUsuario(@Body Usuario usuario);

    /**
     * Esta rota é utilizada para autenticar o usuário no aplicativo.
     *
     * @param dados Este parâmetro possui o email e senha que o usuário forneceu para realizar login
     *              no sistema
     * @return ValidacaoLogin Este objeto representa a resposta que o webservice dará quando for
     * requisitado o login de usuário. Este objeto pode informar os seguintes dados: Email não
     * cadastrado; Senha incorreta; Login realizado com sucesso
     */
    @POST("auth")
    Call<ValidacaoLogin> autenticarUsuario(@Body DadosLogin dados);

    /**
     * Esta rota é utilizada para fazer o check in ou check out de um usuário.
     * @param dadosCheckInCheckOut Este objeto representa um usuário e uma sala, dados necessários
     *                             para realizar check in ou check out em uma sala.
     * @return ValidacaoCheckInCheckOut Este objeto representa a resposta que o webservice dará
     * quando for requisitado o check in ou check out de um usuário em uma sala. A resposta poderá
     * ser: Usuário precisa fazer check out na última sala que frequentou; Operação realizada com
     * sucesso, podendo ser check in ou check out de acordo com a mensagem contida nesse objeto.
     */
    @POST("/frequencia")
    Call<ValidacaoCheckInCheckOut> checkInCheckOut(@Body DadosCheckInCheckOut dadosCheckInCheckOut);

    /**
     * Esta rota verifica se a matrícula fornecida já possui cadastro no banco de dados
     *
     * @param matricula Matrícula do aluno
     * @return DadosFrequenciaUsuario Este objeto é a resposta gerada pelo webservice, contendo o id
     * e nome do usuário dono da matrícula, caso contrário nenhum dado é recebido.
     */
    @GET("usuario/{matricula}")
    Call<DadosFrequenciaUsuario> getUsuario(@Path("matricula") String matricula);

    /**
     * Esta rota é utilizada para verificar se a matrícula fornecida é válida e se possui cadastro
     * no sistema Guardião da N2S.
     *
     * @param matricula Mátricula do usuário
     * @return VerificacaoMatricula Resposta do webservice, podendo conter a matrícula e nome do
     * aluno caso a matrícula exista, vazio caso contrário.
     */
    @GET("/usuario/verificacao/{matricula}")
    Call<VerificacaoMatricula> getVerificacaoMatricula(@Path("matricula") String matricula);

    /**
     * Esta rota é utilizada para o envio de um email de recuperação de senha para o usuário,
     * contendo um código de verificação que deve ser utilizado no momento da alteração da senha.
     * @param email Email do usuário
     * @return Boolean True caso foi possível enviar o email, false caso contrário
     */
    @GET("recuperarsenha/{email}")
    Call<Boolean> recuperarSenha(@Path("email")String email);

    /**
     * Esta rota é utilizada para alterar a senha do usuário
     * @param dadosAlterarSenha Objeto contendo o código de verificação e a nova senha do usuário.
     * @return AlterarSenhaResponse Este objeto pode apresentar uma mensagem de sucesso caso o código
     * seja válido, caso contrário a alteração da senha não será feita e uma mensagem de erro é
     * apresentada.
     */
    @POST("alterarsenha")
    Call<AlterarSenhaResponse> alterarSenha(@Body DadosAlterarSenha dadosAlterarSenha);
}
