package com.example.machine2.movietime.network;

import com.example.machine2.movietime.TrailerAdapter;
import com.example.machine2.movietime.UpdatedMovieDetails;

/**
 * Created by machine2 on 26/05/16.
 */
public interface DetailsAdapter {
    void setMovieDetails(UpdatedMovieDetails detailResponse);
    void movieTrailer(TrailerAdapter trailerAdapter);
}
