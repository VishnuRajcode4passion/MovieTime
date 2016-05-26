package com.example.machine2.movietime;

import com.example.machine2.movietime.network.DetailsAdapter;
import com.example.machine2.movietime.network.MovieAdapter;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsManager extends BaseManager implements NetworkListener{

    MovieAdapter movieAdapter;
    MovieImageAdapter movieImageAdapter;
    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;
    DetailResponse detailResponse;
    String responseString;
    DetailsAdapter detailsAdapter;
    SetDetails setDetails = new SetDetails();

    public void movieManager(DetailsAdapter detailsAdapter, String id) {

        this.detailsAdapter = detailsAdapter;
        this.id = id;
        request.setUrl(UrlProvider.movieDetailsUrl+id);
        request.setHeaders(getHeaders());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        detailResponse = gson.fromJson(responseString, DetailResponse.class);
        detailsAdapter.setMovieDetails(setDetails);
        setDetails.settitle(detailResponse.getOriginal_title());


    }

    @Override
    public void onFailure(int statusCode) {

    }
}
