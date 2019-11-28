package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante a redefinição de senha
 */
public interface VerificacaoMatriculaListener {

    /**
     * Executado quando a matricula fornecida é inválida
     */
    void onInvalidMatricula();

    /**
     * Executado quando a requisição ao webservice falha
     */
    void onFailure();

    /**
     * Executado quando, feita a requisição ao webservice, a matrícula fornecida existe no banco de
     * dados
     */
    void onValidMatricula();

    /**
     * Executado quandoc, feita a requisição ao webservice, a matrícula fornecida não está
     * cadastrada no banco de dados
     */
    void onUnregisteredMatricula();

    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice falha
     */
    void onDone();
}
