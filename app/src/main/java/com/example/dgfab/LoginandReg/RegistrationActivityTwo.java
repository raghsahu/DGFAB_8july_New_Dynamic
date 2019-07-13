package com.example.dgfab.LoginandReg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Activity.AllCountries;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.AllParsings.Registration_only;
import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.Dataregistration;
import com.example.dgfab.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivityTwo extends AppCompatActivity {
    EditText contedt;
    Button submmbtn;
   public static Dataregistration dataregistration;
    Spinner buss_typeed;
    public static String Mycountry;
    EditText countyed ,stateed ,pincodeed,emailed,passworded,firstnameed,lastnameed,companyed,conf_password;
    String country,email,password,confirm_password,first_name,last_name,comp_name,buss_type,sele_subser,state,pincode;
    private ProgressDialog progressDialog;

    @Override
    protected void onPostResume() {
        try {
            countyed.setText(dataregistration.getCountry());
            stateed.setText(dataregistration.getState());
            emailed.setText(dataregistration.getEmail());
            passworded.setText(dataregistration.getPassword());
            firstnameed.setText(dataregistration.getFirstname());
            lastnameed.setText(dataregistration.getLastname());
            companyed.setText(dataregistration.getComp_name());
            pincodeed.setText(dataregistration.getPincode());
            buss_typeed.setSelection(Integer.valueOf(String.valueOf(dataregistration.getBuss_type())));
            //sele_subusered.setText(dataregistration.getSub_bus_type());
          //  Toast.makeText(this, "resumed", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        //Toast.makeText(this, "resumedddd", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);

        countyed = findViewById(R.id.countryedt);
        stateed = findViewById(R.id.stateedt);
        pincodeed = findViewById(R.id.pinedt);
        emailed = findViewById(R.id.emailedt);
        passworded = findViewById(R.id.passwordedt);
        conf_password = findViewById(R.id.conf_password);
        firstnameed = findViewById(R.id.firstnameedt);
        lastnameed = findViewById(R.id.lastnameedt);
        companyed = findViewById(R.id.companyedt);
        buss_typeed = findViewById(R.id.busCate);
       // sele_subusered = findViewById(R.id.busSubCat);
        submmbtn = findViewById(R.id.submmbtn);
        conf_password = findViewById(R.id.conf_password);
        try{
            if(!Mycountry.isEmpty())
            {
                countyed.setText(Mycountry);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            countyed.setText(getIntent().getStringExtra("mycountry"));
            country = getIntent().getStringExtra("mycountry");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           // sele_subusered.setText(getIntent().getStringExtra("com_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        //*********************************************************************
        //***************************************************
        passworded.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (passworded.getRight() - passworded.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here


                        if (passworded.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
                            passworded.setTransformationMethod(new SingleLineTransformationMethod());
                            passworded.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.toogle_off, 0);
                        }
                        else {
                            passworded.setTransformationMethod(new PasswordTransformationMethod());
                            passworded.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.toogle, 0);
                        }

                        passworded.setSelection(passworded.getText().length());

                        return true;
                    }
                }
                return false;
            }
        });

//***********************************************************
        //***************************************************
        conf_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (conf_password.getRight() - conf_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here


                        if (conf_password.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
                            conf_password.setTransformationMethod(new SingleLineTransformationMethod());
                            conf_password.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.toogle_off, 0);
                        }
                        else {
                            conf_password.setTransformationMethod(new PasswordTransformationMethod());
                            conf_password.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.toogle, 0);
                        }

                        conf_password.setSelection(conf_password.getText().length());

                        return true;
                    }
                }
                return false;
            }
        });

