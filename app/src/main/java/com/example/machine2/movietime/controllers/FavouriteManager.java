package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.adapters.FavouriteAdapter;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavouriteManager extends BaseManager  {

    FavouriteAdapter favouriteAdapter;


    public void getPosters(Context context, MoviePosterListener moviePosterListener, ArrayList<String> image, ArrayList<String> ids) {

         favouriteAdapter = new FavouriteAdapter(context,image,ids);
         moviePosterListener.setFavourite(favouriteAdapter);
    }
}
