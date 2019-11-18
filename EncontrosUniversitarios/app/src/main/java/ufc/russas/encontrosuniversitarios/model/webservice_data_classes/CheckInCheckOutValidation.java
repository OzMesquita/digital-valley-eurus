package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;

public class CheckInCheckOutValidation {
    private boolean successful;
    private boolean checkedInOnDifferentRoom;
    private String message;
    private int previousRoom;

    public CheckInCheckOutValidation(boolean successful, boolean checkedInOnDifferentRoom, String message, int previousRoom) {
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
