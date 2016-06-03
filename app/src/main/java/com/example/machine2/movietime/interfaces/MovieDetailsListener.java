package com.example.machine2.movietime.interfaces;

import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.models.UpdatedMovieDetails;

/**
 * Created by machine2 on 26/05/16.
 */
public interface MovieDetailsListener {

    void setMovieDetails(UpdatedMovieDetails detailResponse);
    void movieTrailer(MovieTrailerAdapter movieTrailerAdapter);
    void setErrorMessage(String statusMessage);
}
