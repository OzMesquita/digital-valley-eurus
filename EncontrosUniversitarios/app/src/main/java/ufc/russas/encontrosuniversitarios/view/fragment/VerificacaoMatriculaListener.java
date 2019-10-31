package ufc.russas.encontrosuniversitarios.view.fragment;

public interface VerificacaoMatriculaListener {

    void onInvalidMatricula();
    void onFailure();
    void onValidMatricula();
    void onUnregisteredMatricula();
    void onLoading();
    void onDone();
}
