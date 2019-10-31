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

public interface AtividadeService {
    @GET("atividades")
    Call<List<Atividade>> getAtividades();

    @GET("hoje/atividades/")
    Call<List<Atividade>> getAtividadesDoDia();

    @PUT("atividades/{id}")
    Call<Boolean> atualizarAtividade(@Path("id") int idAtividade, @Body Inicio inicio);

    @GET("atividades/coordenador/{id}")
    Call<List<Atividade>> getAtividadesFrequencia(@Path("id") int idCoordenador);

    @GET("frequencia/{id}")
    Call<List<Atividade>> getAtividadesParticipadas(@Path("id") int idUsuario);

    @GET("momento/")
    Call<DateTime> getMomento();

    @GET("criterios/")
    Call<List<CriterioAtividade>> getCriterios();

    @POST("avaliacao")
    Call<ResultadoAvaliacao> avaliarAtividade(@Body AvaliacaoAtividade avaliacaoAtividade);

    @POST("avaliada/")
    Call<Boolean> verificarAtividadeJaAvaliada(@Body AvaliacaoAtividade avaliacaoAtividade);

    @GET("atividades/professor/{id}")
    Call<List<Atividade>> getAtividadesProfessor(@Path  ("id") int idProfessor);

 }
