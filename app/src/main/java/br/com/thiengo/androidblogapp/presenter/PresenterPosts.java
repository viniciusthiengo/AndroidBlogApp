package br.com.thiengo.androidblogapp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.androidblogapp.model.ModelPosts;
import br.com.thiengo.androidblogapp.view.PostsActivity;


public class PresenterPosts {
    private User user;
    private ModelPosts model;
    private PostsActivity activity;
    private ArrayList<Post> posts = new ArrayList<>();


    public PresenterPosts( PostsActivity a ){
        activity = a;
        user = a.getIntent().getParcelableExtra(User.KEY);
        model = new ModelPosts( this );
    }

    public User getUser(){
        return user;
    }

    public Activity getContext() {
        return activity;
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
        activity.updateListaRecycler();
        showProgressBar( !(posts.size() > 0) );
    }

    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        activity.showProgressBar( visibilidade );
    }
}
