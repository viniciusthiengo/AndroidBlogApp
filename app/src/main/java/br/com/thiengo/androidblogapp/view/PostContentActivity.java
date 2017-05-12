package br.com.thiengo.androidblogapp.view;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import br.com.thiengo.androidblogapp.R;
import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterNotificacao;

public class PostContentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Post post;
    public static boolean isOpened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarFontFamily( toolbar );
        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancelAll();

        post = getIntent().getParcelableExtra(Post.POST_KEY);
        initContent();
    }

    private void toolbarFontFamily(Toolbar toolbar ){
        TextView tv = (TextView) toolbar.getChildAt(0);
        Typeface font = Typeface.createFromAsset( getAssets(), "Timmana.ttf" );
        tv.setTypeface( font );
    }

    private void initContent(){
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText( post.getSumario() );

        ImageView ivBanner = (ImageView) findViewById(R.id.iv_banner);
        Picasso.with( this )
                .load( post.getUriImagem() )
                .into( ivBanner );
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle( post.getTitulo() );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isOpened = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isOpened = false;
    }
}
