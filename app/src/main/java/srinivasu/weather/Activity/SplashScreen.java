package srinivasu.weather.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import srinivasu.weather.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent i = new Intent(SplashScreen.this,Location.class);
                 startActivity(i);
             }
         },2000);

    }
}
