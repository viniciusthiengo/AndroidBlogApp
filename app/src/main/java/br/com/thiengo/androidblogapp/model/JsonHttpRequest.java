package br.com.thiengo.androidblogapp.model;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterLogin;
import br.com.thiengo.androidblogapp.presenter.PresenterPosts;
import br.com.thiengo.androidblogapp.presenter.User;
import cz.msebera.android.httpclient.Header;


class JsonHttpRequest extends JsonHttpResponseHandler {
    private PresenterPosts presenterPosts;
    private PresenterLogin presenterLogin;


    JsonHttpRequest( PresenterPosts presenterPosts){
        this.presenterPosts = presenterPosts;
    }

    JsonHttpRequest( PresenterLogin presenterLogin){
        this.presenterLogin = presenterLogin;
    }

    @Override
    public void onStart() {
        if( presenterPosts != null ){
            presenterPosts.showProgressBar( true );
        }
    }

    @Override
    public void onFinish() {
        if( presenterPosts != null ){
            presenterPosts.showProgressBar( false );
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Post> posts = new ArrayList<>();
        Post p;

        for( int i = 0; i < response.length(); i++ ){
            try{
                p = gson.fromJson( response.getJSONObject( i ).toString(), Post.class );
                posts.add( p );
            }
            catch(JSONException e){}
        }
        presenterPosts.updateListaRecycler( posts );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Gson gson = new Gson();
        User user = gson.fromJson( response.toString(), User.class );

        presenterLogin.resultLogin( user );
    }


}
