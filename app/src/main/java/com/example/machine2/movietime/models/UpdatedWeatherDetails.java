package com.example.machine2.movietime.models;

import com.example.machine2.movietime.UrlProvider;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by machine2 on 30/05/16.
 */
public class UpdatedWeatherDetails {

    Double temp;
    String main;
    String image;
    String country;
    String city;
    Double wind_speed;
    Map<String, String> paramMap;
    RequestParams params;
    Requests request = new Requests();
    UrlProvider urlProvider;
    public Double getTemp()
    {
       return  temp;
    }

    public void setTemp(Double temp) {
       this.temp = temp;
    }

    public void setMain(String main)
    {
    this.main = main;
    }
    public String getMain()
    {
    return main;
    }
    public  void setWeatherImage(String image)
    {
        paramMap = request.getHeader();
        params = new RequestParams(paramMap);
        this.image = image;
        image = urlProvider.WEATHER_IMAGE + image + params;
        this.image = image;
    }
    public  String getWeatherImage()
    {
        return image;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }
    public String getCountry()
    {
      return country;
    }
    public void setWindSpeed(Double wind_speed)

    {
      this.wind_speed = wind_speed;
    }
    public Double getWindSpeed()
    {
     return wind_speed;
    }
    public void setCity(String city)
    {
       this.city = city;
    }
    public String getCity()
    {
     return  city;
    }
}
