package srinivasu.weather.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srinivasu.weather.Adapter.WeatherAdapter;
import srinivasu.weather.R;
import srinivasu.weather.model.Weather;
import srinivasu.weather.model.WeatherData;

import srinivasu.weather.model.WeatherData_Future;
import srinivasu.weather.model.WeatherData_list;
import srinivasu.weather.rest.ApiClient;
import srinivasu.weather.rest.ApiInterface;

public class Home extends Activity {

    @BindView(R.id.city_tv)
    TextView city_tv;
    @BindView(R.id.date_tv)
    TextView date_tv;
    @BindView(R.id.temp_tv)
    TextView temp_tv;
    @BindView(R.id.pressure_tv)
    TextView pressure_tv;
    @BindView(R.id.humidity_tv)
    TextView humidity_tv;
    @BindView(R.id.mintemp_tv)
    TextView mintemp_tv;
    @BindView(R.id.maxtemp_tv)
    TextView maxtemp_tv;
    @BindView(R.id.windspeed_tv)
    TextView windspeed_tv;
    @BindView(R.id.desc_tv)
    TextView desc_tv;
    @BindView(R.id.res_image)
    ImageView res_image;
    @BindView(R.id.sunset_tv)
    TextView sunset_tv;
    @BindView(R.id.weather_recyler)
    RecyclerView weather_recyler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);

        weather_recyler.setLayoutManager(new LinearLayoutManager(this));

        Weather_Forecast(getIntent().getStringExtra("location"));

    }

    @OnClick(R.id.back_img)
    public void onclick() {
        finish();
    }

    public void Weather_Forecast(String location) {
        ApiInterface apiService = ApiClient.getWeatherDetails().create(ApiInterface.class);
        Call<WeatherData> call = apiService.getWeatherDetails(location, "fd88bdd6c7c80026f29e94b63439011d");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                List<Weather> weather = response.body().getWeather();
                Log.d("cord", response.body().getCoord().toString());
                for (int i = 0; i < weather.size(); i++) {
                    Log.d("des", weather.get(i).getDescription().toString());
                    Log.d("main", weather.get(i).getMain().toString());
                    desc_tv.setText(" " + weather.get(i).getMain().toString() + " \n" + " " + weather.get(i).getDescription().toString());
                }
                Log.d("main", response.body().getMains().toString());
                Log.d("wind", response.body().getWind().toString());
                Log.d("sys", response.body().getSys().toString());
                Log.d("date", response.body().getDt().toString());
                Log.d("id", response.body().getId().toString());


                String myimage = response.body().getMains().toString();

                if (myimage.startsWith("s") || myimage.substring(0,3).equals("cle")) {
                    Picasso.with(Home.this)
                            .load(R.drawable.sunny)
                            .into(res_image);
                } else if (myimage.startsWith("r")) {
                    Picasso.with(Home.this)
                            .load(R.drawable.rain)
                            .into(res_image);
                }
                int mm = Integer.parseInt(response.body().getDt());

                long unixSeconds = mm;
                Date dd = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy EEEE"); // the format of your date
                String formattedDate = sdf.format(dd);

                city_tv.setText(response.body().getName().toString());
                date_tv.setText(formattedDate + " ");
                // pressure_tv.setText();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().getMains().toString());
                    String tempd = String.valueOf(jsonObject.getDouble("temp") - 273.15);
                    temp_tv.setText("Temp : " + tempd.substring(0, 5) + (char) 0x00B0 + "C");

                    String temp_min = String.valueOf(jsonObject.getDouble("temp_min") - 273.15);
                    mintemp_tv.setText("Min Temp :" + temp_min.substring(0, 2) + (char) 0x00B0 + "C");

                    String temp_max = String.valueOf(jsonObject.getDouble("temp_max") - 273.15);
                    maxtemp_tv.setText("Max Temp :" + temp_max.substring(0, 2) + (char) 0x00B0 + "C");

                    String pressure = String.valueOf(jsonObject.getDouble("pressure"));
                    pressure_tv.setText("Pressure : " + pressure + "kpa");

                    String humidity = String.valueOf(jsonObject.getDouble("humidity"));
                    humidity_tv.setText("Humidity  : " + humidity + "%");

                    jsonObject = new JSONObject(response.body().getWind().toString());
                    String wind = String.valueOf(jsonObject.getDouble("speed") * 18 / 5);
                    windspeed_tv.setText("Wind Speed :" + wind.substring(0, 2) + "Km / Hr");

                    Weather_Forecast_Future(response.body().getId().toString());


                    JSONObject sunset = new JSONObject(response.body().getSys().toString());
                    int sun =  sunset.getInt("sunrise");
                    long unixSec = sun;
                     Date date = new Date(unixSec * 1000L); // *1000 is to convert seconds to milliseconds
                    SimpleDateFormat sunset_f = new SimpleDateFormat("hh:mm aa"); // the format of your date
                    String sunset_value = sunset_f.format(date);

                    int sunseti =  sunset.getInt("sunset");
                    long unixSecset = sunseti;
                    Date date_s = new Date(unixSecset * 1000L); // *1000 is to convert seconds to milliseconds
                    SimpleDateFormat sunset_f_s = new SimpleDateFormat("hh:mm aa"); // the format of your date
                    String sunset_value_s = sunset_f_s.format(date_s);


                    sunset_tv.setText("SunRise :" +sunset_value +"\n"+"SunSet : "+sunset_value_s);
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

    public void Weather_Forecast_Future(String id) {
        ApiInterface apiService = ApiClient.getWeatherDetails_future().create(ApiInterface.class);
        Call<WeatherData_Future> call = apiService.getWeatherDetails_future(id, "fd88bdd6c7c80026f29e94b63439011d");

        call.enqueue(new Callback<WeatherData_Future>() {
            @Override
            public void onResponse(Call<WeatherData_Future> call, Response<WeatherData_Future> response) {
                String size = String.valueOf(response.body().getList().size());
                // Toast.makeText(getBaseContext(), size + "   " + response.body().getList().toString(), Toast.LENGTH_SHORT).show();
                List<WeatherData_list> weather = response.body().getList();
                weather_recyler.setAdapter(new WeatherAdapter(weather, R.layout.weather_single, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<WeatherData_Future> call, Throwable throwable) {
                Toast.makeText(getBaseContext(), throwable.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
