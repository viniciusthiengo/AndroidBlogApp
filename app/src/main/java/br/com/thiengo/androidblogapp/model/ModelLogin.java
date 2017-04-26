package br.com.thiengo.androidblogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.androidblogapp.presenter.PresenterLogin;
import br.com.thiengo.androidblogapp.presenter.User;


public class ModelLogin {
    private static final String CTRL = "CtrlUser.php";
    private static final String ID_KEY = "id";
    private static final String EMAIL_KEY = "email";
    private static final String METODO_LOGIN = "login";
    private static final String TOKEN_KEY = "token";
    private static final String METODO_TOKEN = "registrar-token-notificacao";

    private AsyncHttpClient asyncHttpClient;
    private PresenterLogin presenter;


    public ModelLogin(PresenterLogin presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void verifyLogin(User user) {
        RequestParams params = new RequestParams();
        params.put( JsonHttpRequest.METODO_KEY, METODO_LOGIN );
        params.put( ID_KEY, user.getId() );
        params.put( EMAIL_KEY, user.getEmail() );

        asyncHttpClient.post(
            presenter.getContext(),
            JsonHttpRequest.URI + CTRL,
            params,
            new JsonHttpRequest( presenter ));
    }

    public void sendToken(User user) {
        RequestParams params = new RequestParams();
        params.put( JsonHttpRequest.METODO_KEY, METODO_TOKEN );
        params.put( ID_KEY, user.getId() );
        params.put( TOKEN_KEY, user.getToken() );

        asyncHttpClient.post(
                presenter.getContext(),
                JsonHttpRequest.URI + CTRL,
                params,
                new JsonHttpRequest( presenter ));
    }
}
