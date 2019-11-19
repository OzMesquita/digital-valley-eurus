package ufc.russas.encontrosuniversitarios.helper;

import android.content.Context;
import android.content.SharedPreferences;

import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySharedPreferences {
    private final String USUARIO_ID = "USUARIOID";
    private final String USUARIO_NOME = "USUARIONOME";
    private final String USUARIO_EMAIL = "USUARIOEMAIL";
    private final String USUARIO_NIVEL_ACESSO = "USUARIONIVELACESSO";
    private final String ATIVIDADES_COORDENADOR = "ATIVIDADESCOORDENADOR";
    private final String SALA = "SALA";
    private static final String MINHAS_PREFERENCIAS = "EURUSSAS";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static MySharedPreferences mySharedPreferences;

    private MySharedPreferences(Context context) {
        preferences = context.getSharedPreferences(MINHAS_PREFERENCIAS,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static MySharedPreferences getInstance(Context context) {
        if(mySharedPreferences == null){
            mySharedPreferences = new MySharedPreferences(context);
        }
        return mySharedPreferences;
    }

    public void setUserData(Usuario usuario){
        editor.putInt(USUARIO_ID,usuario.getId());
        editor.putString(USUARIO_NOME,usuario.getNome());
        editor.putInt(USUARIO_NIVEL_ACESSO,usuario.getNivelAcesso());
        editor.putString(USUARIO_EMAIL,usuario.getEmail());
        editor.commit();
    }

    public boolean clearData(){
        editor.clear();
        return editor.commit();
    }

    public void setRoom(int idRoom){
        editor.putInt(SALA,idRoom);
        editor.commit();
    }

    public void setCoordinatorActivities(List<Atividade> atividadesCoordenador) {
        HashSet<String> values = new HashSet<>();
        for(Atividade a:atividadesCoordenador){
            values.add(String.valueOf(a.getId()));
        }
        editor.putStringSet(ATIVIDADES_COORDENADOR,values);
        editor.commit();
    }

    public Set<String> getCoordinatorActivities(){
        return preferences.getStringSet(ATIVIDADES_COORDENADOR,null);
    }

    public int getUserId(){
        return preferences.getInt(USUARIO_ID,-1);
    }

    public int getRoomId(){
        return preferences.getInt(SALA,-1);
    }

    public String getUserName(){
        return preferences.getString(USUARIO_NOME,null);
    }

    public int getUserAccessLevel(){
        return preferences.getInt(USUARIO_NIVEL_ACESSO,-1);
    }

    public String getUserEmail() { return preferences.getString(USUARIO_EMAIL,null);
    }
}
