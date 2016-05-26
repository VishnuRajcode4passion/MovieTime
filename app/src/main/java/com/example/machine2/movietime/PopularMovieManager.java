package com.example.machine2.movietime;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine3 on 5/20/16.
 */
public class PopularMovieManager extends BaseManager implements NetworkListener {

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;

    NetworkCommunicator networkCommunicator;
    Context context;
    MoviePosterParser moviePosterParser;
    Request request;
    Gson gson;
    MoviesErrorResponse moviesErrorResponse;
    int code;

    String responseString;
    String statusMessage;

    public void movieManager(Context context, MovieAdapter movieAdapter) {

        this.context = context;
        this.movieAdapter = movieAdapter;
        request = new Request();
        request.setUrl(UrlProvider.POPULAR_URL);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        moviePosterParser = new MoviePosterParser();
        movieImageAdapter = moviePosterParser.parser(context,responseBody);
        movieAdapter.setImageAdapter(movieImageAdapter);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesErrorResponse = gson.fromJson(responseString,MoviesErrorResponse.class);
        code = moviesErrorResponse.getStatus_code();
        statusMessage = moviesErrorResponse.getStatus_message();
        Log.d("PopularMoviesManager","CODE "+code);
        Log.d("PopularMoviesManager","STATUS MESSAGE "+statusMessage);
        Toast.makeText(context,statusMessage,Toast.LENGTH_LONG).show();
    }
}
