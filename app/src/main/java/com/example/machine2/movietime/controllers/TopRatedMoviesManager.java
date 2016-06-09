package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.parser.MoviePosterParser;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
//Manager classm defined for the Toprated Movie

public class TopRatedMoviesManager extends BaseManager implements NetworkListener {

    MoviePosterListener moviePosterListener;

    //Method declaration for the top rated movies
    public void getPosters(Context context, MoviePosterListener moviePosterListener) {
        Integer requestId=1;

        this.moviePosterListener = moviePosterListener;

        Requests request = new Requests();
        request.setId(requestId);
        request.setUrl(UrlProvider.TOP_RATED_URL);
        request.setHeaders(getHeaders());

        NetworkCommunicator networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    //implementing the NetworkListener for set the topratedMovie posters
    @Override
    public void onSuccess(Context context, byte[] responseBody) {

        MovieImageAdapter movieImageAdapter;
        MoviePosterParser moviePosterParser;
        moviePosterParser = new MoviePosterParser();
        movieImageAdapter = moviePosterParser.parse(context, responseBody);
        moviePosterListener.refreshPoster(movieImageAdapter);
    }

    //to display the error message ,if there is problem in fetching the contents from server.
    @Override
    public void onFailure(byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
        moviePosterListener.setErrorMessage(statusMessage);
    }
}


