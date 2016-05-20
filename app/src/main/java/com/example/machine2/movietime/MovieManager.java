package com.example.machine2.movietime;

import android.content.Context;

import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;

/**
 * Created by machine3 on 5/20/16.
 */
public class MovieManager implements NetworkListener {

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Context context;
    MoviePosterParser moviePosterParser;

    public void movieManager(Context context) {
        this.context = context;
        this.movieAdapter = (MovieAdapter) context;
        networkCommunicator = new NetworkCommunicator(context, UrlProvider.popularUrl);
        networkCommunicator.sendRequest(this);
    }

    @Override
    public void onResponse(byte[] responseBody) {
        moviePosterParser = new MoviePosterParser(context);
        movieImageAdapter = moviePosterParser.poster(responseBody);
        movieAdapter.setImageAdapter(movieImageAdapter);
    }
}
