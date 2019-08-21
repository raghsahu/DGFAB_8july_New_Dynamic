package com.example.dgfab.Activity.SplashScreens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dgfab.Activity.Buyer_guest_login.Use_reason_Activity;
import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.Buyers.Buyer_Main_Navigation;
import com.example.dgfab.LoginandReg.ManuLoginActivity;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

public class Splash_Activity extends AppCompatActivity {

    private int SPLASH_TIME_OUT = 2000;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.new_add_product);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(this);


          new Handler().postDelayed(new Runnable() {

//         * Showing splash screen with a timer. This will be useful when you
//         * want to show case your app logo / company

            @Override
            public void run() {
                try {
                    String s = sessionManager.getU_Type();
                if(sessionManager.getU_Type().equals("3") || sessionManager.getU_Type().equals("2"))
                {
                    Intent i = new Intent(Splash_Activity.this, Business_Dashboard_Main.class);

                    startActivity(i);
                    finish();
                }else  if(sessionManager.getU_Type().equals("1")){
                    Intent i = new Intent(Splash_Activity.this, Buyer_Main_Navigation.class);

                    startActivity(i);
                    finish();
                }else {
                    Intent intent = new Intent(Splash_Activity.this , Use_reason_Activity.class);
                    startActivity(intent);
                }
                }catch (Exception e)
                {
                    Intent i = new Intent(Splash_Activity.this, App_Intro.class);

                    startActivity(i);
                    finish();
                    e.printStackTrace();
                }

            }
        }, SPLASH_TIME_OUT);
    }


//    }
}