//***********************************************************



        //************************************************************************************


        submmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    country =countyed.getText().toString();
                    state =  stateed.getText().toString();
                    email =  emailed.getText().toString();
                    password =  passworded.getText().toString();
                    confirm_password =  conf_password.getText().toString();
                    first_name =  firstnameed.getText().toString();
                    last_name =  lastnameed.getText().toString();
                    comp_name =  companyed.getText().toString();
                    buss_type =  buss_typeed.getSelectedItem().toString();
                    state =  stateed.getText().toString();
                  //  sele_subser =  sele_subusered.getText().toString();
                    pincode =  pincodeed.getText().toString();

                    Toast.makeText(RegistrationActivityTwo.this, "type"+buss_type, Toast.LENGTH_SHORT).show();

                    if (country.length() != 0 && email.length() != 0 && password.length() != 0 && confirm_password.length() != 0 && first_name.length()
                            != 0 && last_name.length() != 0 && comp_name.length() != 0 && buss_type.length() != 0)
                    {
                        if (confirm_password.equals(password))
                        {
                        if(buss_type.equals("Manufacturer")) {

                            Intent intent = new Intent(RegistrationActivityTwo.this, Registration_Step_1.class);
                            intent.putExtra("country",country);
                            intent.putExtra("state",state);
                            intent.putExtra("email",email);
                            intent.putExtra("password",password);
                            intent.putExtra("first_name",first_name);
                            intent.putExtra("last_name",last_name);
                            intent.putExtra("comp_name",comp_name);
                            intent.putExtra("buss_type",buss_type);
                            intent.putExtra("pincode",pincode);

                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_left,
                                    R.anim.anim_slide_out_left);
                            finish();

                            //RegisteronlyStaff(country, state, email, password, first_name, last_name, comp_name,
                            // pincode, "3", sele_subser);
                        }else {
                            Toast.makeText(RegistrationActivityTwo.this, "please register as manufacturer", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(RegistrationActivityTwo.this, "Password not match", Toast.LENGTH_SHORT).show();
                    }

                    }else {
                        Toast.makeText(RegistrationActivityTwo.this, "Please Enter All Field", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(RegistrationActivityTwo.this, "Please fill everything", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        //*****************************************************************************

        countyed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {

                    Intent intent = new Intent(RegistrationActivityTwo.this, AllCountries.class);
                    dataregistration = new Dataregistration(countyed.getText().toString() ,stateed.getText().toString() ,
                            emailed.getText().toString() ,firstnameed.getText().toString() ,lastnameed.getText().toString()
                            ,passworded.getText().toString(),pincodeed.getText().toString() ,companyed.getText().toString() ,
                            buss_typeed.getSelectedItemId());
                    //sele_subusered.getText().toString()

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                    //   onDestroy();
                    finish();

                }

                return true; // return is important...
            }
        });

//*******************************************************************************************
//        sele_subusered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegistrationActivityTwo.this, Registration_Step_1.class);
//                dataregistration = new Dataregistration(countyed.getText().toString() ,stateed.getText().toString() , emailed.getText().toString() ,firstnameed.getText().toString() ,lastnameed.getText().toString() ,passworded.getText().toString(),pincodeed.getText().toString() ,companyed.getText().toString() , buss_typeed.getSelectedItemId() , sele_subusered.getText().toString());
//                startActivity(intent);
//                overridePendingTransition(R.anim.anim_slide_in_left,
//                        R.anim.anim_slide_out_left);
//                finish();
//            }
//        });


    }



    @Override
    protected void onDestroy() {
       // Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
       // Toast.makeText(this, "restartesd", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onStart() {
       // Toast.makeText(this, "restarted", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    private void RegisteronlyStaff(String country, String state, String email, String password, String first_name,
                                   String last_name, String comp_name, String pincode, String buss_type, String sele_subser) {
        progressDialog = new ProgressDialog(RegistrationActivityTwo.this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Registering Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<Registration_only> get_aboutCall = AbloutApi.REGISTRATION_ONLY_CALL(country,state ,email,password,first_name,last_name,comp_name,pincode,buss_type,sele_subser);
        get_aboutCall.enqueue(new Callback<Registration_only>() {
            @Override
            public void onResponse(Call<Registration_only> call, Response<Registration_only> response) {
                Log.e("responce is ", "" + response.body().getResponce());
                Log.e("responce message is ", "" + response.body().getMassage());
                progressDialog.dismiss();
                       Toast.makeText(RegistrationActivityTwo.this, "Successful", Toast.LENGTH_SHORT).show();
//                if (response.body().getResponce().booleanValue() == true) {
//                    sessionManager.serverEmailLogin(Integer.valueOf(response.body().getMassage().getId()));
                    Intent intent = new Intent(RegistrationActivityTwo.this, Business_Dashboard_Main.class);
                    startActivity(intent);
                     overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                    finish();
//                }

            }

            @Override
            public void onFailure(Call<Registration_only> call, Throwable t) {
                Log.e("Failer",""+t.getMessage());
//                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}