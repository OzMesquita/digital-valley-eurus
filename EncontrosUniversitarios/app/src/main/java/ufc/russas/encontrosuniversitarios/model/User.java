package ufc.russas.encontrosuniversitarios.model;

import ufc.russas.encontrosuniversitarios.model.exceptions.EmptyFieldException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidEmailException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidMatriculaException;
import ufc.russas.encontrosuniversitarios.model.exceptions.InvalidPasswordException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    public final static int NIVEL_ACESSO_PARTICIPANTE = 0;
    public final static int NIVEL_ACESSO_COORDENADOR = 1;

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

    public User(){}

    public User(String nome) {
        this.nome = nome;
    }

    public User(int id, String nome, int nivelAcesso) {
        this(nome);
        this.setId(id);
        this.setNivelAcesso(nivelAcesso);
    }

    public User(String nome, String email, String matricula, String senha) throws EmptyFieldException, InvalidEmailException, InvalidPasswordException, InvalidMatriculaException {
        this.setNome(nome);
        this.setEmail(email);
        this.setMatricula(matricula);
        this.setSenha(senha);
    }

    public User(String email, String senha) throws EmptyFieldException, InvalidPasswordException, InvalidEmailException {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws EmptyFieldException {
        if(nome==null || nome.equals("")) throw new EmptyFieldException("Nome");
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmptyFieldException, InvalidEmailException {
        if(email==null || email.equals("")) throw new EmptyFieldException("Email");
        if(Validator.validarEmail(email)) {
            this.email = email;
        }else{
            throw new InvalidEmailException("Esse email não é válido, verifique o email digitado");
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

    public void setMatricula(String matricula) throws EmptyFieldException, InvalidMatriculaException {
        if(matricula==null || matricula.equals("")) {
            throw new EmptyFieldException("Matricula");
        }
        if(Validator.validarMatricula(matricula)) {
            this.matricula = matricula;
        }else{
            throw new InvalidMatriculaException("A matrícula digitada deve ter 6 caracteres numéricos");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws EmptyFieldException, InvalidPasswordException {
        if(senha==null || senha.equals("")) {
            throw new EmptyFieldException("Senha");
        }
        if(Validator.validarSenha(senha)){
            this.senha = senha;
        }else{
            throw new InvalidPasswordException("A senha deve conter no mínimo 6 caracteres");
        }
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
