package com.example.machine2.movietime.interfaces;

/**
 * Created by machine3 on 5/20/16.
 */
public interface NetworkListener {
    void onSuccess(byte[] responseBody);
    void onFailure(byte[] responseBody);
}
