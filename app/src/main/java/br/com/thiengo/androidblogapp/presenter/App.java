package br.com.thiengo.androidblogapp.presenter;

import android.app.Application;

import com.facebook.accountkit.AccountKit;

/**
 * Created by viniciusthiengo on 18/04/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize( getApplicationContext(), null );
    }
}
