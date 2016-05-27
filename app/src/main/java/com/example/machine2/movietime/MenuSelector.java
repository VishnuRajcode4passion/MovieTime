package com.example.machine2.movietime;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.machine2.movietime.activity.MainActivity;
import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;

import java.util.ArrayList;

/**
 * Created by machine2 on 23/05/16.
 */
public class MenuSelector extends MainActivity {

    Context context;
    NetworkCommunicator networkCommunication;
    DrawerLayout drawer;
    String title;
    TopRatedMovieManager topRatedMovieManager = new TopRatedMovieManager();
    PopularMovieManager popularMovieManager;
    FavoriteManager favoriteManager;
    MainActivity mainActivity;
    MovieDatabase db;
    DetailsAdapter detailsAdapter;
    String Mid;
    MovieAdapter movieAdapter;

    public MenuSelector(MovieAdapter movieAdapter, String id, DrawerLayout drawer) {
        this.context = context;
       this.Mid =  Mid;
        this.drawer = drawer;
    }


    public String getItem(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.topRated) {
            topRatedMovieManager.movieManager(context,mainActivity);
            title = "Top Rated";

        } else if (id == R.id.popular) {
            popularMovieManager.movieManager(context,this);
            title = "Popular";
        } else if (id == R.id.favorite) {
          db = new MovieDatabase(context);
            db.open();
            ArrayList<String> image=db.getPoster();
            ArrayList<String> Movieid= db.getId();
            favoriteManager = new FavoriteManager(context,image,Movieid);
            favoriteManager.movieManager(movieAdapter,Mid);

        }
        else if (id == R.id.logout) {
            drawer.closeDrawer(GravityCompat.START);
           // Intent intent = new Intent(context,LoginActivity.class);
           // context.startActivity(intent);
        }

        return title;
    }
}

