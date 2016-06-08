package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.activities.MovieDetailsActivity;
import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.parser.MovieDetailsParser;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.example.machine2.movietime.models.UpdatedMovieDetails;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MovieDetailResponse;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsManager extends BaseManager implements NetworkListener {

    String id;
    MovieDetailResponse detailResponse;
    MovieDetailsListener movieDetailsListener;
    NetworkCommunicator networkCommunicator;

    //to get the all details of a particular movie by sending all the information to the network communicator class.
    public void getMovieDetails(Context context, MovieDetailsListener movieDetailsListener, String id) {

        Requests request;

        this.movieDetailsListener = movieDetailsListener;
        this.id = id;
        request = new Requests();
        request.setUrl(UrlProvider.MOVIE_DETAILS_URL + id+"?");
        request.setHeaders(getHeaders());

        networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        UpdatedMovieDetails updatedMovieDetails = new UpdatedMovieDetails();
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
