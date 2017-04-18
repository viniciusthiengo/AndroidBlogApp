package br.com.thiengo.androidblogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.androidblogapp.presenter.PresenterPosts;


public class ModelPosts {
    private static final String URI = "http://192.168.25.221:8888/blog-android-app/ctrl/CtrlPost.php";
    private static final String METODO_KEY = "metodo";
    private static final String METODO_POSTS = "get-posts";

    private AsyncHttpClient asyncHttpClient;
    private PresenterPosts presenter;

    public ModelPosts(PresenterPosts presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void retrievePosts() {
        RequestParams requestParams = new RequestParams();
        requestParams.put( METODO_KEY, METODO_POSTS );

        asyncHttpClient.post(
            presenter.getContext(),
            URI,
            requestParams,
            new JsonHttpRequest( presenter ));
    }
}
