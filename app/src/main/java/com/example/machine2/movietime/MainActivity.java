package com.example.machine2.movietime;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;

public class MainActivity extends BaseActivity implements MovieAdapter {

    //variable declaration
    GridView gridView;
    NavigationView navigationView;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NetworkCommunicator networkCommunicator;
    Toolbar toolbar;
    MovieManager movieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        gridView = (GridView) findViewById(R.id.gridview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Popular");

        //calling the progress dialog from the Base activty
        dialogShow(this);

        movieManager = new MovieManager();
        movieManager.movieManager(this);

    }
//sets gridview
    @Override
    public void setImageAdapter(MovieImageAdapter imageAdapter) {

        dialogDismiss();

        gridView.setAdapter(imageAdapter);
    }
}