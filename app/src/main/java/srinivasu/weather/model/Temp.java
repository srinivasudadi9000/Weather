package srinivasu.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 25-07-2017.
 */

public class Temp {
    @SerializedName("day")
    Double day;

    @SerializedName("min")
    Double min;

    @SerializedName("max")
    Double max;

    @SerializedName("night")
    Double night;

    @SerializedName("eve")
    Double eve;

    @SerializedName("morn")
    Double morn;


    public Temp(Double day,Double min,Double max,Double night,Double eve, Double morn) {
        this.day = day; this.min = min;this.max = max;this.night= night;this.eve= eve;this.morn = morn;

    }

    public Double getDay() {
        return day;
    }

    public Double getEve() {
        return eve;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMorn() {
        return morn;
    }

    public Double getNight() {
        return night;
    }
}
