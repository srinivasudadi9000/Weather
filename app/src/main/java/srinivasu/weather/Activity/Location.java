package srinivasu.weather.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import srinivasu.weather.R;
import srinivasu.weather.validation.Validation;

public class Location extends Activity {
    @BindView(R.id.location_et)
    EditText location_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        ButterKnife.bind(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }
    }

    @OnClick(R.id.search_bt)
    public void search() {
        if (Validation.internet(Location.this)) {
            if (location_et.getText().toString().length() == 0) {
                Validation.showalert("Location Should not be null", Location.this);
            } else {
                Intent home = new Intent(Location.this, Home.class);
                home.putExtra("location", location_et.getText().toString());
                startActivity(home);
                location_et.setText("");
            }
        } else {
            Validation.showalert("Check Your Internet Connection !!", Location.this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("permission", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
}

