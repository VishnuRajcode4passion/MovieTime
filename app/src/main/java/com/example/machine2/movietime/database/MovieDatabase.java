package com.example.machine2.movietime.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieDatabase {

    public static final String FavoriteId = "_id";
    public static final String MovieUrl = "movie_url";
    public static final String MovieId = "movie_id";
    public static final String Database = "moviedb";
    public static final String Table = "movietbl";
    public static final Integer Version = 4;
    public static final String Query = "CREATE TABLE " + Table + "("
            + FavoriteId + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + MovieUrl + "  TEXT UNIQUE," + MovieId + " TEXT UNIQUE)";

    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;
    ArrayList<String> urlArrayList = new ArrayList<>();
    ArrayList<String> idArrayList = new ArrayList<>();
    ContentValues values;
    Cursor movieData;
    String[] column;
    int movieId;

    //constructor is created
    public MovieDatabase(Context context) {

        dbHelper = new DbHelper(context);
    }

    public static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, Database, null, Version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(Query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + Table);
            onCreate(db);
        }
    }

    public MovieDatabase open() {

        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //inserting data into database
    public long insert(String poster, String id) {

        values = new ContentValues();
        values.put(MovieUrl, poster);
        values.put(MovieId, id);
        System.out.println("image url " + poster);
        System.out.println("id " + id);
        return sqLiteDatabase.insert(Table, null, values);
    }

    //fetching all the data from the database
    public ArrayList getPoster() {

        column = new String[]{FavoriteId, MovieUrl, MovieId};
        movieData = sqLiteDatabase.query(Table, column, null, null, null, null, null);
        //   String result="";
        int Movie_url = movieData.getColumnIndex(MovieUrl);

        for (movieData.moveToFirst(); !movieData.isAfterLast(); movieData.moveToNext()) {

            urlArrayList.add(movieData.getString(Movie_url));
        }
        return urlArrayList;
    }

    public ArrayList<String> getId() {

        column = new String[]{

                FavoriteId, MovieUrl, MovieId
        };

        movieData = sqLiteDatabase.query(Table, column, null, null, null, null, null);
        movieId = movieData.getColumnIndex(MovieId);

        for (movieData.moveToFirst(); !movieData.isAfterLast(); movieData.moveToNext()) {

            idArrayList.add(movieData.getString(movieId));
        }
        return idArrayList;
    }

    //deleting the corrosponding data from the database
    public void delete(long id) {

        sqLiteDatabase.delete(Table, MovieId + " = " + id, null);
    }
}