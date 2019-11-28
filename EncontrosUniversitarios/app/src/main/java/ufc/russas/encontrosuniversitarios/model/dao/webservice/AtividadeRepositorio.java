package ufc.russas.encontrosuniversitarios.model.dao.webservice;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.AvaliacaoAtividade;
import ufc.russas.encontrosuniversitarios.model.CriterioAtividade;
import ufc.russas.encontrosuniversitarios.model.webservice.ResultadoAvaliacao;
import ufc.russas.encontrosuniversitarios.model.HorarioAtividade;
import ufc.russas.encontrosuniversitarios.model.dao.database.WebServiceDatabase;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtividadeRepositorio{

    private static AtividadeRepositorio atividadeRepositorio;
    private AtividadeService atividadeService;


    private AtividadeRepositorio(){
        atividadeService = WebServiceDatabase.getInstance().getAtividadeService();
    }

    public static AtividadeRepositorio getInstance(){
        if(atividadeRepositorio==null){
            atividadeRepositorio = new AtividadeRepositorio();
        }
        return atividadeRepositorio;
    }

    /**
     * Este método busca todas as atividades
     * @param listener Encapsula o comportamento da view
     */
    public void buscar(final ResponseListener listener) {
        atividadeService.getAtividades().enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    /**
     * Este método busca todas as atividades do dia atual
     * @param listener Encapsula o comportamento da view
     */
    public void buscarAtividadesDoDia(final ResponseListener listener){
        atividadeService.getAtividadesDoDia().enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    /**
     * Este método busca todas as atividades que o coordenador é responsável
     * @param listener Encapsula o comportamento da view
     * @param idCoordenador id do coordenador logado no aplicativo
     */
    public void buscarAtividadesFrequencia(final ResponseListener listener, int idCoordenador) {
        atividadeService.getAtividadesFrequencia(idCoordenador).enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    /**
     * Este método busca todas as atividades que o usuário, com o idUsuário, participou durante o
     * evento, considerando as que ele participou desde seu início até seu fim
     * @param listener Encapsula o comportamento da view
     * @param idUsuario Id do usuário logado no aplicativo
     */
    public void buscarAtividadesParticipadas(final ResponseListener listener, int idUsuario) {
        atividadeService.getAtividadesParticipadas(idUsuario).enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    /**
     * Este método atualiza o horário de início ou fim da atividade passada como parâmetro.
     * @param atividade Atividade para atualizar o horário de início ou fim
     * @param isHorarioInicio Váriável que indica se deve ser alterado o horário de início ou fim,
     *                        true para alterar o horário de início, false para alterar o horário
     *                        final
     * @param listener Encapsula o comportamento da view
     */
    public void atualizarAtividade(Atividade atividade, boolean isHorarioInicio, final ResponseListener listener){
        DateTime horario = isHorarioInicio ? atividade.getHorarioInicio() : atividade.getHorarioFinal();
        atividadeService.atualizarAtividade(atividade.getId(),new HorarioAtividade(isHorarioInicio,horario)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }

    /**
     * Este método busca a hora atual do servidor, de forma a padronizar todos os horários do aplicativo
     * @param listener, Encapsula o comportamento da view
     */
    public void getMomento(final ResponseListener listener){
        atividadeService.getMomento().enqueue(new Callback<DateTime>() {
            @Override
            public void onResponse(Call<DateTime> call, Response<DateTime> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DateTime> call, Throwable t) {
                listener.onFailure("Erro momento");
            }
        });
    }

    /**
     * Este método busca todos os critérios de avaliação de atividade cadastrados no banco de dados
     * @param listener Encapsula o comportamento da view
     */
    public  void getCriterios(final ResponseListener listener){
        atividadeService.getCriterios().enqueue(new Callback<List<CriterioAtividade>>() {
            @Override
            public void onResponse(Call<List<CriterioAtividade>> call, Response<List<CriterioAtividade>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<CriterioAtividade>> call, Throwable t) {
                listener.onFailure("Erro ao buscar critérios do banco");
            }
        });
    }

    /**
     * Este método envia a avaliação de uma atividade para ser salva no banco de dados
     * @param listener Encapsula o comportamento da view
     * @param avaliacaoAtividade Objeto que possui a atividade, avaliador, e critérios com suas
     *                           devidas notas para serem salvas
     */
    public void avaliarAtividade(final ResponseListener listener, AvaliacaoAtividade avaliacaoAtividade){
        atividadeService.avaliarAtividade(avaliacaoAtividade).enqueue(new Callback<ResultadoAvaliacao>() {
            @Override
            public void onResponse(Call<ResultadoAvaliacao> call, Response<ResultadoAvaliacao> response) {
                listener.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<ResultadoAvaliacao> call, Throwable t) {
                listener.onFailure("Erro ao avaliar atividade");
            }
        });
    }

    /**
     * Este método é utilizado para verificar se uma atividade já possui avaliação do avaliador
     * contido no objeto avaliacaoAtividade
     * @param listener Encapsula o comportamento da view
     * @param avaliacaoAtividade Objeto que possui o id da atividade e id do avaliador
     */
    public void verificarAtividadeJaAvaliada(final ResponseListener listener, AvaliacaoAtividade avaliacaoAtividade){
        atividadeService.verificarAtividadeJaAvaliada(avaliacaoAtividade).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure("Erro ao verificar se a atividade já está avaliada");
            }
        });
    }

    /**
     * Este método busca todas as atividades que o professor irá avaliar
     * @param listener Encapsula o comportamento da view
     * @param idProfessor Id do professor para buscar as atividades a serem avaliadas
     */
    public void getAtividadesProfessor(final ResponseListener listener, int idProfessor) {
        atividadeService.getAtividadesProfessor(idProfessor).enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                listener.onSuccess(response.body());
            }

            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                listener.onFailure("Erro ao executar requisição");
            }
        });
    }
}
