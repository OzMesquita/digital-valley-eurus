package ufc.russas.encontrosuniversitarios.model;

import ufc.russas.encontrosuniversitarios.model.exceptions.CampoVazioException;
import ufc.russas.encontrosuniversitarios.model.exceptions.EmailInvalidoException;
import ufc.russas.encontrosuniversitarios.model.exceptions.MatriculaInvalidaException;
import ufc.russas.encontrosuniversitarios.model.exceptions.SenhaInvalidaException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    public final static int NIVEL_ACESSO_PARTICIPANTE = 0;

    @Expose
    @SerializedName("id_usuario")
    private Integer id;
    @Expose
    @SerializedName("nome")
    private String nome;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("matricula")
    private String matricula;
    @Expose
    @SerializedName("senha")
    private String senha;
    @Expose
    @SerializedName("nivel_acesso")
    private int nivelAcesso;

    public Usuario(){}

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(int id,String nome,int nivelAcesso) {
        this(nome);
        this.setId(id);
        this.setNivelAcesso(nivelAcesso);
    }

    public Usuario(String nome, String email, String matricula, String senha) throws CampoVazioException, EmailInvalidoException, SenhaInvalidaException, MatriculaInvalidaException {
        this.setNome(nome);
        this.setEmail(email);
        this.setMatricula(matricula);
        this.setSenha(senha);
    }

    public Usuario(String email, String senha) throws CampoVazioException, SenhaInvalidaException, EmailInvalidoException {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoVazioException {
        if(nome==null || nome.equals("")) throw new CampoVazioException("Nome");
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws CampoVazioException, EmailInvalidoException {
        if(email==null || email.equals("")) throw new CampoVazioException("Email");
        if(Validador.validarEmail(email)) {
            this.email = email;
        }else{
            throw new EmailInvalidoException("Esse email não é válido, verifique o email digitado");
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) throws CampoVazioException, MatriculaInvalidaException {
        if(matricula==null || matricula.equals("")) {
            throw new CampoVazioException("Matricula");
        }
        if(Validador.validarMatricula(matricula)) {
            this.matricula = matricula;
        }else{
            throw new MatriculaInvalidaException("A matrícula digitada deve ter 6 caracteres numéricos");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws CampoVazioException, SenhaInvalidaException {
        if(senha==null || senha.equals("")) {
            throw new CampoVazioException("Senha");
        }
        if(Validador.validarSenha(senha)){
            this.senha = senha;
        }else{
            throw new SenhaInvalidaException("A senha deve conter no mínimo 6 caracteres");
        }
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
