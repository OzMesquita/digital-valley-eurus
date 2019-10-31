package ufc.russas.encontrosuniversitarios.model.exceptions;

public class CampoVazioException extends Exception{
    private String campo;
    public CampoVazioException(String campo){
        this.campo = campo;
    }

    @Override
    public String getMessage() {
        return campo;
    }
}
