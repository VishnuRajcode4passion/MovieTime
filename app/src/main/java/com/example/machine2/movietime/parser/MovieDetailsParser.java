package com.example.machine2.movietime.parser;

import com.example.machine2.movietime.models.MovieDetailResponse;
import com.google.gson.Gson;

/**
 * Created by machine3 on 6/2/16.
 */
//parser class for movie details

public class MovieDetailsParser {
//variable declarations

    Gson gson;
    MovieDetailResponse detailResponse;
    String responseString;

    //method for getting MovieDetailResponse

    public MovieDetailResponse parse(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, MovieDetailResponse.class);
        return  detailResponse;
    }
}
