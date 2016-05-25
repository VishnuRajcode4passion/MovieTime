package com.example.machine2.movietime.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.example.machine2.movietime.MovieImageAdapter;
import com.example.machine2.movietime.PopularMovieManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;

public class MainActivity extends BaseActivity implements MovieAdapter {

    //variable declaration
    GridView gridView;
    Toolbar toolbar;
    PopularMovieManager popularMovieManager;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (GridView) findViewById(R.id.gridview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Popular");

        //calling the progress dialog from the Base activty
        dialogShow();

        popularMovieManager = new PopularMovieManager();
        popularMovieManager.movieManager(this,this);
    }

    //sets gridview.
    @Override
    public void setImageAdapter(MovieImageAdapter imageAdapter) {

        dialogDismiss();

        gridView.setAdapter(imageAdapter);
    }
}