package ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice;

/**
 * Esta interface é utilizada, de forma genérica, para representar respostas as requisições realizadas
 * ao webservice. Esta interface pode trazer os dados requisitados, caso tudo ocorra como esperado
 * no método onSuccess, caso contrário o método onFailure será utilizado com uma mensagem de erro.
 * @param <T> Objeto genérico
 */
public interface ResponseListener<T> {
    void onSuccess(T response);
    void onFailure(String message);
}
