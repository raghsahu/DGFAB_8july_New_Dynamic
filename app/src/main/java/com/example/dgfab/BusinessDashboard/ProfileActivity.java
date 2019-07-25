package com.example.dgfab.BusinessDashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dgfab.Adapter.ProfileAdapter;
import com.example.dgfab.R;


public class ProfileActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView add_com;
    Toolbar toolbar_prff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar_prff = (Toolbar)findViewById(R.id.toolbar_prff);
        toolbar_prff.setNavigationIcon(R.drawable.home);
        toolbar_prff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   stopActivityTask();
                onBackPressed();
            }
        });

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.pager);
        add_com = findViewById(R.id.add_com);

        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Average"));
        tabLayout.addTab(tabLayout.newTab().setText("Analytics"));
        tabLayout.addTab(tabLayout.newTab().setText("Connections"));
        tabLayout.addTab(tabLayout.newTab().setText("Interest"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        add_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_com.setText("Connected");
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

       /* tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ProfileActivity.this.setTitle("DIGFAB");
        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Average"));
        tabLayout.addTab(tabLayout.newTab().setText("Analytics"));
        tabLayout.addTab(tabLayout.newTab().setText("Connections"));
        tabLayout.addTab(tabLayout.newTab().setText("Intrest"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#353B68"));
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        final ProfileAdapter profileAdapter = new ProfileAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
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
        });*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
