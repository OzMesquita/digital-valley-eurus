package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante o carregamento de atividades do banco de dados
 */
public interface AtividadesListener {
    /**
     * Executado quando a requisição ao webservice está sendo feita
     */
    void onLoading();

    /**
     * Executado quando a requisição ao webservice finaliza
     */
    void onDone();
}
