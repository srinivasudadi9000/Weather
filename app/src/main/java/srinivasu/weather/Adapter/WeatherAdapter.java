package srinivasu.weather.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import srinivasu.weather.R;
import srinivasu.weather.model.WeatherData_list;

import static android.R.attr.value;

/**
 * Created by USER on 26-07-2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {
    private List<WeatherData_list> weatherData_lists;
    private int rowLayout;
    private Context context;

    public WeatherAdapter(List<WeatherData_list> weatherData_lists, int rowLayout, Context context) {
        this.weatherData_lists = weatherData_lists;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        holder.daydes_stv.setText(weatherData_lists.get(position).getWeather().get(0).getDescription().toString());
        String myimage = weatherData_lists.get(position).getWeather().get(0).getMain().toLowerCase().toString();

        if (myimage.startsWith("s") || myimage.substring(0,3).equals("cle")){
            Picasso.with(context)
                    .load(R.drawable.sunny)
                     .into(holder.weather_img);
        }else if (myimage.startsWith("r")){
            Picasso.with(context)
                    .load(R.drawable.rain)
                    .into(holder.weather_img);
        }
        int mm = weatherData_lists.get(position).getDt();

        long unixSeconds = mm;
        Date dd = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE"); // the format of your date
        String formattedDate = sdf.format(dd);
        holder.date_stv.setText(formattedDate);
        try {
            JSONObject jsonObject = new JSONObject(weatherData_lists.get(position).getTemp().toString());
            jsonObject.getString("min").toString();
            jsonObject.getString("max").toString();
            jsonObject.getString("night").toString();
            jsonObject.getString("eve").toString();
            jsonObject.getString("morn").toString();

            String day = String.valueOf(jsonObject.getDouble("day") - 273.15);
            holder.day_stv.setText("Temp " + "\n" + day.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

            String min = String.valueOf(jsonObject.getDouble("min") - 273.15);
            holder.mintemp_stv.setText("Min " + "\n" + min.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

            String max = String.valueOf(jsonObject.getDouble("max") - 273.15);
            holder.maxtemp_stv.setText("Max " + "\n" + max.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

            String night = String.valueOf(jsonObject.getDouble("night") - 273.15);
            holder.night_stv.setText("Night " + "\n" + night.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

            String eve = String.valueOf(jsonObject.getDouble("eve") - 273.15);
            holder.eve_stv.setText("Eve " + "\n" + eve.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

            String morn = String.valueOf(jsonObject.getDouble("morn") - 273.15);
            holder.mor_stv.setText("Morn " + "\n" + morn.substring(0, 4) + "\n" + (char) 0x00B0 + "C");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return weatherData_lists.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder {
        TextView daydes_stv, date_stv, day_stv, mintemp_stv, maxtemp_stv, night_stv, eve_stv, mor_stv;
        ImageView weather_img;
        public WeatherHolder(View itemView) {
            super(itemView);
            daydes_stv = (TextView) itemView.findViewById(R.id.daydes_stv);
            day_stv = (TextView) itemView.findViewById(R.id.day_stv);
            date_stv = (TextView) itemView.findViewById(R.id.date_stv);
            mintemp_stv = (TextView) itemView.findViewById(R.id.mintemp_stv);
            maxtemp_stv = (TextView) itemView.findViewById(R.id.maxtemp_stv);
            night_stv = (TextView) itemView.findViewById(R.id.night_stv);
            eve_stv = (TextView) itemView.findViewById(R.id.eve_stv);
            mor_stv = (TextView) itemView.findViewById(R.id.mor_stv);
            weather_img =(ImageView)itemView.findViewById(R.id.weather_img);
        }
    }
}
