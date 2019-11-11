package ufc.russas.encontrosuniversitarios.model.webservice;

public class AlterarSenhaResponse {
    private boolean invalidToken;
    private String message;

    public AlterarSenhaResponse(){}

    public AlterarSenhaResponse(boolean invalidToken, String message) {
        this.invalidToken = invalidToken;
        this.message = message;
    }

    public boolean isInvalidToken() {
        return invalidToken;
    }

    public void setInvalidToken(boolean invalidToken) {
        this.invalidToken = invalidToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
