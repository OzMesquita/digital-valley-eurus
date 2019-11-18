package ufc.russas.encontrosuniversitarios.model.webservice_data_classes;
import com.google.gson.annotations.SerializedName;

public class CheckInData {
    @SerializedName("id_usuario")
    private int userId;
    private int room;

    public CheckInData(int userId, int room) {
        this.userId = userId;
        this.room = room;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
