package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MovieTrailerResponse;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.google.gson.Gson;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieTrailerManager extends BaseManager implements NetworkListener {

    NetworkCommunicator networkCommunicator;
    Requests request = new Requests();
    Gson gson;
    Context context;
    MovieTrailerResponse movieTrailerResponse;
    MovieDetailsListener movieDetailsListener;


    public void getTrailerManager(Context context, MovieDetailsListener movieDetailsListener, String id) {

        String trailerid;
        this.context = context;
        this.movieDetailsListener = movieDetailsListener;
        trailerid = id;
        String trailerId = trailerid+"/videos?";
        request.setUrl(UrlProvider.MOVIE_TRAILER_URL+trailerId);
        System.out.println(" Trailer url " + UrlProvider.MOVIE_TRAILER_URL + trailerId);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        String responseString;
        responseString = new String(responseBody);
        MovieTrailerAdapter movieTrailerAdapter;

        gson = new Gson();
        movieTrailerResponse = gson.fromJson(responseString, MovieTrailerResponse.class);
        movieTrailerAdapter = new MovieTrailerAdapter(context, movieTrailerResponse.getResults());
        movieDetailsListener.movieTrailer(movieTrailerAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
        movieDetailsListener.setErrorMessage(statusMessage);
    }
}
