package com.example.machine2.movietime.network;

import com.example.machine2.movietime.FavoriteAdapter;
import com.example.machine2.movietime.MovieImageAdapter;

/**
 * Created by machine3 on 5/20/16.
 */
public interface MovieAdapter
{
 void setImageAdapter(MovieImageAdapter imageAdapter);
 void setFavorite(FavoriteAdapter favoriteAdapter);
}
