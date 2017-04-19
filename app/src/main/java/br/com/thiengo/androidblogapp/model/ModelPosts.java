package br.com.thiengo.androidblogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.androidblogapp.presenter.PresenterPosts;


public class ModelPosts {
    private static final String CTRL = "CtrlPost.php";
    private static final String METODO_POSTS = "get-posts";

    private AsyncHttpClient asyncHttpClient;
    private PresenterPosts presenter;


    public ModelPosts(PresenterPosts presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void retrievePosts() {
        RequestParams params = new RequestParams();
        params.put( JsonHttpRequest.METODO_KEY, METODO_POSTS );

        asyncHttpClient.post(
            presenter.getContext(),
            JsonHttpRequest.URI + CTRL,
            params,
            new JsonHttpRequest( presenter ));
    }
}
