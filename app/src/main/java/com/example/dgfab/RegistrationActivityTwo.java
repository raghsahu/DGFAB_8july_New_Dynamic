package com.example.dgfab;

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
import com.example.dgfab.AllParsings.Registration_only;

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
    Spinner buss_typeed;
    EditText countyed ,stateed ,pincodeed,emailed,passworded,firstnameed,lastnameed,companyed,sele_subusered;
    String country,email,password,first_name,last_name,comp_name,buss_type,sele_subser,state;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        countyed = findViewById(R.id.contedt);
        stateed = findViewById(R.id.contedt);
        pincodeed = findViewById(R.id.pincode);
        emailed = findViewById(R.id.email);
        passworded = findViewById(R.id.password);
        firstnameed = findViewById(R.id.fulname);
        lastnameed = findViewById(R.id.lasname);
        companyed = findViewById(R.id.comp_name);
        lastnameed = findViewById(R.id.contedt);
        buss_typeed = findViewById(R.id.busCate);
        buss_typeed = findViewById(R.id.busCate);
        sele_subusered = findViewById(R.id.busSubCat);
        try {
            countyed.setText(getIntent().getStringExtra("mycont"));
            country = getIntent().getStringExtra("mycont");
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
                    email =  emailed.getText().toString();
                    email =  emailed.getText().toString();
                    email =  emailed.getText().toString();
                    email =  emailed.getText().toString();

                    if (country.length() != 0 && email.length() != 0 && password.length() != 0 && first_name.length() != 0 && last_name.length() != 0 && comp_name.length() != 0 && buss_type.length() != 0 && sele_subser.length() != 0)
                        RegisteronlyStaff(country, email, password, first_name, first_name, last_name, comp_name, buss_type, sele_subser);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        contedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivityTwo.this, AllCountries.class);
                startActivity(intent);
            }
        });
    }

    private void RegisteronlyStaff(String country, String email, String password, String first_name, String firstName, String last_name, String comp_name, String buss_type, String sele_subser) {
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
        Call<Registration_only> get_aboutCall = AbloutApi.REGISTRATION_ONLY_CALL(country, email, password, first_name, firstName, last_name, password, comp_name,buss_type,sele_subser);
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
//                    Intent intent = new Intent(getActivity(), DashBoard.class);
//                    startActivity(intent);
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