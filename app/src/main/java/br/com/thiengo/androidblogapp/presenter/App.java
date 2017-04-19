package br.com.thiengo.androidblogapp.presenter;

import android.app.Application;

import com.facebook.accountkit.AccountKit;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize( getApplicationContext(), null );
    }
}
