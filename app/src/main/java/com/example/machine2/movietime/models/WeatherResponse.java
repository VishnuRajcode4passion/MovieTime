package com.example.machine2.movietime.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by machine2 on 23/05/16.
 */
public class WeatherResponse {


    /**
     * lon : 76.6
     * lat : 8.88
     */

    private CoordBean coord;
    /**
     * coord : {"lon":76.6,"lat":8.88}
     * weather : [{"id":501,"main":"Rain","description":"moderate rain","icon":"10d"}]
     * base : stations
     * main : {"temp":297.467,"pressure":1009.18,"humidity":100,"temp_min":297.467,"temp_max":297.467,"sea_level":1023.69,"grnd_level":1009.18}
     * wind : {"speed":2.1,"deg":227.502}
     * rain : {"3h":8.375}
     * clouds : {"all":92}
     * dt : 1465447706
     * sys : {"message":0.0076,"country":"IN","sunrise":1465432442,"sunset":1465477913}
     * id : 1259091
     * name : Kollam
     * cod : 200
     */

    private String base;
    /**
     * temp : 297.467
     * pressure : 1009.18
     * humidity : 100
     * temp_min : 297.467
     * temp_max : 297.467
     * sea_level : 1023.69
     * grnd_level : 1009.18
     */

    private MainBean main;
    /**
     * speed : 2.1
     * deg : 227.502
     */

    private WindBean wind;
    /**
     * 3h : 8.375
     */

    private RainBean rain;
    /**
     * all : 92
     */

    private CloudsBean clouds;
    private int dt;
    /**
     * message : 0.0076
     * country : IN
     * sunrise : 1465432442
     * sunset : 1465477913
     */

    private SysBean sys;
    private int id;
    private String name;
    private int cod;
    /**
     * id : 501
     * main : Rain
     * description : moderate rain
     * icon : 10d
     */

    private List<WeatherBean> weather;

    public CoordBean getCoord() {
        return coord;
    }

    public void setCoord(CoordBean coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public RainBean getRain() {
        return rain;
    }

    public void setRain(RainBean rain) {
        this.rain = rain;
    }

    public CloudsBean getClouds() {
        return clouds;
    }

    public void setClouds(CloudsBean clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public SysBean getSys() {
        return sys;
    }

    public void setSys(SysBean sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<WeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherBean> weather) {
        this.weather = weather;
    }

    public static class CoordBean {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class MainBean {
        private double temp;
        private double pressure;
        private int humidity;
        private double temp_min;
        private double temp_max;
        private double sea_level;
        private double grnd_level;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getSea_level() {
            return sea_level;
        }

        public void setSea_level(double sea_level) {
            this.sea_level = sea_level;
        }

        public double getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(double grnd_level) {
            this.grnd_level = grnd_level;
        }
    }

    public static class WindBean {
        private double speed;
        private double deg;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }
    }

    public static class RainBean {
        @SerializedName("3h")
        private double value3h;

        public double getValue3h() {
            return value3h;
        }

        public void setValue3h(double value3h) {
            this.value3h = value3h;
        }
    }

    public static class CloudsBean {
        private int all;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    public static class SysBean {
        private double message;
        private String country;
        private int sunrise;
        private int sunset;

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }
    }

    public static class WeatherBean {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
