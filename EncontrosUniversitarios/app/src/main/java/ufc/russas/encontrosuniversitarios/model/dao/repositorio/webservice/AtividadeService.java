package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.AvaliacaoAtividade;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.model.ResultadoAvaliacao;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ufc.russas.encontrosuniversitarios.model.HorarioAtividade;

public interface AtividadeService {
    /**
     * Esta rota busca todas as atividades cadastradas no banco de dados
     * @return List<Atividade> Lista das atividades cadastradas
     */
    @GET("atividades")
    Call<List<Atividade>> getAtividades();

    /**
     * Esta rota busca todas as atividades do dia atual disponíveis no banco de dados.
     * O dia considerado será o dia do servidor em que o banco de dados está hospedado.
     * @return List<Atividade> Lista de atividades do dia
     */
    @GET("hoje/atividades/")
    Call<List<Atividade>> getAtividadesDoDia();

    /**
     * Essa rota é utilizada para iniciar/finalizar uma atividade
     *
     * @param idAtividade Esse parâmetro representa o id de uma atividade do banco de dados
     * @param horarioAtividade Esse parâmetro representa o horário de início ou fim da atividade com
     *                         o id do parâmetro idAtividade
     * @return Boolean O valor deste boolean representa o sucesso, caso true, ou falha da operação caso contrário
     */
    @PUT("atividades/{id}")
    Call<Boolean> atualizarAtividade(@Path("id") int idAtividade, @Body HorarioAtividade horarioAtividade);

    /**
     * Esta rota busca todas as atividades de um usuário com perfil coordenador
     *
     * @param idCoordenador Esse parâmetro representa o id de um usuário com perfil coordenador
     *                      cadastrado no banco de dados
     * @return List<Atividade> Lista de atividades relacionadas com o perfil do coordenador
     */
    @GET("atividades/coordenador/{id}")
    Call<List<Atividade>> getAtividadesFrequencia(@Path("id") int idCoordenador);

    /**
     * Esta rota busca todas as atividades que um usuário participou do início até o fim
     *
     * @param idUsuario Esse parâmetro representa o id de um usuário cadastrado no banco de dados
     * @return List<Atividade> Lista de todas as atividades que o usuário participou do início
     * até o fim
     */
    @GET("frequencia/{id}")
    Call<List<Atividade>> getAtividadesParticipadas(@Path("id") int idUsuario);

    /**
     * Esta rota busca a hora atual do servidor em que o serviço está sendo executado, com a
     * finalidade de utilizar somente um horário global, evitando assim inconsistências de horário
     * entre os dispositivos
     *
     * @return DateTime Horário atual do servidor
     */
    @GET("momento/")
    Call<DateTime> getMomento();

    /**
     * Esta rota busca todos os critérios de avaliação de atividades cadastrados no banco de dados
     *
     * @return List<CriterioAtividade> Lista de todos os critérios para avaliação das atividades
     */
    @GET("criterios/")
    Call<List<CriterioAtividade>> getCriterios();

    /**
     * Esta rota é utilizada para salvar a avaliação de uma atividade.
     *
     * @param avaliacaoAtividade Este parâmetro representa a atividade, professor e a nota atribuida
     *                           aos critérios na avaliação da atividade.
     * @return ResultadoAvaliacao O valor retornado representa o sucesso ou falha na avaliação da
     * atividade, apresentando erro em casos de erro no servidor ou quando a atividade já foi
     * avaliada pelo mesmo professor.
     */
    @POST("avaliacao")
    Call<ResultadoAvaliacao> avaliarAtividade(@Body AvaliacaoAtividade avaliacaoAtividade);

    /**
     * Essa rota é utilizada para verificar se uma atividade já foi avaliada previamente. Para isso,
     * só são necessários o id da atividade e o id do avaliador.
     *
     * @param avaliacaoAtividade Este parâmetro representa o id do avaliador e o id da atividade
     * @return Boolean O valor deste boolean representa se a atividade já foi avaliada, caso tenha
     * sido avaliada o valor será true, falso caso contrário.
     */
    @POST("avaliada/")
    Call<Boolean> verificarAtividadeJaAvaliada(@Body AvaliacaoAtividade avaliacaoAtividade);

    /**
     * Esta rota é utilizada para buscar todas as atividades que um professor avaliará de acordo com
     * os registros presentes no banco de dados.
     *
     * @param idProfessor Esse parâmetro representa o id de um usuário com perfil professor
     *                    cadastrado no bando de dados.
     * @return List<Atividade> Lista de todas as atividades que o professor pode avaliar
     */
    @GET("atividades/professor/{id}")
    Call<List<Atividade>> getAtividadesProfessor(@Path  ("id") int idProfessor);

 }
