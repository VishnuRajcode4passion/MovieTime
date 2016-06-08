package com.example.machine2.movietime.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.controllers.WeatherManager;
import com.example.machine2.movietime.interfaces.WeatherDetailsListener;
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
        city_name = bundle.getString("cityname");
        System.out.println("citys"+city_name);

        WeatherManager weatherManager = new WeatherManager();
        weatherManager.getWeather(this,city_name);

        showDialog();


    }

    @Override
    public void setWeatherDetails(UpdatedWeatherDetails updatedWeatherDetails) {
      String main;
        main = updatedWeatherDetails.getTemp();
        System.out.println("main"+main);
        temp.setText(main);
        dismissDialog();
    }
}