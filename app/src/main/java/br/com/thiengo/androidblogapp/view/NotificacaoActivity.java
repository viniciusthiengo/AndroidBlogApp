package br.com.thiengo.androidblogapp.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import br.com.thiengo.androidblogapp.R;
import br.com.thiengo.androidblogapp.model.SPUtil;
import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterLogin;
import br.com.thiengo.androidblogapp.presenter.PresenterNotificacao;
import br.com.thiengo.androidblogapp.presenter.PresenterPosts;

public class NotificacaoActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    private PresenterNotificacao presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarFontFamily( toolbar );
        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        presenter = new PresenterNotificacao(this);
        presenter.setCheckBoxConf( R.id.categoria_1 );
        presenter.setCheckBoxConf( R.id.categoria_2 );
        presenter.setCheckBoxConf( R.id.categoria_3 );
        presenter.setCheckBoxConf( R.id.categoria_4 );
        presenter.setCheckBoxConf( R.id.categoria_5 );
    }

    private void toolbarFontFamily(Toolbar toolbar ){
        TextView tv = (TextView) toolbar.getChildAt(0);
        Typeface font = Typeface.createFromAsset( getAssets(), "Timmana.ttf" );
        tv.setTypeface( font );
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
    public void onCheckedChanged(CompoundButton cb, boolean b) {
        presenter.onCheckedChanged(cb, b);
    }
}
