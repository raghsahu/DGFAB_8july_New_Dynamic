package com.example.dgfab.Connections;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Adapter.ProfileAdapter;
import com.example.dgfab.R;


public class SeenProfile extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView add_com,dealname;
    TextView create_order;
    ImageView heart1,heart2;
    ImageView share;

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
        create_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    if(whatsname.equals("Accountant")) {
//                        Intent intent = new Intent(SeenProfile.this, DirCateActivity.class);
//                        startActivity(intent);
//                        whatsname ="";
//                    }
//                }catch (Exception e)
//                {
//                    Intent intent = new Intent(SeenProfile.this, CreateOrder.class);
//
//                    startActivity(intent);
//                    e.printStackTrace();
//                }
            }
        });
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
        final ProfileAdapter profileAdapter = new ProfileAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(profileAdapter);

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
}
