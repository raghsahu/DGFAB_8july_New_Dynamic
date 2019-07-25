package com.example.dgfab.LoginandReg;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dgfab.Activity.Buyer_guest_login.LoginActivity;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.R;

public class Registration_pro_1 extends AppCompatActivity {
    Button btnnext,btnlog;
    EditText fulname , lasname,comp_name,citynae,pincode;
    Spinner type_spin,type_country;
    public String fulnamestr, lasnamestr, citynaestr,comp_namestr, picodestr, countystr ,typespinstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_pro_1);

        btnnext = findViewById(R.id.btnnext);
        btnlog = findViewById(R.id.btnlog);
        type_spin = findViewById(R.id.type_spin);
        fulname = findViewById(R.id.fulname);
        lasname = findViewById(R.id.lasname);
        comp_name = findViewById(R.id.comp_name);
        citynae = findViewById(R.id.citynae);
        pincode = findViewById(R.id.pincode);
        type_country = findViewById(R.id.type_country);

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
                if(fulname.getText().toString().length() !=0 && lasname.getText().toString().length() !=0 &&
                comp_name.getText().toString().length() !=0 && citynae.getText().toString().length() !=0 && pincode.getText().toString().length() !=0 &&
                        type_spin.getSelectedItem().equals("Select Business Type") && type_country.getSelectedItem().equals("Select Your Country"))
                {
                    fulnamestr = fulname.getText().toString();
                    lasnamestr = lasname.getText().toString();
                    comp_namestr = comp_name.getText().toString();
                    citynaestr = citynae.getText().toString();
                    countystr = type_country.getSelectedItem().toString();
                    picodestr = pincode.getText().toString();
                    typespinstr = type_spin.getSelectedItem().toString();

//                    intent.putExtra("fulname" , fulname.getText().toString());
//                    intent.putExtra("fulname" , lasname.getText().toString());
//                    intent.putExtra("fulname" , comp_name.getText().toString());
//                    intent.putExtra("fulname" , citynae.getText().toString());
//                    intent.putExtra("fulname" , pincode.getText().toString());
                    startActivity(intent);

                }else {
                        if(fulname.getText().toString().length() ==0)
                        {
                            fulname.setError("Full name can not be empty");
                        }
                        if(pincode.getText().toString().length() ==0)
                        {
                            pincode.setError("Full name can not be empty");
                        }
                        if(citynae.getText().toString().length() ==0)
                        {
                            citynae.setError("Full name can not be empty");
                        }
                        if(comp_name.getText().toString().length() ==0)
                        {
                            comp_name.setError("Full name can not be empty");
                        }
                        if(type_country.getSelectedItem().equals("Select Business Type"))
                        {
                            type_country.setBackgroundColor(Color.RED);
                        }
                        if(type_spin.getSelectedItem().equals("Select Your Country"))
                        {
                            type_spin.setBackgroundColor(Color.RED);
                        }



                }

            }
        });
    }
}
