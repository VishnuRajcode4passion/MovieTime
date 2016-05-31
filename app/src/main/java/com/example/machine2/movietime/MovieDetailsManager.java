package com.example.machine2.movietime;

import com.example.machine2.movietime.controllers.BaseManager;
import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsManager extends BaseManager implements NetworkListener{

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;
    MovieDetailResponse detailResponse;
    String responseString;
    DetailsAdapter detailsAdapter;
    UpdatedMovieDetails updatedMovieDetails = new UpdatedMovieDetails();

    public void getMovieDetails(DetailsAdapter detailsAdapter, String id) {

        this.detailsAdapter = detailsAdapter;
        this.id = id;
        request.setUrl(UrlProvider.MOVIE_DETAILS_URL+id);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, MovieDetailResponse.class);
        updatedMovieDetails.settitle(detailResponse.getOriginal_title());
        updatedMovieDetails.setImage(detailResponse.getPoster_path());
        updatedMovieDetails.setDuration(detailResponse.getRuntime());
        updatedMovieDetails.setRatings(detailResponse.getVote_average());
        updatedMovieDetails.setReleseDate(detailResponse.getRelease_date());
        updatedMovieDetails.setDescription(detailResponse.getOverview());
        detailsAdapter.setMovieDetails(updatedMovieDetails);



    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
