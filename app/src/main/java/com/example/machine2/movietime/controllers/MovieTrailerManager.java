package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MovieTrailerResponse;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.example.machine2.movietime.parser.MoviePosterParser;
import com.example.machine2.movietime.parser.MovieTrailerParser;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.google.gson.Gson;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieTrailerManager extends BaseManager implements NetworkListener {

    //variable declarations
    MovieDetailsListener movieDetailsListener;

    //method for getting the trailer URL and header
    public void getTrailerManager(Context context, MovieDetailsListener movieDetailsListener, String id) {

        String trailerid;
        Requests request = new Requests();
        this.movieDetailsListener = movieDetailsListener;
        trailerid = id;
        String trailerId = trailerid + "/videos?";
        request.setUrl(UrlProvider.MOVIE_TRAILER_URL + trailerId);
        System.out.println(" Trailer url " + UrlProvider.MOVIE_TRAILER_URL + trailerId);
        request.setHeaders(getHeaders());
        NetworkCommunicator networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    //implementing the NetworkListener to get MovieTrailerResponses
    @Override
    public void onSuccess(Context context, byte[] responseBody) {

        MovieTrailerParser movieTrailerParser = new MovieTrailerParser();
        MovieTrailerAdapter movieTrailerAdapter;
        movieTrailerAdapter = movieTrailerParser.parse(context, responseBody);
        movieDetailsListener.movieTrailer(movieTrailerAdapter);
    }

    //to display the error message ,if there is problem in fetching the contents from server.
    @Override
    public void onFailure(byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
        movieDetailsListener.setErrorMessage(statusMessage);
    }
}
