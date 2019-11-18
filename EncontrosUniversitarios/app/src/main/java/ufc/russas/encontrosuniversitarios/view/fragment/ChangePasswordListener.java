package ufc.russas.encontrosuniversitarios.view.fragment;

public interface ChangePasswordListener {
    void onSuccess();
    void onFailure(String message);
    void onPasswordsDoesntMatch();
    void onInvalidToken();
    void onEmptyField(String fieldName);
    void onShortPassword();
    void onLoading();
    void onDone();
}
