package com.example.machine2.movietime;

import android.util.Log;

import com.example.machine2.movietime.models.MoviesErrorResponse;
import com.google.gson.Gson;

/**
 * Created by machine3 on 6/2/16.
 */
public class MoviesErrorParser {

    Gson gson;
    MoviesErrorResponse moviesErrorResponse;
    int statusCode;

    String responseString;
    String statusMessage;

    public String parse(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesErrorResponse = gson.fromJson(responseString, MoviesErrorResponse.class);
        statusCode = moviesErrorResponse.getStatus_code();
        statusMessage = moviesErrorResponse.getStatus_message();
        Log.d("MoviesErrorParser", "CODE " + statusCode);
        Log.d("MoviesErrorParser", "STATUS MESSAGE " + statusMessage);
        return statusMessage;
    }
}
