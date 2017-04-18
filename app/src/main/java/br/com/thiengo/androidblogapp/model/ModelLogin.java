package br.com.thiengo.androidblogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.androidblogapp.presenter.PresenterLogin;
import br.com.thiengo.androidblogapp.presenter.User;


public class ModelLogin {
    private static final String URI = "http://192.168.25.221:8888/blog-android-app/ctrl/CtrlUser.php";
    private static final String METODO_KEY = "metodo";
    private static final String ID_KEY = "id";
    private static final String EMAIL_KEY = "email";
    private static final String METODO_LOGIN = "login";

    private AsyncHttpClient asyncHttpClient;
    private PresenterLogin presenter;

    public ModelLogin(PresenterLogin presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void verifyLogin(User user) {
        RequestParams requestParams = new RequestParams();
        requestParams.put( METODO_KEY, METODO_LOGIN );
        requestParams.put( ID_KEY, user.getId() );
        requestParams.put( EMAIL_KEY, user.getEmail() );

        asyncHttpClient.post(
            presenter.getContext(),
            URI,
            requestParams,
            new JsonHttpRequest( presenter ));
    }
}
