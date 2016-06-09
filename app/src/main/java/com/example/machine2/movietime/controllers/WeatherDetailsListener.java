package com.example.machine2.movietime.controllers;

import android.content.Context;

import com.example.machine2.movietime.models.UpdatedWeatherDetails;

/**
 * Created by machine2 on 06/06/16.
 */
//interface for the weather activity

public interface WeatherDetailsListener {

    void setWeatherDetails(Context context,UpdatedWeatherDetails updatedWeatherDetails);
    void setErrorMessage(String statusMessage);
}
