package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.MoviesErrorParser;
import com.example.machine2.movietime.lists.MovieImageAdapter;
import com.example.machine2.movietime.MoviePosterParser;
import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.network.MoviePosterListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
public class TopRatedMoviesManager extends BaseManager implements NetworkListener {

    MoviePosterListener moviePosterListener;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Context context;
    MoviePosterParser moviePosterParser;
    String statusMessage;
    MoviesErrorParser moviesErrorParser;
    Request request = new Request();

    public void getPosters(Context context, MoviePosterListener moviePosterListener) {

        this.context = context;
        this.moviePosterListener = moviePosterListener;

        request.setUrl(UrlProvider.TOP_RATED_URL);
        request.setHeaders(getHeaders());

        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        moviePosterParser = new MoviePosterParser();
        movieImageAdapter = moviePosterParser.parse(context, responseBody);
        moviePosterListener.refreshPoster(movieImageAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
        moviePosterListener.setErrorMessage(statusMessage);
    }
}


