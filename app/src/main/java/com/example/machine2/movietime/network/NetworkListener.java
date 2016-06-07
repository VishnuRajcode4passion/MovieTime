package com.example.machine2.movietime.network;

/**
 * Created by machine3 on 5/20/16.
 */
//interface created for the network classes

public interface NetworkListener {
    void onSuccess(byte[] responseBody);
    void onFailure(byte[] responseBody);
}
