package com.example.machine2.movietime.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;
import com.example.machine2.movietime.controllers.WeatherManager;
import com.example.machine2.movietime.controllers.WeatherListener;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherActivity extends BaseActivity implements WeatherListener {

    double temp;
    Button search;
    EditText searchCity;
    String cityName;
    WeatherManager weatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfragment);
        FragmentManager manager = getSupportFragmentManager();
        search = (Button) findViewById(R.id.searchs);
        searchCity = (EditText)findViewById(R.id.search);
        cityName = searchCity.getText().toString();
        System.out.println("city"+cityName);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != cityName) {
                    weatherManager = new WeatherManager();
                    weatherManager.getWeather(cityName);
                }
            }
        });
    }

    @Override
    public void onSuccess(UpdatedWeatherDetails updatedWeatherDetails) {
        temp = updatedWeatherDetails.getWeather();
    }
}