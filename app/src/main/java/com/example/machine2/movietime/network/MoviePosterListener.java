package com.example.machine2.movietime.network;

import com.example.machine2.movietime.lists.FavouriteAdapter;
import com.example.machine2.movietime.lists.MovieImageAdapter;

/**
 * Created by machine3 on 5/20/16.
 */
public interface MoviePosterListener {

    void refreshPoster(MovieImageAdapter imageAdapter);

    void setFavorite(FavouriteAdapter favouriteAdapter);

    void setErrorMessage(String statusMessage);
}
