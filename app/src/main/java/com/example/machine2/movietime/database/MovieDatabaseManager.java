package com.example.machine2.movietime.database;

/**
 * Created by machine2 on 31/05/16.
 */
public class MovieDatabaseManager {


    String id;
    MovieDatabase db;

    public void setFavorite(String posters, String id, MovieDatabase db) {

        String poster;

        poster = posters;
        this.id = id;
        this.db = db;
        db.open();
        db.insert(poster, id);
        db.close();
    }
    public void removeFavorites(String id)
    {
        this.id = id;
        db.open();
        long l = Long.parseLong(id);
        db.delete(l);
        db.close();
    }
}
