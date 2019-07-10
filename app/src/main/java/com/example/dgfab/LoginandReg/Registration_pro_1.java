package com.example.dgfab.LoginandReg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dgfab.Activity.Buyer_guest_login.LoginActivity;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.R;

public class Registration_pro_1 extends AppCompatActivity {
    Button btnnext,btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_pro_1);

        btnnext = findViewById(R.id.btnnext);
        btnlog = findViewById(R.id.btnlog);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration_pro_1.this , ManuLoginActivity.class);
                startActivity(intent);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration_pro_1.this , Registration_Step_1.class);
                startActivity(intent);

            }
        });
    }
}
