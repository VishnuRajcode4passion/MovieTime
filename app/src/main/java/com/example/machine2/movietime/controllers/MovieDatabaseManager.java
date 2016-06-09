package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.database.MovieDatabase;

import java.util.ArrayList;

/**
 * Created by machine2 on 31/05/16.
 */
public class MovieDatabaseManager {

    String posters;
    String  title;
    String id;
    Context context;
    String favouriteState;
    MovieDatabase db;
    ArrayList<String> image;

    ArrayList<String> ids;
    public MovieDatabaseManager(Context context)
     {
     this.context = context;
      db = new MovieDatabase(context);
    }

    //to insert favourite movie into data base.
    public void setFavorite(String posters, String id, String check_state) {

        this.favouriteState = check_state;
        this.posters = posters;
        this.id = id;
        db.open();
        db.insert(posters, id, favouriteState);
        db.close();
    }

    //to delete favourite movie from database
    public void removeFavorites(String id, MovieDatabase db) {

        db.open();
        long l = Long.parseLong(id);
        db.delete(l);
        db.close();
    }

     //to get the state,ie. whether a particular movie is marked as checked or not.
    public String getState(String id) {

        db.open();
        String state = db.getFavouriteState(id);
        db.close();
        System.out.println("STATE2 " + state);
        return state;
    }
    public void getFavourite(Context context, MoviePosterListener moviePosterListener)
    {

       FavouriteManager favouriteManager;

        db.open();

        image = db.getPoster();
        ids = db.getId();

        favouriteManager = new FavouriteManager(moviePosterListener);
        favouriteManager.getPosters(context, image, ids);

        db.close();


    }
}

