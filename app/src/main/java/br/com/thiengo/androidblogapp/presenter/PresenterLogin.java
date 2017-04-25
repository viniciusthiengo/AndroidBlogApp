package br.com.thiengo.androidblogapp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import br.com.thiengo.androidblogapp.model.ModelLogin;
import br.com.thiengo.androidblogapp.model.SPUtil;
import br.com.thiengo.androidblogapp.view.LoginActivity;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class PresenterLogin {
    private ModelLogin model;
    private Context context;


    public PresenterLogin( Context c ){
        context = c;
        model = new ModelLogin( this );
    }

    public Context getContext() {
        return context;
    }

    public void verifyLogin( User user ) {
        model.verifyLogin( user );
    }

    public void resultLogin( User user ) {
        if( user.isLogged() ){
            SPUtil.saveUserId(context, user );

            Intent it = new Intent( context, PostsActivity.class );
            it.putExtra( User.KEY, user );
            it.putExtra( Post.CATEGORIA_KEY, getCategoria() );

            context.startActivity( it );
            ((Activity)context).finish();
        }
    }

    private int getCategoria() {
        Intent it = ((Activity) context).getIntent();

        if( it != null
                && it.hasExtra( Post.CATEGORIA_KEY ) ){

            return Integer.parseInt( it.getStringExtra( Post.CATEGORIA_KEY ) );
        }
        return 0;
    }

    public void sendToken() {
        User user = new User();
        user.setId( SPUtil.getUserId(context) );
        user.setToken( FirebaseInstanceId.getInstance().getToken() );

        if( !SPUtil.statusTokenServer(context)
            && user.ehValidoEnviarToken() ){

            model.sendToken( user );
        }
    }
}
