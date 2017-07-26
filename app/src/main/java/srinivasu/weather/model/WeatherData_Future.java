package srinivasu.weather.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by USER on 26-07-2017.
 */

public class WeatherData_Future {

    @SerializedName("city")
    JSONObject city;

    @SerializedName("list")
    List<WeatherData_list> list;



    public JSONObject getCity() {
        return city;
    }

    public List<WeatherData_list> getList() {
        return list;
    }


}
