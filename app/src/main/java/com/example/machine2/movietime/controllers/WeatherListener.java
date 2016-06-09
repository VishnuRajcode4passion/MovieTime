package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.models.UpdatedWeatherDetails;

/**
 * Created by machine2 on 30/05/16.
 */
//interface created for setting the weather details into the activity

public interface WeatherListener {

    void onSuccess(UpdatedWeatherDetails updatedWeatherDetails);

}
