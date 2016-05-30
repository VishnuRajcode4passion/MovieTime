package com.example.machine2.movietime;

import android.content.Context;

import com.example.machine2.movietime.controllers.BaseManager;
import com.example.machine2.movietime.models.MovieTrailerResponse;
import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieTrailerManager extends BaseManager implements NetworkListener {

    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    String trailerId;
    Gson gson;
    Context context;
    MovieTrailerResponse movieTrailerResponse;
    String responseString;
    DetailsAdapter detailsAdapter;
    TrailerAdapter trailerAdapter;


    public void movieManager(Context context, DetailsAdapter detailsAdapter, String id) {

        this.context = context;
        this.detailsAdapter = detailsAdapter;
        this.id = id;
        trailerId = id+"/videos?";
        request.setUrl(UrlProvider.MOVIE_TRAILER_URL+trailerId);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        movieTrailerResponse = gson.fromJson(responseString, MovieTrailerResponse.class);
        trailerAdapter = new TrailerAdapter(context, movieTrailerResponse.getResults());
        detailsAdapter.movieTrailer(trailerAdapter);



    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
