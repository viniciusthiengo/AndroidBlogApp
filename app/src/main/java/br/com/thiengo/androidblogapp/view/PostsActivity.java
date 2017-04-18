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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.thiengo.androidblogapp.R;
import br.com.thiengo.androidblogapp.presenter.Post;
import br.com.thiengo.androidblogapp.presenter.PresenterPosts;

public class PostsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PresenterPosts presenter;
    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarFont( toolbar );

        presenter = new PresenterPosts( this );
        initDrawer( toolbar );
        initViews();

        presenter.retrievePosts( savedInstanceState );
    }

    private void toolbarFont( Toolbar toolbar ){
        TextView tv = (TextView) toolbar.getChildAt(0);
        Typeface font = Typeface.createFromAsset( getAssets(), "Timmana.ttf" );
        tv.setTypeface( font );
    }

    private void initDrawer( Toolbar toolbar ){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(this);
        setDataDrawerHeader( navigation );
    }

    private void setDataDrawerHeader( NavigationView navigation ){
        LinearLayout ll = (LinearLayout) navigation.getHeaderView(0);
        ImageView ivProfile = (ImageView) ll.getChildAt(0);
        TextView tvNome = (TextView) ll.getChildAt(1);
        TextView tvProfissao = (TextView) ll.getChildAt(2);

        Picasso.with( this )
                .load( presenter.getUser().getUriImagem() )
                .into( ivProfile );

        tvNome.setText( presenter.getUser().getNome() );
        tvProfissao.setText( presenter.getUser().getProfissao() );
    }

    private void initViews() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration( divider );

        adapter = new PostsAdapter( this, presenter.getPosts() );
        recyclerView.setAdapter( adapter );
    }

    public void updateListaRecycler(){
        adapter.notifyDataSetChanged();
    }

    public void showProgressBar( int visibilidade ){
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList( Post.POSTS_KEY, presenter.getPosts() );
        super.onSaveInstanceState(outState);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_conf_notif) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
