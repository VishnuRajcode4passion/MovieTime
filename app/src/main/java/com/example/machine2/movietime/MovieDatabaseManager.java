package com.example.machine2.movietime;

/**
 * Created by machine2 on 31/05/16.
 */
public class MovieDatabaseManager {

    String posters;
    String id;
    MovieDatabase db;

    public void setFavorite(String posters, String id, MovieDatabase db) {

        this.posters = posters;
        this.id = id;
        this.db = db;
        db.open();
        db.insert(posters, id);
        //Toast.makeText(get, "Added to Favoruite", Toast.LENGTH_LONG).show();
        db.close();
    }
    public void removeFavorites()
    {
        db.open();
        long l = Long.parseLong(id);
        db.delete(l);
        db.close();
    }
}
