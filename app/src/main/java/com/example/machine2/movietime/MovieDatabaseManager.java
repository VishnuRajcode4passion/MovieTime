package com.example.machine2.movietime;

/**
 * Created by machine2 on 31/05/16.
 */
public class MovieDatabaseManager {
    String posters;
    String id;

    public void getFavorite(String posters, String id, MovieDatabase db) {

        this.posters = posters;
        this.id = id;
        db.open();
        db.insert(posters, id);
        //Toast.makeText(get, "Added to Favoruite", Toast.LENGTH_LONG).show();
        db.close();
    }


}
