package com.example.machine2.movietime;

import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsManager extends BaseManager implements NetworkListener {

    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;
    MovieDetailResponse movieDetailResponse;
    String responseString;
    DetailsAdapter detailsAdapter;
    SetMovieDetails setMovieDetails = new SetMovieDetails();

    public void movieManager(DetailsAdapter detailsAdapter, String id) {

        this.detailsAdapter = detailsAdapter;
        this.id = id;
        request.setUrl(UrlProvider.MOVIE_DETAILS_URL + id);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        movieDetailResponse = gson.fromJson(responseString, MovieDetailResponse.class);
        setMovieDetails.settitle(movieDetailResponse.getOriginal_title());
        detailsAdapter.setMovieDetails(setMovieDetails);
    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
