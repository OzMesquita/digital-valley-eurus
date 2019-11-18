package ufc.russas.encontrosuniversitarios.model;

public class Validator {

    public static boolean validarEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean validarSenha(String senha){
        return senha.length()>=6;
    }

    public static boolean validarMatricula(String matricula){
        return matricula.matches("[0-9]{6}");
    }

}
