package com.example.dgfab.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.R;

public class LoginActivity extends AppCompatActivity {

    TextView tv_optcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_optcode=findViewById(R.id.tv_optcode);

        tv_optcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


    }
}
