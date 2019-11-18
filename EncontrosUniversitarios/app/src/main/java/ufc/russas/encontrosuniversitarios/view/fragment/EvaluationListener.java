package ufc.russas.encontrosuniversitarios.view.fragment;

public interface EvaluationListener {
    void onAlreadyEvaluatedActivity();
    void onError(String message);
    void onSuccess();
    void onLoading();
    void onDone();
}
