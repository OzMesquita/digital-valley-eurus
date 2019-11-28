package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante a redefinição de senha
 */
public interface RedefinicaoSenhaListener {
    /**
     * Executado quando a redefinição de senha é executada com sucesso
     */
    void onSuccess();

    /**
     * Executado quando a redefinição de senha falha
     * @param message
     */
    void onFailure(String message);

    /**
     * Executado quando um campo do formulário de redefinição de senha está inválido
     */
    void onInvalidField();

    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice finaliza
     */
    void onDone();
}
