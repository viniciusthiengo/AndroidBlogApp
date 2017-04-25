package br.com.thiengo.androidblogapp.model;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.thiengo.androidblogapp.presenter.PresenterLogin;

/**
 * Created by viniciusthiengo on 19/04/17.
 */

public class CustomFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        /*
         * O TRECHO ABAIXO É RESPONSÁVEL POR PERMITIR QUE
         * UM NOVO TOKEN SEJA ENVIADO, OU SEJA, COLOCANDO
         * COMO FALSE O VALOR DO STATUS DO TOKEN
         * */
        SPUtil.saveStatusTokenServer( this, false );

        PresenterLogin presenterLogin = new PresenterLogin(this);
        presenterLogin.sendToken();
    }
}
