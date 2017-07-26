package srinivasu.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 25-07-2017.
 */

public class Weather_Main {
    @SerializedName("temp")
    String temp;


    public Weather_Main(String temp ){
        this.temp= temp;
    }


    public String getTemp() {
        return temp;
    }
}
