package com.example.dgfab.Activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.LoginandReg.Registration_pro_1;
import com.example.dgfab.R;

public class Use_reason_Activity extends AppCompatActivity {
    TextView allow_reason;
    RadioButton bsrad,perrad;
    CardView businees , personal;
    DrawableRes cirlce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_reason_);
        allow_reason=findViewById(R.id.allow_reason);
        bsrad = findViewById(R.id.bsrad);
        perrad = findViewById(R.id.persord);
        businees=findViewById(R.id.bsneed);
        personal=findViewById(R.id.persona);

        allow_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsrad.isChecked()) {
                    perrad.setChecked(false);
                    Intent in = new Intent(Use_reason_Activity.this, Registration_pro_1.class);
                    startActivity(in);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                }else if(perrad.isChecked()){
                    bsrad.setChecked(false);
                    Intent in = new Intent(Use_reason_Activity.this, LoginActivity.class);
                    startActivity(in);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                }else {
                    Toast.makeText(Use_reason_Activity.this, "Please select anything or select skip", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
