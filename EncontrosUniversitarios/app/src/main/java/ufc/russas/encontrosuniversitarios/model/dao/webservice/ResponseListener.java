package ufc.russas.encontrosuniversitarios.model.dao.webservice;

public interface ResponseListener<T> {
    void onSuccess(T response);
    void onFailure(String message);
}
