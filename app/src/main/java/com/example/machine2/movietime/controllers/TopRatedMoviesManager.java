package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.MoviesErrorParser;
import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.MoviePosterParser;
import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.interfaces.MoviePosterListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.interfaces.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
public class TopRatedMoviesManager extends BaseManager implements NetworkListener {

    MoviePosterListener moviePosterListener;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Context context;




    public void getPosters(Context context, MoviePosterListener moviePosterListener) {

        this.context = context;
        this.moviePosterListener = moviePosterListener;

        Request request = new Request();
        request.setUrl(UrlProvider.TOP_RATED_URL);
        request.setHeaders(getHeaders());

        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        MoviePosterParser moviePosterParser;
        moviePosterParser = new MoviePosterParser();
        movieImageAdapter = moviePosterParser.parse(context, responseBody);
        moviePosterListener.refreshPoster(movieImageAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
        moviePosterListener.setErrorMessage(statusMessage);
    }
}


