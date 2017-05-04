package br.com.thiengo.androidblogapp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import br.com.thiengo.androidblogapp.R;
import br.com.thiengo.androidblogapp.model.ModelLogin;
import br.com.thiengo.androidblogapp.model.SPUtil;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class PresenterNotificacao {
    private Context context;

    public PresenterNotificacao( Context c ){
        context = c;
    }

    public void configPrimeiraAbertura() {
        if( !SPUtil.statusPrimeiraAbertura(context) ){
            return;
        }

        onCheckedChanged( getCategoria(R.id.categoria_1), true);
        onCheckedChanged( getCategoria(R.id.categoria_2), true);
        onCheckedChanged( getCategoria(R.id.categoria_3), true);
        onCheckedChanged( getCategoria(R.id.categoria_4), true);
        onCheckedChanged( getCategoria(R.id.categoria_5), true);

        SPUtil.saveStatusPrimeiraAbertura(context, false);
    }

    public void setCheckBoxConf( int id ){
        Activity a = (Activity) context;
        String categoria = getCategoria( id );
        CheckBox cb = (CheckBox) a.findViewById( id );

        cb.setOnCheckedChangeListener( (CompoundButton.OnCheckedChangeListener) context );
        cb.setChecked( SPUtil.statusCategoria(context, categoria) );
    }

    private String getCategoria( int id ){
        switch( id ){
            case R.id.categoria_1:
                return "categoria_1";
            case R.id.categoria_2:
                return "categoria_2";
            case R.id.categoria_3:
                return "categoria_3";
            case R.id.categoria_4:
                return "categoria_4";
            default:
                return "categoria_5";
        }
    }

    public void onCheckedChanged(CompoundButton cb, boolean b) {
        String categoria = getCategoria( cb.getId() );
        onCheckedChanged(categoria, b);
    }

    private void onCheckedChanged(String categoria, boolean status) {
        if( status ){
            FirebaseMessaging.getInstance().subscribeToTopic( categoria );
        }
        else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic( categoria );
        }
        SPUtil.saveStatusCategoria(context, categoria, status);
    }
}
