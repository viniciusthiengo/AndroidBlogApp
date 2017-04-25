package br.com.thiengo.androidblogapp.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.androidblogapp.model.ModelPosts;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class PresenterPosts {
    private static PresenterPosts instance;
    private User user;
    private ModelPosts model;
    private Context context;
    private ArrayList<Post> posts = new ArrayList<>();


    public static PresenterPosts getInstance( Context c ){
        if( instance == null ){
            instance = new PresenterPosts( c );
        }
        return instance;
    }

    private PresenterPosts( Context a ){
        context = a;
        user = ((Activity) a).getIntent().getParcelableExtra(User.KEY);
        model = new ModelPosts( this );
    }

    public User getUser(){
        return user;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void retrievePosts() {
        model.retrievePosts();
    }

    public void updateListaRecycler(Object object) {
        List<Post> postsCarregados = (List<Post>) object;
        posts.clear();
        posts.addAll( postsCarregados );
        ((PostsActivity) context).updateListaRecycler();
        showProgressBar( !(posts.size() > 0) );
    }

    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        ((PostsActivity) context).showProgressBar( visibilidade );
    }

    public void updateListaRecycler(Post post) {
        posts.add( 0, post );
        ((PostsActivity) context).updateListaRecycler( 0 );
    }
}
