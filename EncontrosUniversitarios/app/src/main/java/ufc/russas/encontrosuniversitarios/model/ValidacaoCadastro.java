package ufc.russas.encontrosuniversitarios.model;

public class ValidacaoCadastro {
    private boolean alreadyTakenEmail;
    private boolean alreadyTakenMatricula;
    private String message;

    public ValidacaoCadastro(boolean alreadyTakenEmail, boolean alreadyTakenMatricula, String message) {
        this.alreadyTakenEmail = alreadyTakenEmail;
        this.alreadyTakenMatricula = alreadyTakenMatricula;
        this.message = message;
    }

    public boolean isAlreadyTakenEmail() {
        return alreadyTakenEmail;
    }

    public void setAlreadyTakenEmail(boolean alreadyTakenEmail) {
        this.alreadyTakenEmail = alreadyTakenEmail;
    }

    public boolean isAlreadyTakenMatricula() {
        return alreadyTakenMatricula;
    }

    public void setAlreadyTakenMatricula(boolean alreadyTakenMatricula) {
        this.alreadyTakenMatricula = alreadyTakenMatricula;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
