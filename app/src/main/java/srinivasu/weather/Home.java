package srinivasu.weather;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srinivasu.weather.model.Weather;
import srinivasu.weather.model.WeatherData;
import srinivasu.weather.model.Weather_Main;
import srinivasu.weather.rest.ApiClient;
import srinivasu.weather.rest.ApiInterface;

public class Home extends Activity {

    @BindView(R.id.city_tv) TextView city_tv;
    @BindView(R.id.date_tv) TextView date_tv;
    @BindView(R.id.temp_tv) TextView temp_tv;
    @BindView(R.id.pressure_tv) TextView pressure_tv;
    @BindView(R.id.humidity_tv) TextView humidity_tv;
    @BindView(R.id.mintemp_tv) TextView mintemp_tv;
    @BindView(R.id.maxtemp_tv) TextView maxtemp_tv;
    @BindView(R.id.windspeed_tv) TextView windspeed_tv;
    @BindView(R.id.desc_tv) TextView desc_tv;
    @BindView(R.id.res_image) ImageView res_image;
    private final static String APP_ID = "fd88bdd6c7c80026f29e94b63439011d";
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);

        ApiInterface apiService =
                ApiClient.getWeatherDetails().create(ApiInterface.class);
        Call<WeatherData> call = apiService.getWeatherDetails("visakhapatnam", "fd88bdd6c7c80026f29e94b63439011d");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                List<Weather> weather = response.body().getWeather();
                Log.d("cord", response.body().getCoord().toString());
                for (int i = 0; i < weather.size(); i++) {
                    Log.d("des", weather.get(i).getDescription().toString());
                    Log.d("main", weather.get(i).getMain().toString());
                    desc_tv.setText(" "+weather.get(i).getMain().toString()+" \n" +" "+ weather.get(i).getDescription().toString());
                }
                Log.d("main", response.body().getMains().toString());
                Log.d("wind", response.body().getWind().toString());
                Log.d("sys", response.body().getSys().toString());
                Log.d("date", response.body().getDt().toString());
                Log.d("id", response.body().getId().toString());

                 int mm= Integer.parseInt(response.body().getDt());

                long unixSeconds = mm;
                Date dd = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy EEEE"); // the format of your date
               // sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
                String formattedDate = sdf.format(dd);
                //SimpleDateFormat day = new SimpleDateFormat("EEEE"); // the format of your date
                // String day_mon = sdf.format(day);
                System.out.println(formattedDate);


                city_tv.setText(response.body().getName().toString());
                date_tv.setText(formattedDate +" ");
               // pressure_tv.setText();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().getMains().toString());
                    String tempd = String.valueOf(jsonObject.getDouble("temp") - 273.15);
                    temp_tv.setText(tempd.substring(0,5)+(char) 0x00B0);

                    //String temp_min = String.valueOf(jsonObject.getDouble("temp_min"));
                    String temp_min= String.valueOf(jsonObject.getDouble("temp_min") - 273.15);
                     mintemp_tv.setText(temp_min.substring(0,2)+(char) 0x00B0);

                   // String temp_max = String.valueOf(jsonObject.getDouble("temp_max"));
                    String temp_max= String.valueOf(jsonObject.getDouble("temp_max") - 273.15);
                     maxtemp_tv.setText(temp_max.substring(0,2)+(char) 0x00B0);

                    String pressure = String.valueOf(jsonObject.getDouble("pressure"));
                    pressure_tv.setText(pressure +"kpa");

                    String humidity = String.valueOf(jsonObject.getDouble("humidity"));
                    humidity_tv.setText("H  : "+humidity +"%");

                    jsonObject = new JSONObject(response.body().getWind().toString());
                    String wind = String.valueOf(jsonObject.getDouble("speed")*18/5);
                    windspeed_tv.setText(wind.substring(0,2));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
