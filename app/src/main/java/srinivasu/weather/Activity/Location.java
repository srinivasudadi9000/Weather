package srinivasu.weather.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import srinivasu.weather.R;

public class Location extends Activity {
    @BindView(R.id.location_et)
    EditText location_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.search_bt)
    public void search(){
        Intent home = new Intent(Location.this,Home.class);
        home.putExtra("location",location_et.getText().toString());
        startActivity(home);
        location_et.setText("");
    }
}
