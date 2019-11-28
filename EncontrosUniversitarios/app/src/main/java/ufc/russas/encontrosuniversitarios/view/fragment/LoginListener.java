package ufc.russas.encontrosuniversitarios.view.fragment;

import ufc.russas.encontrosuniversitarios.model.Usuario;

/**
 * Interface com as ações possíveis durante o login de usuário
 */
public interface LoginListener {
    /**
     * Executado quando o login é validado e o usuário pode logar no aplicativo
     * @param usuario
     */
    void onSuccess(Usuario usuario);

    /**
     * Executado quando a requisição ao webservice falha
     * @param message
     */
    void onFailure(String message);

    /**
     * Executado quando um dos campos do formulário de login está vazio
     * @param field
     */
    void onEmptyField(String field);

    /**
     * Executado quando a senha fornecida é inválida
     * @param message
     */
    void onInvalidPassword(String message);

    /**
     * Executado quando o email fornecido é inválido
     * @param message
     */
    void onInvalidEmail(String message);

    /**
     * Executado quando, após a requisição ao webservice, é identificado que o email fornecido não
     * possui cadastro no banco de dados
     */
    void onUnregisteredEmail();

    /**
     * Executado quando, após a requisição ao webservice, é identificado que a senha fornecida não
     * é igual a senha da conta com o email fornecido
     */
    void onWrongPassword();

    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice finaliza
     */
    void onDone();
}
