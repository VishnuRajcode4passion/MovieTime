package com.example.machine2.movietime;

/**
 * Created by machine3 on 5/20/16.
 */
import android.content.Context;
import com.google.gson.Gson;

/**
 * Created by machine2 on 09/05/16.
 */
//Manager class of the movie sendRequest
public class MoviePosterParser {

    Gson gson;
    MoviesResponse moviesResponse;
    MovieImageAdapter imageAdapter;
    String responseString;
    Context context;

    public MoviePosterParser(Context context) {
        this.context = context;
    }

    MovieImageAdapter poster(byte[] responseBody) {
        responseString = new String(responseBody);
        gson = new Gson();
        moviesResponse = gson.fromJson(responseString, MoviesResponse.class);
        imageAdapter = new MovieImageAdapter(context, moviesResponse.getResults());
        return imageAdapter;
    }
}