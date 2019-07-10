package com.example.dgfab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        contedt = findViewById(R.id.contedt);
        submmbtn = findViewById(R.id.submmbtn);
        try {
            contedt.setText(getIntent().getStringExtra("mycont"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        submmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisteronlyStaff();
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

    private void RegisteronlyStaff() {
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
//        Call<Registration_only> get_aboutCall = AbloutApi.REGISTRATION_ONLY_CALL(main_type.toString(), name, email, mobilkr, address, companyname, password, mainselect);
//        get_aboutCall.enqueue(new Callback<Registration_only>() {
//            @Override
//            public void onResponse(Call<Registration_only> call, Response<Registration_only> response) {
////                Toast.makeText(getActivity(), ""+response.body().getMassage().getId(), Toast.LENGTH_SHORT).show();
//                //   SubTypestrings = new String[response.body().getData().size()];
//                Log.e("responce is ", "" + response.body().getResponce());
//                Log.e("responce message is ", "" + response.body().getMassage());
//                Toast.makeText(getActivity(), "responce is " + response, Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//                if (response.body().getResponce().booleanValue() == true) {
//                    sessionManager.serverEmailLogin(Integer.valueOf(response.body().getMassage().getId()));
//                    Intent intent = new Intent(getActivity(), DashBoard.class);
//                    startActivity(intent);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Registration_only> call, Throwable t) {
//                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });




    }
}