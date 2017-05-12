package br.com.thiengo.androidblogapp.model;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterPosts;
import br.com.thiengo.androidblogapp.view.LoginActivity;
import br.com.thiengo.androidblogapp.view.PostContentActivity;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class CustomFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Gson gson = new Gson();
        Post p = gson.fromJson( remoteMessage.getData().get(Post.POST_KEY ), Post.class );

        if( PostsActivity.isOpened
            && remoteMessage.getData().size() > 0
            && remoteMessage.getData().containsKey(Post.POST_KEY ) ){

            PresenterPosts presenter = PresenterPosts.getInstance(this);
            presenter.updateListaRecycler( p );
        }
        else if( !LoginActivity.isOpened
                && !PostContentActivity.isOpened ){
            Bitmap bitmap = null;
            try{
                bitmap = Picasso.with( this )
                    .load( p.getUriImagem() )
                    .get();
            }
            catch(Exception e){}

            int idCategoria = Integer.parseInt( remoteMessage.getData().get( Post.CATEGORIA_KEY ) );

            Intent intent = new Intent(this, PostContentActivity.class);
            intent.putExtra( Post.CATEGORIA_KEY, String.valueOf( idCategoria ) );
            intent.putExtra( Post.POST_KEY, p );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

            TaskStackBuilder pilha = TaskStackBuilder.create(this);
            pilha.addParentStack( PostContentActivity.class );
            pilha.addNextIntent( intent );

            PendingIntent pendingIntent = pilha.getPendingIntent( idCategoria, PendingIntent.FLAG_UPDATE_CURRENT );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setTicker( p.getTitulo() )
                .setContentTitle( p.getTitulo() )
                .setContentText( p.getSumario() )
                .setSmallIcon( p.getIcon( idCategoria ) )
                .setLargeIcon( bitmap )
                .setStyle( new NotificationCompat.BigPictureStyle().bigPicture(bitmap) )
                .setContentIntent( pendingIntent );

            NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notifyManager.notify( idCategoria, builder.build() );
        }
    }
}
