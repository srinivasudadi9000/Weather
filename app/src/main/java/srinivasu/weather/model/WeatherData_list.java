package srinivasu.weather.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 26-07-2017.
 */

public class WeatherData_list {

    @SerializedName("dt")
    int dt;

    @SerializedName("temp")
    JsonElement temp;

    @SerializedName("pressure")
    Double pressure;

    @SerializedName("humidity")
    int humidity;

    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("speed")
    Double speed;

    @SerializedName("clouds")
    int clouds;
    public WeatherData_list(Double speed,int dt,JsonElement temp,Double pressure,int humidity,List<Weather> weather,
                            int clouds){
        this.speed= speed;this.dt = dt;this.pressure = pressure;this.weather = weather;this.humidity = humidity;
        this.temp = temp;this.clouds = clouds;
    }


    public JsonElement getTemp() {
        return temp;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getSpeed() {
        return speed;
    }

    public int getDt() {
        return dt;
    }

    public int getClouds() {
        return clouds;
    }

    public int getHumidity() {
        return humidity;
    }

}
