package com.example.machine2.movietime.network;

import android.content.Context;

/**
 * Created by machine3 on 5/20/16.
 */
//interface created for the network classes

public interface NetworkListener {

    void onSuccess(Context context,byte[] responseBody);
    void onFailure(byte[] responseBody);
}
