package com.example.machine2.movietime.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.machine2.movietime.MenuSelector;
import com.example.machine2.movietime.MovieImageAdapter;
import com.example.machine2.movietime.PopularMovieManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;

public class MainActivity extends BaseActivity implements MovieAdapter,NavigationView.OnNavigationItemSelectedListener {

    //variable declaration
    GridView gridView;
    Toolbar toolbar;
    PopularMovieManager popularMovieManager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    NetworkCommunicator networkCommunicator;

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
        popularMovieManager.movieManager(this,this);

    }
//sets gridview.......

    @Override
    public void setImageAdapter(MovieImageAdapter imageAdapter) {

        dialogDismiss();

        gridView.setAdapter(imageAdapter);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        MenuSelector menuSelector = new MenuSelector(this,networkCommunicator,drawer);
        String title = menuSelector.getItem(item);
        getSupportActionBar().setTitle(title);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}