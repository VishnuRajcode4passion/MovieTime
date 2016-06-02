package com.example.machine2.movietime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.machine2.movietime.FavoriteAdapter;
import com.example.machine2.movietime.controllers.FavoriteManager;
import com.example.machine2.movietime.MovieDatabase;
import com.example.machine2.movietime.MovieImageAdapter;
import com.example.machine2.movietime.PopularMovieManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.TopRatedMovieManager;
import com.example.machine2.movietime.network.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MovieAdapter, NavigationView.OnNavigationItemSelectedListener {

    //variable declaration
    GridView gridView;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    String movie_id;
    TextView MovieId;
    Intent intent;
    String title;
    TopRatedMovieManager topRatedMovieManager;
    PopularMovieManager popularMovieManager;
    FavoriteManager favoriteManager;
    MovieDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        gridView = (GridView) findViewById(R.id.gridview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Popular");

        //calling the progress dialog from the Base activty
        dialogShow();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        popularMovieManager = new PopularMovieManager();
        popularMovieManager.getPosters(this, this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieId = (TextView) view.findViewById(R.id.textView);
                movie_id = MovieId.getText().toString();
                intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("selectedId", movie_id);
                startActivity(intent);
            }
        });
    }

    //sets gridview..
    @Override
    public void setImageAdapter(MovieImageAdapter imageAdapter) {


        dialogDismiss();

        gridView.setAdapter(imageAdapter);
    }

    @Override
    public void setFavorite(FavoriteAdapter favoriteAdapter) {

        dialogDismiss();

        gridView.setAdapter(favoriteAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.topRated) {

            dialogShow();

            topRatedMovieManager = new TopRatedMovieManager();
            topRatedMovieManager.getPosters(this, this);
            title = "Top Rated";

        } else if (id == R.id.popular) {

            dialogShow();

            popularMovieManager.getPosters(this, this);
            title = "Popular";

        } else if (id == R.id.favorite) {

            dialogShow();

            db = new MovieDatabase(this);
            db.open();
            ArrayList<String> image = db.getPoster();
            ArrayList<String> ids = db.getId();
            favoriteManager = new FavoriteManager();
            favoriteManager.getPosters(this, this, image, ids);
            db.close();
            title = "Favourite";

        } else if (id == R.id.search) {

            WeatherActivity weatherActivity = new WeatherActivity();
        }
        getSupportActionBar().setTitle(title);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}