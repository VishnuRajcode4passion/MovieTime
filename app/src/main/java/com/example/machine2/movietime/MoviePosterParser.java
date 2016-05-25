package com.example.machine2.movietime;

/**
 * Created by machine3 on 5/20/16.
 */
import android.content.Context;
import com.google.gson.Gson;

public class MoviePosterParser {

    Gson gson;
    MoviesResponse moviesResponse;
    MovieImageAdapter imageAdapter;
    String responseString;
    Context context;


    //parser method for parsing json to gson
    MovieImageAdapter parser(Context context, byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesResponse = gson.fromJson(responseString, MoviesResponse.class);
        imageAdapter = new MovieImageAdapter(context, moviesResponse.getResults());
        return imageAdapter;
    }
}