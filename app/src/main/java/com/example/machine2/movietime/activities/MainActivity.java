package com.example.machine2.movietime.activities;

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
import android.widget.Toast;

import com.example.machine2.movietime.lists.FavouriteAdapter;
import com.example.machine2.movietime.controllers.FavouriteManager;
import com.example.machine2.movietime.MovieDatabase;
import com.example.machine2.movietime.lists.MovieImageAdapter;
import com.example.machine2.movietime.controllers.PopularMoviesManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.controllers.TopRatedMoviesManager;
import com.example.machine2.movietime.network.MoviePosterListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MoviePosterListener, NavigationView.OnNavigationItemSelectedListener {

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
    TopRatedMoviesManager topRatedMoviesManager;
    PopularMoviesManager popularMoviesManager;
    FavouriteManager favouriteManager;
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
        showDialog();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        popularMoviesManager = new PopularMoviesManager();
        popularMoviesManager.getPosters(this, this);

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
    public void refreshPoster(MovieImageAdapter imageAdapter) {

        dismissDialog();

        gridView.setAdapter(imageAdapter);
    }

    @Override
    public void setFavorite(FavouriteAdapter favouriteAdapter) {

        dismissDialog();

        gridView.setAdapter(favouriteAdapter);
    }

    @Override
    public void setErrorMessage(String statusMessage) {

        dismissDialog();

        Toast.makeText(this, statusMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.topRated) {

            showDialog();

            topRatedMoviesManager = new TopRatedMoviesManager();
            topRatedMoviesManager.getPosters(this, this);
            title = "Top Rated";

        } else if (id == R.id.popular) {

            showDialog();

            popularMoviesManager.getPosters(this, this);
            title = "Popular";

        } else if (id == R.id.favorite) {

            showDialog();

            db = new MovieDatabase(this);
            db.open();
            ArrayList<String> image = db.getPoster();
            ArrayList<String> ids = db.getId();
            favouriteManager = new FavouriteManager();
            favouriteManager.getPosters(this, this, image, ids);
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