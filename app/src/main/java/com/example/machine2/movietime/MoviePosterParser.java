package com.example.machine2.movietime;

/**
 * Created by machine3 on 5/20/16.
 */
import android.content.Context;

import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.models.MoviesPosterResponse;
import com.google.gson.Gson;

public class MoviePosterParser {

    Gson gson;
    MoviesPosterResponse moviesPosterResponse;
    MovieImageAdapter imageAdapter;
    String responseString;

    //parse method for parsing json to gson and getting the results from Gson class.
    public MovieImageAdapter parse(Context context, byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesPosterResponse = gson.fromJson(responseString, MoviesPosterResponse.class);
        imageAdapter = new MovieImageAdapter(context, moviesPosterResponse.getResults());
        return imageAdapter;
    }
}