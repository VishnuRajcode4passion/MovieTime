package com.example.machine2.movietime.parser;

import android.content.Context;

import com.example.machine2.movietime.BaseParser;
import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.models.MovieDetailResponse;
import com.example.machine2.movietime.models.MovieTrailerResponse;
import com.google.gson.Gson;

/**
 * Created by machine3 on 6/9/16.
 */
public class MovieTrailerParser extends BaseParser {

    //method for getting MovieTrailerResponse
    public MovieTrailerAdapter parse(Context context, byte[] responseBody) {

        MovieTrailerResponse  movieTrailerResponse;
        MovieTrailerAdapter movieTrailerAdapter;

        gson = new Gson();
        responseString = new String(responseBody);
        movieTrailerResponse = gson.fromJson(responseString, MovieTrailerResponse.class);
        movieTrailerAdapter = new MovieTrailerAdapter(context, movieTrailerResponse.getResults());
        return  movieTrailerAdapter;
    }
}
