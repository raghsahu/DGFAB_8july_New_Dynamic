package com.example.dgfab.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.R;

public class Use_reason_Activity extends AppCompatActivity {
    TextView allow_reason;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_reason_);

        allow_reason=findViewById(R.id.allow_reason);

        allow_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Use_reason_Activity.this, LoginActivity.class);
                startActivity(in);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);


            }
        });

    }
}
