package com.example.dgfab.Activity.SplashScreens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dgfab.R;

public class Splash_Activity extends AppCompatActivity {

    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {

                Intent i = new Intent(Splash_Activity.this, App_Intro.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }



}
