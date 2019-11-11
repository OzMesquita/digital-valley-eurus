package ufc.russas.encontrosuniversitarios.model.webservice;

public class ResultadoAvaliacao {
    private Boolean alreadyEvaluatedActivity;
    private Boolean error;
    private String message;

    public ResultadoAvaliacao(Boolean alreadyEvaluatedActivity, Boolean error, String message) {
        this.alreadyEvaluatedActivity = alreadyEvaluatedActivity;
        this.error = error;
        this.message = message;
    }

    public Boolean getAlreadyEvaluatedActivity() {
        return alreadyEvaluatedActivity;
    }

    public void setAlreadyEvaluatedActivity(Boolean alreadyEvaluatedActivity) {
        this.alreadyEvaluatedActivity = alreadyEvaluatedActivity;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
