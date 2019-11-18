package ufc.russas.encontrosuniversitarios.model.exceptions;

public class EmptyFieldException extends Exception{
    private String campo;
    public EmptyFieldException(String campo){
        this.campo = campo;
    }

    @Override
    public String getMessage() {
        return campo;
    }
}
