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
 * Created by machine3 on 5/20/16.
 */
public class PopularMoviesManager extends BaseManager implements NetworkListener {

    MoviePosterListener moviePosterListener;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Context context;

    //to pass the information regarding popular movies to network communicator and sets the results in gridview.
    public void getPosters(Context context, MoviePosterListener moviePosterListener) {
        Requests request;

        this.context = context;
        this.moviePosterListener = moviePosterListener;

        request = new Requests();
        request.setUrl(UrlProvider.POPULAR_URL);
        request.setHeaders(getHeaders());

        networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    //when the request was successful,the response body is obtained from network communicator class and the posters are set in grid view.
    @Override
    public void onSuccess(byte[] responseBody) {

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
