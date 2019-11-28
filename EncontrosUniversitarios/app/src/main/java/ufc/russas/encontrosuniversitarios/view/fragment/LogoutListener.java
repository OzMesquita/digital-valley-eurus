package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante o logout do usuário
 */
public interface LogoutListener {
    /**
     * Executado quando o usuário faz log out no aplicativo
     */
    void onSuccessfulLogout();

    /**
     * Executado quando ocorre um erro ao deslogar
     */
    void onFailure();
}
