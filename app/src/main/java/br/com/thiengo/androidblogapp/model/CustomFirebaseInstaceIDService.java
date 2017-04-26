package br.com.thiengo.androidblogapp.model;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import br.com.thiengo.androidblogapp.presenter.PresenterLogin;

/**
 * Created by viniciusthiengo on 25/04/17.
 */

public class CustomFirebaseInstaceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        SPUtil.saveStatusTokenServer( this, false );

        PresenterLogin presenter = PresenterLogin.getInstance(this);
        presenter.sendToken();
    }
}
