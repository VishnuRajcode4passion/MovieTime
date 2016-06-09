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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.adapters.FavouriteAdapter;
import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.controllers.MovieDatabaseManager;
import com.example.machine2.movietime.controllers.MoviePosterListener;
import com.example.machine2.movietime.controllers.PopularMoviesManager;
import com.example.machine2.movietime.controllers.TopRatedMoviesManager;
import com.example.machine2.movietime.database.MovieDatabase;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity implements MoviePosterListener, NavigationView.OnNavigationItemSelectedListener {

    //variable declaration
    GridView gridView;

    Toolbar toolbar;

    DrawerLayout drawer;

    ActionBarDrawerToggle toggle;

    TextView MovieId;
    TextView FavAlert;

    ImageView CurrentProfPic;

    Intent intent;

    TopRatedMoviesManager topRatedMoviesManager;

    PopularMoviesManager popularMoviesManager;

    MovieDatabase movieDatabase;

    String movieId;
    String title;


    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        CurrentProfPic = (ImageView)findViewById(R.id.profile_image);


        navigationView = (NavigationView) findViewById(R.id.nav_view);


        toolbar = (Toolbar) findViewById(R.id.toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        gridView = (GridView) findViewById(R.id.gridview);


        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.popular));
try {
    Bundle bundle = getIntent().getExtras();
    String image_url = bundle.getString("url");
    System.out.println("image" + image_url);

    Picasso.with(getApplicationContext()).load(image_url).into(CurrentProfPic);


}
catch(Exception e)
        {

        }



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

                movieId = MovieId.getText().toString();
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("selectedId", movieId);

                gridView.startAnimation(animation);

                startActivity(intent);
            }
        });
    }

    //set  posters in  gridview..
    @Override
    public void refreshPoster(MovieImageAdapter imageAdapter) {

        dismissDialog();
        gridView.setAdapter(imageAdapter);
    }

    //set favourite movie posters in grid view.
    @Override
    public void setFavourite(FavouriteAdapter favouriteAdapter) {

        dismissDialog();

        gridView.setAdapter(favouriteAdapter);
    }

    // //to display the error message ,if there is problem in fetching the contents from server.
    @Override
    public void setErrorMessage(String statusMessage) {

        dismissDialog();

        Toast.makeText(this, statusMessage, Toast.LENGTH_LONG).show();
    }




    int backButtonCount = 0;

    @Override
    public void onBackPressed(){




        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem item) {





        int id = item.getItemId();

        if (id == R.id.topRated) {

            showDialog();

            topRatedMoviesManager = new TopRatedMoviesManager();
            topRatedMoviesManager.getPosters(this, this);
            title = getString(R.string.topRated);

        } else if (id == R.id.popular) {

            showDialog();

            popularMoviesManager.getPosters(this, this);
            title = getString(R.string.popular);

        }
        else if (id == R.id.favorite) {

            showDialog();
            title = getString(R.string.favourite);
            
            MovieDatabaseManager movieDatabaseManager = new MovieDatabaseManager(this);
            movieDatabaseManager.getFavourite(this,this);



           // movieDatabase.close();

            title = "Favourite";



//            movieDatabaseManager = new MovieDatabaseManager();
//            movieDatabaseManager.getFavourite();

        }
        else if (id == R.id.search) {

            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {

            FacebookSdk.sdkInitialize(getApplicationContext());
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setTitle(title);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}