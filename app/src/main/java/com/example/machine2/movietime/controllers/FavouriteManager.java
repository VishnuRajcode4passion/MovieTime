package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.lists.FavouriteAdapter;
import com.example.machine2.movietime.MovieDatabase;
import com.example.machine2.movietime.models.MovieDetailResponse;
import com.example.machine2.movietime.lists.MovieImageAdapter;
import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.models.UpdatedMovieDetails;
import com.example.machine2.movietime.network.MovieDetailsListener;
import com.example.machine2.movietime.network.MoviePosterListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavouriteManager extends BaseManager implements NetworkListener {

    MoviePosterListener moviePosterListener;
    Gson gson;

    MovieDetailResponse detailResponse;
    String responseString;
    Context context;
    ArrayList<String> images;
    ArrayList<String> mid;
    FavouriteAdapter favouriteAdapter;


    public void getPosters(Context context, MoviePosterListener moviePosterListener, ArrayList<String> image, ArrayList<String> ids) {
        favouriteAdapter = new FavouriteAdapter(context,image,ids);
        moviePosterListener.setFavorite(favouriteAdapter);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, MovieDetailResponse.class);
        favouriteAdapter = new FavouriteAdapter(context, images, mid);
        moviePosterListener.setFavorite(favouriteAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
