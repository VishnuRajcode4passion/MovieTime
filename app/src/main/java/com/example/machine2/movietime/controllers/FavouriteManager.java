package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.adapters.FavouriteAdapter;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavouriteManager extends BaseManager  {

    FavouriteAdapter favouriteAdapter;
    MoviePosterListener moviePosterListener;

    public FavouriteManager(MoviePosterListener moviePosterListener ) {

        this.moviePosterListener = moviePosterListener;
    }

    // to get the posters of favourite movies.
    public void getPosters(Context context, ArrayList<String> image, ArrayList<String> ids) {

         favouriteAdapter = new FavouriteAdapter(context,image,ids);
         moviePosterListener.setFavourite(favouriteAdapter);
    }
}
