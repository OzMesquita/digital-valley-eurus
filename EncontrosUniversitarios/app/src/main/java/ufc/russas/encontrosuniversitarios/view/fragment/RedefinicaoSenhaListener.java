package ufc.russas.encontrosuniversitarios.view.fragment;

public interface RedefinicaoSenhaListener {
    void onSuccess();
    void onFailure(String message);
    void onInvalidField();
    void onLoading();
    void onDone();
}
