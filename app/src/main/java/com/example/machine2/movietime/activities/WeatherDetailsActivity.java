package com.example.machine2.movietime.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.controllers.WeatherManager;
import com.example.machine2.movietime.controllers.WeatherDetailsListener;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;

/**
 * Created by machine2 on 06/06/16.
 */
public class WeatherDetailsActivity extends  BaseActivity implements WeatherDetailsListener {
    TextView temp;
    Bundle bundle;
    String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayfragment);
        temp = (TextView)findViewById(R.id.textView5);
        bundle = getIntent().getExtras();
       city_name = bundle.getString("cityName");

        WeatherManager weatherManager = new WeatherManager();
        weatherManager.getWeather(this,city_name);

        showDialog();


    }

    @Override
    public void setWeatherDetails(UpdatedWeatherDetails updatedWeatherDetails) {
       double temperature;
        temperature = updatedWeatherDetails.getTemp();

        temp.setText(String.valueOf(temperature));
    }
}