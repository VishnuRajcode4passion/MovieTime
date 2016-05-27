package com.example.machine2.movietime;

import android.content.Context;

import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavoriteManager extends BaseManager implements NetworkListener{

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;

    DetailResponse detailResponse;
    MovieDatabase db;
    String responseString;
    DetailsAdapter detailsAdapter;
    UpdatedMovieDetails updatedMovieDetails = new UpdatedMovieDetails();
    Context context;
    ArrayList<String> images;
    ArrayList<String> mid;
    FavoriteAdapter favoriteAdapter;

    public FavoriteManager(Context context, ArrayList<String> images, ArrayList<String> mid) {
        this.context = context;
        this.images = images;
        this.mid = mid;



    }

    public void movieManager(MovieAdapter movieAdapter, String id) {

        this.movieAdapter = movieAdapter;
        this.id = id;
        request.setUrl(updatedMovieDetails.getImage());
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, DetailResponse.class);
        favoriteAdapter = new FavoriteAdapter(context,images,mid);
        db.open();
        //Toast.makeText(, "Added to Favoruite", Toast.LENGTH_LONG).show();
        db.close();
        movieAdapter.setFavorite(favoriteAdapter);



    }

    @Override
    public void onFailure(byte[] responseBody) {

    }
}
