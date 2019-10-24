package com.example.dgfab.Activity.Buyer_guest_login;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.R;

public class Forget_Mobile extends AppCompatActivity {

    TextView tv_email_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__mobile);

        tv_email_code=findViewById(R.id.tv_email_code);

        tv_email_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Forget_Mobile.this, EmailOtpActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });

    }
}
