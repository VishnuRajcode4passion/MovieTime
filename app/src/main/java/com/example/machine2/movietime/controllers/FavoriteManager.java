package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.FavoriteAdapter;
import com.example.machine2.movietime.MovieDatabase;
import com.example.machine2.movietime.MovieDetailResponse;
import com.example.machine2.movietime.MovieImageAdapter;
import com.example.machine2.movietime.Request;
import com.example.machine2.movietime.UpdatedMovieDetails;
import com.example.machine2.movietime.controllers.BaseManager;
import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavoriteManager extends BaseManager implements NetworkListener {

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;

    MovieDetailResponse detailResponse;
    MovieDatabase db;
    String responseString;
    DetailsAdapter detailsAdapter;
    UpdatedMovieDetails updatedMovieDetails = new UpdatedMovieDetails();
    Context context;
    ArrayList<String> images;
    ArrayList<String> mid;
    FavoriteAdapter favoriteAdapter;


    public void getPosters(Context context, MovieAdapter movieAdapter, ArrayList<String> image, ArrayList<String> ids) {
        favoriteAdapter = new FavoriteAdapter(context,image,ids);
        System.out.println("FAVOURITE ADAPTER " + favoriteAdapter);
        movieAdapter.setFavorite(favoriteAdapter);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, MovieDetailResponse.class);
        favoriteAdapter = new FavoriteAdapter(context, images, mid);
        movieAdapter.setFavorite(favoriteAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
