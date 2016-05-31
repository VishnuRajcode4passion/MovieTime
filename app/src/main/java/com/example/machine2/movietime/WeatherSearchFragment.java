package com.example.machine2.movietime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherSearchFragment extends Fragment {
    Button search;
    EditText searchCity;
    String cityName;
    WeatherManager weatherManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchfragment, container, false);

        search = (Button) view.findViewById(R.id.searchs);
        searchCity = (EditText) view.findViewById(R.id.search);
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
        return view;
    }


}
