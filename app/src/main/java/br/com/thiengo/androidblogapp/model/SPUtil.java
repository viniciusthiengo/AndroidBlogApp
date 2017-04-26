package br.com.thiengo.androidblogapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.thiengo.androidblogapp.presenter.User;

/**
 * Created by viniciusthiengo on 25/04/17.
 */

public class SPUtil {
    private static final String PREF = "PREF";
    private static final int MODE = Context.MODE_PRIVATE;
    private static final String ID_KEY = "id";
    private static final String STATUS_KEY = "status";


    public static void saveUserId(Context context, User user){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        sp.edit().putString(ID_KEY, user.getId()).apply();
    }

    public static String getUserId( Context context ){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        return sp.getString(ID_KEY, null);
    }

    public static void saveStatusTokenServer(Context context, boolean status){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        sp.edit().putBoolean(STATUS_KEY, status).apply();
    }

    public static boolean statusTokenServer(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        return sp.getBoolean(STATUS_KEY, false);
    }
}
