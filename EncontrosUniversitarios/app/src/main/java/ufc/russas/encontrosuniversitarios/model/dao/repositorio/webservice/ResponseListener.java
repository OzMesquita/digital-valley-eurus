package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

public interface ResponseListener<T> {
    void onSuccess(T response);
    void onFailure(String message);
}
