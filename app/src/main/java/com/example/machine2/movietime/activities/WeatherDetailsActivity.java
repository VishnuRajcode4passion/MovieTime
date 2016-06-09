package com.example.machine2.movietime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.controllers.WeatherDetailsListener;
import com.example.machine2.movietime.controllers.WeatherManager;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;
import com.squareup.picasso.Picasso;

/**
 * Created by machine2 on 06/06/16.
 */
public class WeatherDetailsActivity extends  BaseActivity implements WeatherDetailsListener {
    //variable declarations

    TextView temp;
    TextView main;
    TextView country;
    TextView windSpeed;
    ImageView weatherImage;
    ImageView imageView;
    Bundle bundle;
    String city_name;
    ImageView Home;
    ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayfragment);
        temp = (TextView)findViewById(R.id.textView9);
        main = (TextView)findViewById(R.id.textView8);
        windSpeed = (TextView)findViewById(R.id.textView7);
        country = (TextView)findViewById(R.id.textView5);
        weatherImage = (ImageView)findViewById(R.id.showicon);
        Home = (ImageView)findViewById(R.id.HomePage);
        arrow = (ImageView) findViewById(R.id.imageView3);

        bundle = getIntent().getExtras();
        city_name = bundle.getString("cityname");
        System.out.println("citys" + city_name);

        WeatherManager weatherManager = new WeatherManager();
        weatherManager.getWeather(this,this,city_name);

        showDialog();

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherDetailsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(WeatherDetailsActivity.this, WeatherActivity.class);
                startActivity(intent);

            }
        });


    }
//implementing inteface to set the details

    @Override
    public void setWeatherDetails(UpdatedWeatherDetails updatedWeatherDetails) {

        Double temps;
        Double wind_speed;

        String mains;
        String image;
        String country_name;
        String city_name;

        temps = updatedWeatherDetails.getTemp();
        mains = updatedWeatherDetails.getMain();
        image = updatedWeatherDetails.getWeatherImage();
        country_name = updatedWeatherDetails.getCountry();
        city_name = updatedWeatherDetails.getCity();
        wind_speed = updatedWeatherDetails.getWindSpeed();

        temp.setText(String.valueOf(temps)+ " °C");
        main.setText(mains);
        country.setText(city_name+","+country_name);
        windSpeed.setText(String.valueOf(wind_speed+"mps"));
        Picasso.with(this).load(image).resize(30,40).into(weatherImage);

        dismissDialog();
    }
}