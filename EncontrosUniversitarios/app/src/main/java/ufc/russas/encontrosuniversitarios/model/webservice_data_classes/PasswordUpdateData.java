package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

public class PasswordUpdateData {
    private String token;
    private String password;

    public PasswordUpdateData(){}

    public PasswordUpdateData(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
