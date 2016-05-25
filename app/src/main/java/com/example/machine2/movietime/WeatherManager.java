package com.example.machine2.movietime;


import android.support.v4.app.FragmentActivity;

import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

/**
 * Created by machine2 on 23/05/16.
 */
public class WeatherManager implements NetworkListener {
    UrlProvider urlProvider;
    String Url;
    String responseString;
    Gson gson;
    WeatherResponse weatherResponse;
    private FragmentActivity Context;
    FragmentB f2;


    public void manager(CharSequence city) {
       // Url= urlProvider.WeatherUrl;
        //String URLprovider = Url+city;
        RequestParams params = new RequestParams();
        params.put("api_key", "45df4fca7d202600be0e657e2d0a9dcd");
        NetworkCommunicator networkCommunicator = new NetworkCommunicator();
        networkCommunicator.weatherRequest(this,city,params);


    }

    @Override
    public void onSuccess(byte[] responseBody) {


        responseString = new String(responseBody);
        gson = new Gson();
        weatherResponse = gson.fromJson(responseString, WeatherResponse.class);
        f2=new FragmentB();
        f2.change(weatherResponse);

    }
}
