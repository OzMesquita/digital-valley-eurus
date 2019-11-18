package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

public class PasswordUpdateResponse {
    private boolean invalidToken;
    private String message;

    public PasswordUpdateResponse(){}

    public PasswordUpdateResponse(boolean invalidToken, String message) {
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
