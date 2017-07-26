package srinivasu.weather.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by USER on 25-07-2017.
 */

public class WeatherData {
    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("coord")
    JsonElement coord;

    @SerializedName("main")
    JsonElement mains;

    @SerializedName("wind")
    JsonElement wind;

    @SerializedName("sys")
    JsonElement sys;

    @SerializedName("dt")
    String dt;

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public JsonElement getCoord() {
        return coord;
    }

    public JsonElement getWind() {
        return wind;
    }

    public JsonElement getMains() {
        return mains;
    }

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JsonElement getSys() {
        return sys;
    }
}
