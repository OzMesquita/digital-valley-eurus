package ufc.russas.encontrosuniversitarios.model.webservice;

public class ValidacaoCheckInCheckOut {
    private boolean successful;
    private boolean checkedInOnDifferentRoom;
    private String message;
    private int previousRoom;

    public ValidacaoCheckInCheckOut(boolean successful, boolean checkedInOnDifferentRoom, String message, int previousRoom) {
        this.successful = successful;
        this.checkedInOnDifferentRoom = checkedInOnDifferentRoom;
        this.message = message;
        this.previousRoom = previousRoom;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean isCheckedInOnDifferentRoom() {
        return checkedInOnDifferentRoom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
