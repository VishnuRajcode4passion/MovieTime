package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;
import com.example.machine2.movietime.models.WeatherResponse;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.example.machine2.movietime.parser.WeatherDetailParser;

/**
 * Created by machine2 on 30/05/16.
 */
//Manager class defined for the weather activity
public class WeatherManager extends BaseManager implements NetworkListener {

    //variable declarations
    WeatherDetailsListener weatherDetailsListener;

    //method for getting the URL and headers
    public void getWeather(Context context,WeatherDetailsListener weatherDetailsListener, String city_name) {

        Requests request;
        this.weatherDetailsListener = weatherDetailsListener;
        request = new Requests();
        request.setUrl(UrlProvider.WEATHER_URL+city_name);
        request.setHeader(getHeader());
        NetworkCommunicator networkCommunicator = new NetworkCommunicator(context);
        networkCommunicator.sendRequest(this, request);
    }

    //implemeting the methods ofNetworkListener.
    @Override
    public void onSuccess(Context context,byte[] responseBody) {

        UpdatedWeatherDetails updatedWeatherDetails = new UpdatedWeatherDetails();
        WeatherDetailParser weatherDetailParser = new WeatherDetailParser();
        WeatherResponse weatherResponse;

        weatherResponse = weatherDetailParser.parse(responseBody);
        System.out.println("weatherResponse  " + weatherResponse);
        System.out.println("weatherResponse.getMain()" + weatherResponse.getDescription());
        updatedWeatherDetails.setTemp(weatherResponse.getMain());

        weatherDetailsListener.setWeatherDetails(updatedWeatherDetails);
    }

    //to display the error message ,if there is problem in fetching the contents from server.
    @Override
    public void onFailure( byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);

        weatherDetailsListener.setErrorMessage(statusMessage);
    }
}

