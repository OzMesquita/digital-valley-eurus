package ufc.russas.encontrosuniversitarios.view.fragment;

/**
 * Interface com as ações possíveis durante o check in e check out de um usuário em uma sala
 */
public interface CheckInCheckOutListener {
    /**
     * Executado quando o cadastro de usuário é realizado com sucesso
     * @param message
     */
    void onSuccess(String message);

    /**
     * Executado quando um usuário com faz check in em uma sala e tenta fazer check in em outra sala
     * sem ter realizado check out na sala prévia
     * @param message
     */
    void onCheckedInOnDifferentRoom(String message);

    /**
     * Executado quando um QRCode inválido fornecido para realizar check in ou check out
     * @param message
     */
    void onInvalidQRCode(String message);

    /**
     * Executado quando ocorre um erro na requisição
     * @param message
     */
    void onFailure(String message);
}
