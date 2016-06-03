package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MovieTrailerResponse;
import com.example.machine2.movietime.interfaces.MovieDetailsListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.interfaces.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieTrailerManager extends BaseManager implements NetworkListener {

    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;
    Context context;
    MovieTrailerResponse movieTrailerResponse;
    String responseString;
    MovieDetailsListener movieDetailsListener;
    MovieTrailerAdapter movieTrailerAdapter;

    public void getTrailerManager(Context context, MovieDetailsListener movieDetailsListener, String id) {

        this.context = context;
        this.movieDetailsListener = movieDetailsListener;
        this.id = id;
        String trailerId = id+"/videos?";
        request.setUrl(UrlProvider.MOVIE_TRAILER_URL+trailerId);
        System.out.println(" Trailer url "+ UrlProvider.MOVIE_TRAILER_URL+trailerId);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        movieTrailerResponse = gson.fromJson(responseString, MovieTrailerResponse.class);
        movieTrailerAdapter = new MovieTrailerAdapter(context, movieTrailerResponse.getResults());
        movieDetailsListener.movieTrailer(movieTrailerAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
