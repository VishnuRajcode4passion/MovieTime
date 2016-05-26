package com.example.machine2.movietime;

import android.content.Context;

import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;

/**
 * Created by machine2 on 26/05/16.
 */
public class TopRatedMovieManager extends BaseManager implements NetworkListener {


        MovieAdapter movieAdapter;
        MovieImageAdapter movieImageAdapter;
        NetworkCommunicator networkCommunicator;
        Context context;
        MoviePosterParser moviePosterParser;
        Request request = new Request();

        public void movieManager(Context context, MovieAdapter movieAdapter) {

            this.context = context;
            this.movieAdapter = movieAdapter;
            request.setUrl(UrlProvider.TOP_RATED_URL);
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

        }
    }


