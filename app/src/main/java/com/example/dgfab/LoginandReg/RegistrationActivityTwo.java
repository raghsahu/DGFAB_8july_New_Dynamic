package com.example.dgfab.LoginandReg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    EditText countyed ,stateed ,pincodeed,emailed,passworded,firstnameed,lastnameed,companyed,sele_subusered;
    String country,email,password,first_name,last_name,comp_name,buss_type,sele_subser,state,pincode;
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
            sele_subusered.setText(dataregistration.getSub_bus_type());
            Toast.makeText(this, "resumed", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "resumedddd", Toast.LENGTH_SHORT).show();
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
        firstnameed = findViewById(R.id.firstnameedt);
        lastnameed = findViewById(R.id.lastnameedt);
        companyed = findViewById(R.id.companyedt);
        buss_typeed = findViewById(R.id.busCate);
        sele_subusered = findViewById(R.id.busSubCat);
        submmbtn = findViewById(R.id.submmbtn);
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
            sele_subusered.setText(getIntent().getStringExtra("com_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        submmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    country =countyed.getText().toString();
                    state =  stateed.getText().toString();
                    email =  emailed.getText().toString();
                    password =  passworded.getText().toString();
                    first_name =  firstnameed.getText().toString();
                    last_name =  lastnameed.getText().toString();
                    comp_name =  companyed.getText().toString();
                    state =  stateed.getText().toString();
                    buss_type =  buss_typeed.getSelectedItem().toString();
                    state =  stateed.getText().toString();
                    sele_subser =  sele_subusered.getText().toString();
                    pincode =  pincodeed.getText().toString();
                //    email =  emailed.getText().toString();

                    if (country.length() != 0 && email.length() != 0 && password.length() != 0 && first_name.length()
                            != 0 && last_name.length() != 0 && comp_name.length() != 0 && buss_type.length() != 0 &&
                            sele_subser.length() != 0){
                    if(buss_type.equals("Manufacturer")) {
                        RegisteronlyStaff(country, state, email, password, first_name, last_name, comp_name, pincode, "3", sele_subser);
                    }else {
                        Toast.makeText(RegistrationActivityTwo.this, "Working on other please register as manufacturer", Toast.LENGTH_SHORT).show();
                    }
                    }
                }catch (Exception e)
                {
                    Toast.makeText(RegistrationActivityTwo.this, "Please fill everything", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        countyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivityTwo.this, AllCountries.class);
                dataregistration = new Dataregistration(countyed.getText().toString() ,stateed.getText().toString() , emailed.getText().toString() ,firstnameed.getText().toString() ,lastnameed.getText().toString() ,passworded.getText().toString(),pincodeed.getText().toString() ,companyed.getText().toString() , buss_typeed.getSelectedItemId() , sele_subusered.getText().toString());
                startActivity(intent);
             //   onDestroy();
               finish();
            }
        });



        sele_subusered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivityTwo.this, Registration_Step_1.class);
                dataregistration = new Dataregistration(countyed.getText().toString() ,stateed.getText().toString() , emailed.getText().toString() ,firstnameed.getText().toString() ,lastnameed.getText().toString() ,passworded.getText().toString(),pincodeed.getText().toString() ,companyed.getText().toString() , buss_typeed.getSelectedItemId() , sele_subusered.getText().toString());
                startActivity(intent);
                finish();
            }
        });


    }
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        savedInstanceState.putString("countyed", countyed.getText().toString());
//        savedInstanceState.putString("stateed", stateed.getText().toString());
//        savedInstanceState.putString("emailed", emailed.getText().toString());
//        savedInstanceState.putString("passworded", passworded.getText().toString());
//        savedInstanceState.putString("firstnameed", firstnameed.getText().toString());
//        savedInstanceState.putString("lastnameed", lastnameed.getText().toString());
//        savedInstanceState.putString("companyed", companyed.getText().toString());
//        savedInstanceState.putString("pincodeed", pincodeed.getText().toString());
//        savedInstanceState.putString("sele_subusered",sele_subusered.getText().toString());
//        savedInstanceState.putInt("buss_typeed",buss_typeed.getSelectedItemPosition());
//      //  savedInstanceState.putString("MyString", "Welcome back to Android");
//        // etc.
//    }


    @Override
    protected void onDestroy() {
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        Toast.makeText(RegistrationActivityTwo.this, "Restoring", Toast.LENGTH_SHORT).show();
//      countyed.setText(savedInstanceState.getString("countyed"));
//     stateed.setText( savedInstanceState.getString("stateed"));
//      emailed.setText(savedInstanceState.getString("emailed"));
//       passworded.setText(savedInstanceState.getString("passworded"));
//      firstnameed.setText(savedInstanceState.getString("firstnameed"));
//      lastnameed.setText(savedInstanceState.getString("lastnameed"));
//      companyed.setText( savedInstanceState.getString("companyed"));
//      pincodeed.setText(savedInstanceState.getString("pincodeed"));
//        sele_subusered.setText(savedInstanceState.getString("sele_subusered"));
//        buss_typeed.setSelection(savedInstanceState.getInt("buss_typeed"));
////        double myDouble = savedInstanceState.getDouble("myDouble");
////        int myInt = savedInstanceState.getInt("MyInt");
////        String myString = savedInstanceState.getString("MyString");
//    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "restartesd", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "restarted", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    private void RegisteronlyStaff(String country, String state, String email, String password, String first_name, String last_name, String comp_name, String pincode, String buss_type, String sele_subser) {
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
//                Toast.makeText(getActivity(), ""+response.body().getMassage().getId(), Toast.LENGTH_SHORT).show();
                //   SubTypestrings = new String[response.body().getData().size()];
                Log.e("responce is ", "" + response.body().getResponce());
                Log.e("responce message is ", "" + response.body().getMassage());
        //       Toast.makeText(getActivity(), "responce is " + response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
//                if (response.body().getResponce().booleanValue() == true) {
//                    sessionManager.serverEmailLogin(Integer.valueOf(response.body().getMassage().getId()));
                    Intent intent = new Intent(RegistrationActivityTwo.this, Business_Dashboard_Main.class);
                    startActivity(intent);
                    finish();
//                }

            }

            @Override
            public void onFailure(Call<Registration_only> call, Throwable t) {
//                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}