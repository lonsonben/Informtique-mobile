package com.example.bluetoothdivicesscanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private static boolean splashLoaded = false;
    //SharedPreferences prefs;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*prefs = PreferenceManager.getDefaultSharedPreferences(this);
        splashLoaded = prefs.getBoolean("Splash_Loaded", false);*/

       if (!splashLoaded) {
            setContentView(R.layout.activity_main);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Intent mainIntent = new Intent(MainActivity.this, PrincipalViewActivity.class);
                        MainActivity.this.startActivity(mainIntent);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        MainActivity.this.finish();
                    }
                }
            }, SPLASH_DISPLAY_LENGTH);

            splashLoaded = true;
            /*SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Splash_Loaded", true);
            editor.apply();*/
        }
        else{
            Intent mainIntent = new Intent(MainActivity.this, PrincipalViewActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        }
    }
}
