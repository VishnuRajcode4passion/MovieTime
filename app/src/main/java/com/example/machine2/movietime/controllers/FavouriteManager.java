package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.adapters.FavouriteAdapter;
import com.example.machine2.movietime.models.MovieDetailResponse;
import com.example.machine2.movietime.interfaces.MoviePosterListener;
import com.example.machine2.movietime.interfaces.NetworkListener;
import com.google.gson.Gson;

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
