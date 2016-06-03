package com.example.machine2.movietime.interfaces;

import com.example.machine2.movietime.adapters.FavouriteAdapter;
import com.example.machine2.movietime.adapters.MovieImageAdapter;

/**
 * Created by machine3 on 5/20/16.
 */
public interface MoviePosterListener {

    void refreshPoster(MovieImageAdapter imageAdapter);

    void setFavourite(FavouriteAdapter favouriteAdapter);

    void setErrorMessage(String statusMessage);
}
