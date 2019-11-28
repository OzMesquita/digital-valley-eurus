package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante a alteração de senha do usuario
 */
public interface AlterarSenhaListener {
    /**
     * Executado quando a senha é alterada com sucesso
     */
    void onSuccess();

    /**
     * Executado quando ocorre um erro na requisição ao webservice
     * @param message
     */
    void onFailure(String message);

    /**
     * Executado quando a senha e confirmação de senha não são iguais
     */
    void onPasswordsDoesntMatch();

    /**
     * Executado quando o código de validação não existe no banco de dados
     */
    void onInvalidToken();

    /**
     * Executado quando um campo do formulário está vazio
     * @param fieldName
     */
    void onEmptyField(String fieldName);

    /**
     * Executado quando a senha é pequena
     */
    void onShortPassword();

    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice finaliza
     */
    void onDone();
}
