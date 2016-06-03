package com.example.machine2.movietime.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.machine2.movietime.lists.MovieImageAdapter;
import com.example.machine2.movietime.MoviePosterParser;
import com.example.machine2.movietime.models.Request;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MoviesErrorResponse;
import com.example.machine2.movietime.models.WeatherResponse;
import com.example.machine2.movietime.network.MoviePosterListener;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.example.machine2.movietime.network.WeatherListener;
import com.google.gson.Gson;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherManager extends BaseManager implements NetworkListener {
    MoviePosterListener movieAdapter;
    MovieImageAdapter movieImageAdapter;


    Context context;
    MoviePosterParser moviePosterParser;

    MoviesErrorResponse moviesErrorResponse;
    int code;
    String statusMessage;
    NetworkCommunicator networkCommunicator;
    Request request = new Request();
    String id;
    Gson gson;
    String cityName;
    WeatherResponse weatherResponse;
    String responseString;
   WeatherListener weatherListener;
    UpdatedWeatherDetails updatedWeatherDetails = new UpdatedWeatherDetails();

    public void getWeather(String cityName) {

        this.cityName = cityName;
        request.setUrl(UrlProvider.WEATHER_URL + cityName);
        request.setHeader(getHeader());
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }


    @Override
    public void onSuccess(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        weatherResponse = gson.fromJson(responseString, WeatherResponse.class);
        WeatherResponse.MainBean mainBean = new WeatherResponse.MainBean();
        updatedWeatherDetails.setWeather(mainBean.getTemp());
        weatherListener.onSuccess(updatedWeatherDetails);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        moviesErrorResponse = gson.fromJson(responseString,MoviesErrorResponse.class);
        code = moviesErrorResponse.getStatus_code();
        statusMessage = moviesErrorResponse.getStatus_message();
        Log.d("PopularMoviesManager", "CODE " + code);
        Log.d("PopularMoviesManager","STATUS MESSAGE "+statusMessage);
        Toast.makeText(context, statusMessage, Toast.LENGTH_LONG).show();

    }
}
