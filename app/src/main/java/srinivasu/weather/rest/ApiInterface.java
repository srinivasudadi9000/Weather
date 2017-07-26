package srinivasu.weather.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import srinivasu.weather.model.WeatherData;
import srinivasu.weather.model.WeatherData_Future;

/**
 * Created by USER on 25-07-2017.
 */

public interface ApiInterface {
    @GET("weather")
    Call<WeatherData> getWeatherDetails(@Query("q") String q,@Query("appid") String appid);

    @GET("forecast/daily")
    Call<WeatherData_Future> getWeatherDetails_future(@Query("id") String q, @Query("appid") String appid);
}
