package ufc.russas.encontrosuniversitarios.view.fragment;

public interface PasswordRedefinitionListener {
    void onSuccess();
    void onFailure(String message);
    void onInvalidField();
    void onLoading();
    void onDone();
}
