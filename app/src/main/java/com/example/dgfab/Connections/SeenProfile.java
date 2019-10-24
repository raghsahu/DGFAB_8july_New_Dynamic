package com.example.dgfab.Connections;

import android.app.ProgressDialog;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.MyInfo;
import com.example.dgfab.Dapter_others.SeenProfileAdapter;
import com.example.dgfab.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SeenProfile extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView add_com,dealname;
    TextView create_order;
    ImageView heart1,heart2;
    ImageView share;
   static public String Theirid;
    String whatsname,Connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen_profile);
        dealname = findViewById(R.id.dealname);
        //heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        share = findViewById(R.id.share);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.pager);
        create_order=(TextView) findViewById(R.id.create_order);
        whatsname = getIntent().getStringExtra("whatsname");
        Connected = getIntent().getStringExtra("Connected");
        Theirid = getIntent().getStringExtra("theirid");
            GETTHEIRINFo(Theirid);
//        create_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                try {
////                    if(whatsname.equals("Accountant")) {
////                        Intent intent = new Intent(SeenProfile.this, DirCateActivity.class);
////                        startActivity(intent);
////                        whatsname ="";
////                    }
////                }catch (Exception e)
////                {
////                    Intent intent = new Intent(SeenProfile.this, CreateOrder.class);
////
////                    startActivity(intent);
////                    e.printStackTrace();
////                }
//            }
//        });
//
//        heart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              heart1.setVisibility(View.GONE);
//              heart2.setVisibility(View.VISIBLE);
//            }
//        });

        heart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart2.setVisibility(View.GONE);
                heart1.setVisibility(View.VISIBLE);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

//        create_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(create_order.getText().toString().equals("Create Sales Order"))
//                {
//                    Toast.makeText(SeenProfile.this, "Create Sales Order", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SeenProfile.this , Creat_sales_accountant.class);
//                    startActivity(intent);
//                }else {
//                    Intent intent = new Intent(SeenProfile.this,DirCateActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(SeenProfile.this, "not Sales Order", Toast.LENGTH_SHORT).show();
//                }
//
//
////                if ()************accountant login******************************
////                Intent intent = new Intent(SeenProfile.this,Creat_sales_accountant.class);
////                startActivity(intent);
//            }
//        });
        try {
            if(whatsname.equals("Accountant"))
            {
                create_order.setText("Create Sales Order");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        add_com = findViewById(R.id.add_com);
        add_com.setText(Connected);
        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Average"));
        tabLayout.addTab(tabLayout.newTab().setText("Analytics"));
        tabLayout.addTab(tabLayout.newTab().setText("Connections"));
        tabLayout.addTab(tabLayout.newTab().setText("Interest"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            dealname.setText(getIntent().getStringExtra("dealname"));

        add_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_com.getText().equals("Request Sent"))
                {
                    add_com.setText("Connect Now");
                }else {
                    add_com.setText("Request Sent");
                }

            }
        });
        final SeenProfileAdapter seenProfileAdapter = new SeenProfileAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(seenProfileAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void GETTHEIRINFo(String theirid) {

        ProgressDialog progressDialog = new ProgressDialog(SeenProfile.this);
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("sortname is" , ""+theirid);
        Call<MyInfo> Get_All_Country_New = AbloutApi.MY_INFO_CALL(String.valueOf(theirid));
        Get_All_Country_New.enqueue(new Callback<MyInfo>() {
            @Override
            public void onResponse(Call<MyInfo> call, Response<MyInfo> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        dealname.setText(response.body().getData().getName());
//                        mmobile.setText(response.body().getData().getMobile());
//                        madd.setText(response.body().getData().getAddress());
//                        mcomp.setText(response.body().getData().getCompanyName());
//                        mcomab.setText(response.body().getData().getBrandName());
//
//                        user_idString = response.body().getData().getId();
//                        nameString  =response.body().getData().getName();
//                        lastnameString = response.body().getData().getLastname();
//                        mobileString = response.body().getData().getMobile();
//                        emailString = response.body().getData().getEmail();
//                        countryString = response.body().getData().getCountry();
//                        stateString = response.body().getData().getState();
//                        cityString = response.body().getData().getCity();
//                        pinString = response.body().getData().getPin();
//                        savemouthString = response.body().getData().getEmail();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MyInfo> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(SeenProfile.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SeenProfile.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
