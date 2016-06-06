package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.MovieDetailsParser;
import com.example.machine2.movietime.MoviesErrorParser;
import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.models.UpdatedMovieDetails;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MovieDetailResponse;
import com.example.machine2.movietime.interfaces.MovieDetailsListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.interfaces.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsManager extends BaseManager implements NetworkListener {

    String id;
    MovieDetailResponse detailResponse;
    MovieDetailsListener movieDetailsListener;
    NetworkCommunicator networkCommunicator;


    UpdatedMovieDetails updatedMovieDetails = new UpdatedMovieDetails();

    public void getMovieDetails(MovieDetailsListener movieDetailsListener, String id) {

        Request request;

        this.movieDetailsListener = movieDetailsListener;
        this.id = id;

        request = new Request();
        request.setUrl(UrlProvider.MOVIE_DETAILS_URL + id);
        request.setHeaders(getHeaders());

        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        MovieDetailsParser movieDetailsParser;
        movieDetailsParser = new MovieDetailsParser();
        detailResponse = movieDetailsParser.parse(responseBody);

        updatedMovieDetails.settitle(detailResponse.getOriginal_title());
        updatedMovieDetails.setImage(detailResponse.getPoster_path());
        updatedMovieDetails.setDuration(detailResponse.getRuntime());
        updatedMovieDetails.setRatings(detailResponse.getVote_average());
        updatedMovieDetails.setReleseDate(detailResponse.getRelease_date());
        updatedMovieDetails.setDescription(detailResponse.getOverview());

        movieDetailsListener.setMovieDetails(updatedMovieDetails);
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
