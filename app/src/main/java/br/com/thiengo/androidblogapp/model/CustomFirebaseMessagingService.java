package br.com.thiengo.androidblogapp.model;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterPosts;
import br.com.thiengo.androidblogapp.view.PostsActivity;

/**
 * Created by viniciusthiengo on 25/04/17.
 */

public class CustomFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if( PostsActivity.isOpened
            && remoteMessage.getData().size() > 0
            && remoteMessage.getData().containsKey(Post.POST_KEY ) ){

            Gson gson = new Gson();
            Post p = gson.fromJson( remoteMessage.getData().get(Post.POST_KEY ), Post.class );

            PresenterPosts presenter = PresenterPosts.getInstance(this);
            presenter.updateListaRecycler( p );
        }
    }
}
