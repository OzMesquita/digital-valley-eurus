package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

public class MatriculaVerification {
    private String message;
    private Data data;
    private String status;

    public MatriculaVerification() {
    }

    public MatriculaVerification(String message, Data data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Data data) {
        this.data = data;
    }
}


