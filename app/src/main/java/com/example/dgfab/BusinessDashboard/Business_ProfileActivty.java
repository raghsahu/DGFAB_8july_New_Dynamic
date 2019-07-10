package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dgfab.R;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class Business_ProfileActivty extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView add_com ,jetpro;
    Toolbar toolbar_prff;
    private ProgressDialog dialog;
    ImageView header_cover_image;
    CircleImageView prom;
    File Propic,Coverpic;
    int c,p;
    private int RESULT_PICK_IMAGE = 101;
    private int RESULT_LOAD_IMAGE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__profile_activty);

        toolbar_prff = (Toolbar)findViewById(R.id.toolbar_prff);
        toolbar_prff.setNavigationIcon(R.drawable.home);
        header_cover_image = findViewById(R.id.header_cover_image);
        jetpro = findViewById(R.id.jetpro);
        prom = findViewById(R.id.user_profile_photo);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Average"));
        tabLayout.addTab(tabLayout.newTab().setText("Analytics"));
        tabLayout.addTab(tabLayout.newTab().setText("Connections"));
        tabLayout.addTab(tabLayout.newTab().setText("Interest"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
