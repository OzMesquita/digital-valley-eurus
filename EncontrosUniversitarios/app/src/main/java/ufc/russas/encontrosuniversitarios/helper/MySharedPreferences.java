package ufc.russas.encontrosuniversitarios.helper;

import android.content.Context;
import android.content.SharedPreferences;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySharedPreferences {
    private final String USER_ID = "USERID";
    private final String USER_NAME = "USERNAME";
    private final String USER_EMAIL = "USEREMAIL";
    private final String USER_ACCESS_LEVEL = "USERACCESSLEVEL";
    private final String COORDINATOR_ACTIVITIES = "COORDINATORACTIVITIES";
    private final String ROOM = "ROOM";
    private static final String MY_PREFERENCES = "EURUSSAS";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static MySharedPreferences mySharedPreferences;

    private MySharedPreferences(Context context) {
        preferences = context.getSharedPreferences(MY_PREFERENCES,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static MySharedPreferences getInstance(Context context) {
        if(mySharedPreferences == null){
            mySharedPreferences = new MySharedPreferences(context);
        }
        return mySharedPreferences;
    }

    public void setUserData(User user){
        editor.putInt(USER_ID, user.getId());
        editor.putString(USER_NAME, user.getNome());
        editor.putInt(USER_ACCESS_LEVEL, user.getNivelAcesso());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.commit();
    }

    public boolean clearData(){
        editor.clear();
        return editor.commit();
    }

    public void setRoom(int idRoom){
        editor.putInt(ROOM,idRoom);
        editor.commit();
    }

    public void setCoordinatorActivities(List<Activity> coordinatorActivities) {
        HashSet<String> values = new HashSet<>();
        for(Activity a:coordinatorActivities){
            values.add(String.valueOf(a.getId()));
        }
        editor.putStringSet(COORDINATOR_ACTIVITIES,values);
        editor.commit();
    }

    public Set<String> getCoordinatorActivities(){
        return preferences.getStringSet(COORDINATOR_ACTIVITIES,null);
    }

    public int getUserId(){
        return preferences.getInt(USER_ID,-1);
    }

    public int getRoomId(){
        return preferences.getInt(ROOM,-1);
    }

    public String getUserName(){
        return preferences.getString(USER_NAME,null);
    }

    public int getUserAccessLevel(){
        return preferences.getInt(USER_ACCESS_LEVEL,-1);
    }

    public String getUserEmail() { return preferences.getString(USER_EMAIL,null);
    }
}
