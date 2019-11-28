package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante a avaliação de uma atividade
 */
public interface AvaliacaoListener {
    /**
     * Executado quando a atividade sendo avaliada já possui avaliação duplicada do avaliador
     * logado no aplicativo
     */
    void onAlreadyEvaluatedActivity();

    /**
     * Executado quando ocorre um erro na requisição ao webservice
     * @param message
     */
    void onError(String message);

    /**
     * Executado quando a avaliação é feita com sucesso
     */
    void onSuccess();

    /**
     * Executado quando a requisição de avaliação da atividade ainda está sendo realizada
     */
    void onLoading();

    /**
     * Executado quando a requisição de avaliação da atividade finaliza
     */
    void onDone();
}
