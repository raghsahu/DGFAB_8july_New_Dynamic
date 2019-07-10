package com.example.dgfab.LoginandReg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dgfab.BusinessDashboard.Business_Drawer;
import com.example.dgfab.R;

public class ManuLoginActivity extends AppCompatActivity {

    Button LogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        LogIn=findViewById(R.id.logmid);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(ManuLoginActivity.this, Business_Drawer.class);
                startActivity(in);

                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });

    }
}
