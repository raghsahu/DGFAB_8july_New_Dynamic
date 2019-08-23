package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.dgfab.BusinessDashboard.Business_CRM.CRM_Fragment;
import com.example.dgfab.BusinessDashboard.Business_HomeDashboard.Home_fragment;
import com.example.dgfab.LoginandReg.ManuLoginActivity;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import de.hdodenhof.circleimageview.CircleImageView;

public class Business_Dashboard_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView nav_imageView;
    public ImageView notion;
    private ActionBar toolbar1;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__drawer);
        sessionManager = new SessionManager(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        notion = findViewById(R.id.notion);
        setSupportActionBar(toolbar);
        toolbar1 = getSupportActionBar();
        new GETMYINFORMATION(new SessionManager(this).getUS()).execute();

        //**************************************
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_business_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        notion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Business_Dashboard_Main.this , MyAllRequests.class);
                startActivity(intent);
            }
        });
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        toolbar.setTitle("DGFAB");
        loadFragment(new Home_fragment());
        //*****************************************************************************

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_business);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.name);
       CircleImageView nav_imageView=(CircleImageView)hView.findViewById(R.id.nav_imageView);
        GETTHEBASEICINFO();
        navigationView.setNavigationItemSelectedListener(this);

        nav_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Business_Dashboard_Main.this, Business_ProfileActivty.class);
            //    Intent intent = new Intent(Business_Dashboard_Main.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GETTHEBASEICINFO() {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar1.setTitle("DGFAB");
                    fragment = new Home_fragment();
                    loadFragment(fragment);
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);

                    return true;
                case R.id.navigation_feeds:
                    //toolbar1.setTitle("My Gifts");
                    return true;
                case R.id.navigation_directory:
                   // toolbar1.setTitle("Cart");
                    return true;
                    case R.id.navigation_my_crm:
                        toolbar1.setTitle("My CRM");
                        fragment = new CRM_Fragment();
                        loadFragment(fragment);
                        overridePendingTransition(R.anim.anim_slide_in_left,
                                R.anim.anim_slide_out_left);
                    return true;
                case R.id.navigation_profile:
                    //toolbar1.setTitle("Profile");
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_business, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business__drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_galleic_cre_staff) {
            Intent intent = new Intent(Business_Dashboard_Main.this , StaffActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_addproduct) {
           // Intent intent = new Intent(Business_Dashboard_Main.this , AddProduct_Activity.class);
            Intent intent = new Intent(Business_Dashboard_Main.this , AddProductWay.class);
            startActivity(intent);


        } else if (id == R.id.nav_tools) {

        }else if (id == R.id.nav_improve) {
                Intent TO_All_Search = new Intent(Business_Dashboard_Main.this , Search_All_Users.class);
                startActivity(TO_All_Search);
                //toolbar1.setTitle("Profile");


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            sessionManager.serverEmailLogin("null" , "200000");
            Intent intent = new Intent(Business_Dashboard_Main.this , ManuLoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GETMYINFORMATION extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        int US;
        public GETMYINFORMATION(int us) {
            this.US =us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(Business_Dashboard_Main.this);
            dialog.setCancelable(true);
            // dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/getusers");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", US);


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000  /*milliseconds*/);
                conn.setConnectTimeout(15000  /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if (result != null) {

                try {
                    Log.e("result at ", "get back" + result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++) {
                        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_business);
                        View hView = navigationView.getHeaderView(0);
                        TextView name = (TextView) hView.findViewById(R.id.nav_name);
                        TextView nav_email = (TextView) hView.findViewById(R.id.nav_email);
                        name.setText(jsonArray.getJSONObject(0).get("name").toString());
                        nav_email.setText(jsonArray.getJSONObject(0).get("email").toString());

                    }
                    try {
//                        Glide.with(Business_Dashboard_Main.this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.prof).error(R.drawable.prof))
//                                .load("https://neareststore.in/uploads/" + jsonObject.getJSONArray("data").getJSONObject(0).getString("image"))
//                                .listener(new RequestListener<Drawable>() {
//                                    @Override
//                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                        // log exception
//                                        Log.e("TAG", "Error loading image", e);
//                                        return false; // important to return false so the error placeholder can be placed
//                                    }
//
//                                    @Override
//                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                        return false;
//                                    }
//                                })
//                                .into(prom);
//                        prom.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(ProfileActivty.this, ProfileActivty.class);
//                                startActivity(intent);
//                            }
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
//                        Glide.with(Business_Dashboard_Main.this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.pbgg).error(R.drawable.pbgg))
//                                .load("https://neareststore.in/uploads/" + jsonObject.getJSONArray("data").getJSONObject(0).getString("cover_image"))
//                                .listener(new RequestListener<Drawable>() {
//                                    @Override
//                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                        // log exception
//                                        Log.e("TAG", "Error loading image", e);
//                                        return false; // important to return false so the error placeholder can be placed
//                                    }
//
//                                    @Override
//                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                        return false;
//                                    }
//                                })
//                                .into(header_cover_image);
//                        prom.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(ProfileActivty.this, ProfileActivty.class);
//                                startActivity(intent);
//                            }
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();
//                    }
                    }

//                nav_user.setText(user);

                    Log.e("PostRegistration", result.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}
