package com.example.dgfab.Activity.Buyer_guest_login;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.R;

public class LoginActivity extends AppCompatActivity {

    TextView tv_optcode,forget_mobile,txtmobile,txtpincode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_optcode=findViewById(R.id.tv_optcode);
        forget_mobile=findViewById(R.id.forget_mobile);
        txtmobile=findViewById(R.id.txtmobile);
        txtpincode=findViewById(R.id.txtpincode);

        tv_optcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtmobile.getText().toString().length() !=0 && txtpincode.getText().toString().length() !=0) {
                    Intent in = new Intent(LoginActivity.this, OtpActivity.class);
                    in.putExtra("mob" , txtmobile.getText().toString());
                    in.putExtra("pinco" , txtpincode.getText().toString());
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                }

            }
        });


        forget_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(LoginActivity.this, Forget_Mobile.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);


            }
        });


    }
}
