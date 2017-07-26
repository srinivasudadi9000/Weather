package srinivasu.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 25-07-2017.
 */

public class Weather {
    @SerializedName("id")
    String id;

    @SerializedName("main")
    String main;

    @SerializedName("description")
    String description;

    public Weather(String id, String main, String description){
        this.id= id;this.main = main;this.description = description;
    }



    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}
