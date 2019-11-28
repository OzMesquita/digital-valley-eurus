package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante a alteração de senha do usuario
 */
public interface CadastroUsuarioListener {
    /**
     * Executado quando o cadastro de usuário é realizado com sucesso
     * @param message
     */
    void onSuccess(String message);

    /**
     * Executado quando um campo do formulário de cadastro está vazio
     * @param message
     */
    void onEmptyField(String message);

    /**
     * Executado quando o email fornecido não está no formato correto
     * @param message
     */
    void onInvalidEmail(String message);

    /**
     * Executado quando a senha fornecida não é válida
     * @param message
     */
    void onInvalidPassword(String message);

    /**
     * Executado quando a matrícula fornecida não é válida
     * @param message
     */
    void onInvalidMatricula(String message);

    /**
     * Executado quando a requisição é feita, porém já existe uma conta cadastrada com o email
     * fornecido
     */
    void onAlreadyTakenEmail();

    /**
     * Executado quando a requisição é feita, porém já existe uma conta cadastrada com a matrícula
     * fornecida
     */
    void onAlreadyTakenMatricula();

    /**
     * Executado quando ocorre um erro na requisição
     */
    void onFailure();

    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice finaliza
     */
    void onDone();
}
