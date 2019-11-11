package ufc.russas.encontrosuniversitarios.model.webservice;

import com.google.gson.annotations.SerializedName;

public class DadosFrequenciaUsuario {
    @SerializedName("id_usuario")
    private Integer id;
    private String nome;

    public DadosFrequenciaUsuario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
