package com.example.machine2.movietime;

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
    public static final String Favoriteid="_id";
    public static final String MovieUrl ="movie_url";
    public static final String MovieId ="movie_id";
    public static final String Database="moviedb";
    public static final String Table="movietbl";
    public static final Integer Version=4;
    public static final String  Query = "CREATE TABLE " + Table  + "("
            + Favoriteid  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + MovieUrl + "  TEXT UNIQUE," + MovieId + " TEXT UNIQUE)";

    private final Context context;
   // MovieDatabaseManager context;
    private SQLiteDatabase db;
    private Dbhelp dbhelp;
    ArrayList<String> urlArrayList = new ArrayList<String>();
    ArrayList<String> idArrayList = new ArrayList<String>();

    //constructor is created
    public MovieDatabase(Context context) {
        this.context = context;
        dbhelp=new Dbhelp(context);
    }

    public static class Dbhelp extends SQLiteOpenHelper {


        public Dbhelp(Context context) {
            super(context, Database, null, Version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Table);
            onCreate(db);

        }


    }
    public MovieDatabase open()
    {
        db = dbhelp.getWritableDatabase();
        return this;
    }
    public void close()
    {
        dbhelp.close();
    }
    //inserting data into database
    public long insert(String poster, String id)
    {

        ContentValues values = new ContentValues();
        values.put(MovieUrl, poster);
        values.put(MovieId, id);
        System.out.println("image url " + poster);
        System.out.println("id " + id);
        return db.insert(Table,null,values);

    }
    //fetching all the data from the database
    public ArrayList getPoster()
    {

        String[] column = new String[]{Favoriteid, MovieUrl,MovieId};
        Cursor movidata=db.query(Table,column,null,null,null,null,null);
        //   String result="";
        int Movie_url=movidata.getColumnIndex(MovieUrl);

        for(movidata.moveToFirst();!movidata.isAfterLast();movidata.moveToNext()) {
            //  result = result +" " + movidata.getString(Movie_id)  + "\n";
            urlArrayList.add(movidata.getString(Movie_url));

        }
        return urlArrayList;
    }
    public ArrayList<String> getId()
    {

        String[] column = new String[]{Favoriteid, MovieUrl,MovieId};
        Cursor movidata=db.query(Table,column,null,null,null,null,null);
        //     String result="";
        int Movie_id=movidata.getColumnIndex(MovieId);

        for(movidata.moveToFirst();!movidata.isAfterLast();movidata.moveToNext()) {
            //   result = result +" " + movidata.getString(Movie_id)  + "\n";
            idArrayList.add(movidata.getString(Movie_id));

        }
        return idArrayList;
    }
    //deleting the corrosponding data from the database
    public void delete(long id) {
        System.out.println("id"+id);
     //   db.execSQL("delete from "+Table+ " where "+MovieUrl+ " = "+url);
       db.delete(Table,MovieId+" = " + id,null);
    }


}