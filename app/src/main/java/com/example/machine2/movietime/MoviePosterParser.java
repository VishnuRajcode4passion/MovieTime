package com.example.machine2.movietime;

/**
 * Created by machine3 on 5/20/16.
 */
import android.content.Context;

import com.example.machine2.movietime.models.MoviesResponse;
import com.google.gson.Gson;

public class MoviePosterParser {

    Gson gson;
    MoviesResponse moviesResponse;
    MovieImageAdapter imageAdapter;
    String responseString;

    //parser method for parsing json to gson and getting the results from Gson class.
    MovieImageAdapter parser(Context context, byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesResponse = gson.fromJson(responseString, MoviesResponse.class);
        imageAdapter = new MovieImageAdapter(context, moviesResponse.getResults());
        return imageAdapter;
    }
}