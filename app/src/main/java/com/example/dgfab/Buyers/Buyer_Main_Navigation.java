package com.example.dgfab.Buyers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.Activity.Buyer_guest_login.Use_reason_Activity;
import com.example.dgfab.R;
public class Buyer_Main_Navigation extends AppCompatActivity {
    TextView logorreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer__main__navigation);

        logorreg =findViewById(R.id.logorreg);
        logorreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buyer_Main_Navigation.this , Use_reason_Activity.class);
                startActivity(intent);
            }
        });
    }
}
