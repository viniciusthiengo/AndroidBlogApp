package br.com.thiengo.androidblogapp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by viniciusthiengo on 26/02/17.
 */

public class ManterListaFragment extends Fragment {
    public static final String KEY = "manter_listas_fragment";
    private ArrayList<Post> posts = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance( true );
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
