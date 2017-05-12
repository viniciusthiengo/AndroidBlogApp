package br.com.thiengo.androidblogapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.thiengo.androidblogapp.presenter.User;


public class SPUtil {
    private static final String PREF = "PREF";
    private static final int MODE = Context.MODE_PRIVATE;
    private static final String ID_KEY = "id";
    private static final String STATUS_KEY = "status";
    private static final String PRIMEIRA_ABERTURA_KEY = "primeira_abertura";


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

    public static void saveStatusCategoria(Context context, String key, boolean status){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        sp.edit().putBoolean(key, status).apply();
    }

    public static boolean statusCategoria(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        return sp.getBoolean(key, true);
    }


    public static void saveStatusPrimeiraAbertura(Context context, boolean status){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        sp.edit().putBoolean(PRIMEIRA_ABERTURA_KEY, status).apply();
    }

    public static boolean statusPrimeiraAbertura(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREF, MODE);
        return sp.getBoolean(PRIMEIRA_ABERTURA_KEY, false);
    }
}
