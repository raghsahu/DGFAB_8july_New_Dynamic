package com.example.dgfab.Activity.Buyer_guest_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.Buyers.Buyer_Main_Navigation;
import com.example.dgfab.R;

public class OtpActivity extends AppCompatActivity {

    TextView tv_opt_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        tv_opt_verify=findViewById(R.id.tv_opt_verify);
        tv_opt_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OtpActivity.this, Buyer_Main_Navigation.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });



    }
}
