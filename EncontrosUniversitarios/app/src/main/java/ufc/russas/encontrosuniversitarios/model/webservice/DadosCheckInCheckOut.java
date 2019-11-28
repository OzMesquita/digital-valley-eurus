package ufc.russas.encontrosuniversitarios.model.webservice;
import com.google.gson.annotations.SerializedName;

public class DadosCheckInCheckOut {
    @SerializedName("id_usuario")
    private int idUsuario;
    private int sala;

    public DadosCheckInCheckOut(int idUsuario, int sala) {
        this.idUsuario = idUsuario;
        this.sala = sala;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
}
