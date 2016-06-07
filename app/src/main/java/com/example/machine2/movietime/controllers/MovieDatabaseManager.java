package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.database.MovieDatabase;

/**
 * Created by machine2 on 31/05/16.
 */
public class MovieDatabaseManager {

    String posters;
    String id;
    MovieDatabase db;
    String favouriteState;

    //to insert favourite movie into data base.
    public void setFavorite(String posters, String id, String b, MovieDatabase db) {

        this.favouriteState = b;
        this.posters = posters;
        this.id = id;
        this.db = db;
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
    public String getState(String id, MovieDatabase db) {

        db.open();
        String state = db.getFavouriteState(id);
        db.close();
        System.out.println("STATE2 " + state);
        return state;
    }
}