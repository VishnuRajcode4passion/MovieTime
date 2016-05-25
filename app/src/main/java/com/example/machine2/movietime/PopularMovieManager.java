package com.example.machine2.movietime;

import android.content.Context;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

/**
 * Created by machine3 on 5/20/16.
 */
public class PopularMovieManager implements NetworkListener {

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Context context;
    MoviePosterParser moviePosterParser;
    Gson gson;
    MoviesResponse moviesResponse;
    MovieImageAdapter imageAdapter;
    String responseString;

    public void movieManager(Context context) {
        this.context = context;
        this.movieAdapter = (MovieAdapter) context;
        RequestParams params = new RequestParams();
        params.put("api_key", "efc0d91dd29ee74d0c55029e31266793");
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, UrlProvider.popularUrl,params);
    }

    @Override
    public void onSuccess(byte[] responseBody) {
        responseString = new String(responseBody);
        gson = new Gson();
        moviesResponse = gson.fromJson(responseString, MoviesResponse.class);
        imageAdapter = new MovieImageAdapter(context, moviesResponse.getResults());
        movieAdapter.setImageAdapter(imageAdapter);
    }
}
