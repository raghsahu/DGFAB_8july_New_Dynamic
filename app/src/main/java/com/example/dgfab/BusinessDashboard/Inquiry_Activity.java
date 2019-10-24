package com.example.dgfab.BusinessDashboard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.dgfab.R;

public class Inquiry_Activity extends AppCompatActivity {
    String maname;
    EditText manametx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_);
        manametx = findViewById(R.id.maname);
        maname = getIntent().getStringExtra("maname");
    }
}
