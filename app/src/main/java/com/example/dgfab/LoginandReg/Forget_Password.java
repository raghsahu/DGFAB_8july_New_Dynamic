package com.example.dgfab.LoginandReg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dgfab.Activity.Buyer_guest_login.EmailOtpActivity;
import com.example.dgfab.Activity.Buyer_guest_login.Forget_Mobile;
import com.example.dgfab.R;

import org.w3c.dom.Text;

public class Forget_Password extends AppCompatActivity {

    
    EditText et_email;
    TextView tv_submit;
    String Et_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        
        et_email=findViewById(R.id.txtemail);
        tv_submit=findViewById(R.id.tv_email_code);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Et_Email=et_email.getText().toString();
                GetPassword(Et_Email);
                
//                Intent in = new Intent(Forget_Password.this, EmailOtpActivity.class);
//                startActivity(in);
//                overridePendingTransition(R.anim.anim_slide_in_left,
//                        R.anim.anim_slide_out_left);


            }
        });
        
    }

    private void GetPassword(String et_Email) {




    }
}
