package com.example.encontrosuniversitarios.model;

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

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isCheckedInOnDifferentRoom() {
        return checkedInOnDifferentRoom;
    }

    public void setCheckedInOnDifferentRoom(boolean checkedInOnDifferentRoom) {
        this.checkedInOnDifferentRoom = checkedInOnDifferentRoom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
    }
}
