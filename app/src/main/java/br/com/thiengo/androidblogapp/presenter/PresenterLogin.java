package br.com.thiengo.androidblogapp.presenter;

import android.app.Activity;
import android.content.Intent;

import br.com.thiengo.androidblogapp.model.ModelLogin;
import br.com.thiengo.androidblogapp.view.LoginActivity;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class PresenterLogin {
    private ModelLogin model;
    private LoginActivity activity;


    public PresenterLogin( LoginActivity activity ){
        this.activity = activity;
        model = new ModelLogin( this );
    }

    public Activity getContext() {
        return activity;
    }

    public void verifyLogin( User user ) {
        model.verifyLogin( user );
    }

    public void resultLogin( User user ) {
        if( user.isLogged() ){
            Intent it = new Intent(activity, PostsActivity.class);
            it.putExtra( User.KEY, user);

            activity.startActivity( it );
            activity.finish();
        }
    }
}
