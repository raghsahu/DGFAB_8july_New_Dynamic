package com.example.dgfab.LoginandReg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.AllParsings.All_Country_State;
import com.example.dgfab.AllParsings.All_State_found;
import com.example.dgfab.AllParsings.All_State_found_responce;
import com.example.dgfab.AllParsings.Datum_Country;
import com.example.dgfab.AllParsings.Get_Cities;
import com.example.dgfab.AllParsings.Get_Cities_Data;
import com.example.dgfab.AllParsings.Registration_only;
import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.Dataregistration;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.Shared_Preference;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivityTwo extends AppCompatActivity {
    EditText contedt;
    Button nextbtn;
   public static Dataregistration dataregistration;
    Spinner buss_typeed;
    AutoCompleteTextView et_city,countyed ,stateed ;
    public static String Mycountry;
    EditText pincodeed,emailed,passworded,firstnameed,lastnameed,companyed,conf_password;
    String country,email,password,confirm_password,first_name,last_name,comp_name,buss_type,sele_subser,state,pincode;
    private ProgressDialog progressDialog;
    String City;

    ArrayList<String> country_list=new ArrayList<>();
    private ArrayAdapter<String> CountryAdapter;
    HashMap<Integer,Datum_Country>country_hashmap=new HashMap<>();
     String country_id,city_name;

    ArrayList<String> state_list=new ArrayList<>();
    ArrayList<String> city_list=new ArrayList<>();
    private ArrayAdapter<String> StateAdapter;
    private ArrayAdapter<String> CityAdapter;
    HashMap<Integer,All_State_found>state_hashmap=new HashMap<>();
    HashMap<Integer, Get_Cities_Data> getCitiesHashMap  =new HashMap<>();


    @Override
    protected void onPostResume() {
        try {
           // countyed.setText(dataregistration.getCountry());
           // stateed.setText(dataregistration.getState());
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
        et_city = findViewById(R.id.et_city);
        pincodeed = findViewById(R.id.pinedt);
        emailed = findViewById(R.id.emailedt);
        passworded = findViewById(R.id.passwordedt);
        conf_password = findViewById(R.id.conf_password);
        firstnameed = findViewById(R.id.firstnameedt);
        lastnameed = findViewById(R.id.lastnameedt);
        companyed = findViewById(R.id.companyedt);
        buss_typeed = findViewById(R.id.busCate);
       // sele_subusered = findViewById(R.id.busSubCat);
        nextbtn = findViewById(R.id.nextbtn);
        conf_password = findViewById(R.id.conf_password);

        Get_All_Country("");
        et_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(RegistrationActivityTwo.this, "Text is", Toast.LENGTH_SHORT).show();
//                try {
//                    city_list.clear();
//                    //  stateed.setAdapter(null);
//                    //   StateAdapter.notifyDataSetChanged();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
                for(int i=0;i<state_hashmap.size();i++) {

                    //                  Log.d("getCitiesHashMap", "" + state_hashmap.get(i).getName());
//                    Log.d("city_list", "" + city_list.get(i));
                    String s1 = s.toString();
                    String s2 = state_hashmap.get(i).getName();
                    String s3 = stateed.getText().toString();
              //      Log.d("hey state list" , ""+state_list.get(position).toString());
                    Log.d("hey state hashmap list" , ""+state_hashmap.get(i).getName());
                    if (state_hashmap.get(i).getName().equals(s3)) {

                        //   city_name =
                        //     Toast.makeText(RegistrationActivityTwo.this, "You selected "+state_hashmap.get(position).getName(), Toast.LENGTH_LONG).show();
                        if(city_list.size() <=0) {
                            GETALLCITIES(state_hashmap.get(i).getId(), s1);
                        }else {
                           // Toast.makeText(RegistrationActivityTwo.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        stateed.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                                Toast.makeText(RegistrationActivityTwo.this, "selected"+stateed.getText().toString(), Toast.LENGTH_SHORT).show();
//                try {
//                    city_list.clear();
//                    //  stateed.setAdapter(null);
//                    //   StateAdapter.notifyDataSetChanged();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                for(int i=0;i<state_hashmap.size();i++) {
//
//  //                  Log.d("getCitiesHashMap", "" + state_hashmap.get(i).getName());
////                    Log.d("city_list", "" + city_list.get(i));
//                    String s1 = stateed.getText().toString();
//                    String s2 = state_hashmap.get(i).getName();
//                    Log.d("hey state list" , ""+state_list.get(position).toString());
//                    Log.d("hey state hashmap list" , ""+state_hashmap.get(i).getName());
//                    if (state_hashmap.get(i).getName().equals(s1)) {
//
//                        //   city_name =
//                        //     Toast.makeText(RegistrationActivityTwo.this, "You selected "+state_hashmap.get(position).getName(), Toast.LENGTH_LONG).show();
//                        GETALLCITIES(state_hashmap.get(i).getId(),state_list.get(i).toString());
//                    }
//                }
            }
        });
//        stateed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        stateed.setonA(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(RegistrationActivityTwo.this, "selected"+stateed.getText().toString(), Toast.LENGTH_SHORT).show();
////                try {
////                    city_list.clear();
////                    //  stateed.setAdapter(null);
////                    //   StateAdapter.notifyDataSetChanged();
////                }catch (Exception e)
////                {
////                    e.printStackTrace();
////                }
////                for(int i=0;i<state_hashmap.size();i++)
////                {
////
////                    Log.d("getCitiesHashMap" , ""+state_hashmap.get(i).getName());
////                    Log.d("city_list" , ""+city_list.get(i));
////                    if(state_hashmap.get(i).getName().equals(state_list.get(i).toString()))
////                    {
////
////                        //   city_name =
////                        //     Toast.makeText(RegistrationActivityTwo.this, "You selected "+state_hashmap.get(position).getName(), Toast.LENGTH_LONG).show();
////                        GETALLCITIES(state_hashmap.get(i).getId() , state_list.get(i));
////                    }
////                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(RegistrationActivityTwo.this, "Nothing selected", Toast.LENGTH_SHORT).show();
//            }
//        });
        stateed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                try {
//                    state_list.clear();
//                    //  stateed.setAdapter(null);
//                    //   StateAdapter.notifyDataSetChanged();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
               //     state_list.clear();
                    //  stateed.setAdapter(null);
                    //   StateAdapter.notifyDataSetChanged();
                    if(state_list.size() <=0) {
                        StateExecuteTask(country_id, s.toString());
                    }else {
                        Toast.makeText(RegistrationActivityTwo.this, "done", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//                    city_list.clear();
//                    //  stateed.setAdapter(null);
//                    //   StateAdapter.notifyDataSetChanged();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                for(int i=0;i<state_hashmap.size();i++)
//                {
//
//                  //  Log.d("getCitiesHashMap" , ""+state_hashmap.get(i).getName());
////                    Log.d("city_list" , ""+city_list.get(i));
//                    if(state_hashmap.get(i).getName().equals(state_list.get(i).toString()))
//                    {
//
//                        //   city_name =
//                        //     Toast.makeText(RegistrationActivityTwo.this, "You selected "+state_hashmap.get(position).getName(), Toast.LENGTH_LONG).show();
//                        GETALLCITIES(state_hashmap.get(i).getId() , state_list.get(i));
//                    }
//                }
            }
        });

        //******************spinner***designation********************
        countyed.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem=countyed.getAdapter().getItem(position).toString();

                for (int i = 0; i < country_hashmap.size(); i++) {
                    if (country_hashmap.get(i).getName().equals(countyed.getAdapter().getItem(position))){

                        country_id=country_hashmap.get(i).getId();
                         //StateExecuteTask(country_hashmap.get(i).getId() ,country_hashmap.get(i).getName());
                        //new GETALLSTATE(country_hashmap.get(i).getId() ,country_hashmap.get(i).getSortname()).execute();
                         i=country_hashmap.size();
                    }
                    //Toast.makeText(RegistrationActivityTwo.this,country_id, Toast.LENGTH_SHORT).show();
                }
                Log.e("autotext_id",country_id);
            }
        });




        //************************************************************************************

        try{
            if(!Mycountry.isEmpty())
            {
                //countyed.setText(Mycountry);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            //countyed.setText(getIntent().getStringExtra("mycountry"));
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


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    country =countyed.getText().toString();
                    state =  stateed.getText().toString();
                    email =  emailed.getText().toString();
                    City =  et_city.getText().toString();
                    password =  passworded.getText().toString();
                    confirm_password =  conf_password.getText().toString();
                    first_name =  firstnameed.getText().toString();
                    last_name =  lastnameed.getText().toString();
                    comp_name =  companyed.getText().toString();
                    buss_type =  buss_typeed.getSelectedItem().toString();
                  //  sele_subser =  sele_subusered.getText().toString();
                    pincode =  pincodeed.getText().toString();

                    Toast.makeText(RegistrationActivityTwo.this, "type"+buss_type, Toast.LENGTH_SHORT).show();

                    if (country.length() != 0 && email.length() != 0 && password.length() != 0 && confirm_password.length() != 0 && first_name.length()
                            != 0 && last_name.length() != 0 && comp_name.length() != 0 && buss_type.length() != 0 && City.length() !=0 && state.length() !=0 )
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
                            intent.putExtra("city",City);

                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_left,
                                    R.anim.anim_slide_out_left);
                            finish();

                            //RegisteronlyStaff(country, state, email, password, first_name, last_name, comp_name,
                            // pincode, "3", sele_subser,City);
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

//        countyed.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(MotionEvent.ACTION_UP == event.getAction()) {
//
//                    Intent intent = new Intent(RegistrationActivityTwo.this, AllCountries.class);
//                    dataregistration = new Dataregistration(countyed.getSelectedItem().toString() ,stateed.getSelectedItem().toString() ,
//                            emailed.getText().toString() ,firstnameed.getText().toString() ,lastnameed.getText().toString()
//                            ,passworded.getText().toString(),pincodeed.getText().toString() ,companyed.getText().toString() ,
//                            buss_typeed.getSelectedItemId());
//                    //sele_subusered.getText().toString()
//
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.anim_slide_in_left,
//                            R.anim.anim_slide_out_left);
//                    //   onDestroy();
//                    finish();
//
//                }
//
//                return true; // return is important...
//            }
//        });

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

    private void GETALLCITIES(String id, String sortname) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("id is" , id);
        Log.d("sortname is" , sortname);
        Call<Get_Cities> Get_All_Country_New = AbloutApi.GET_CITIES_CALL(id,sortname);
        Get_All_Country_New.enqueue(new Callback<Get_Cities>() {
            @Override
            public void onResponse(Call<Get_Cities> call, Response<Get_Cities> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce().toString());
                try {
                    if(response.body().getData().size() >0) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            //   state_hashmap.put(i, new All_State_found(state_id,state_name));
                            city_list.add(response.body().getData().get(i).getName());
                            Log.e("hashmap_city", "" + country_hashmap);
                            getCitiesHashMap.put(i, new Get_Cities_Data(response.body().getData().get(i).getStateId(), response.body().getData().get(i).getName()));
                        }
                        CityAdapter = new ArrayAdapter<String>(RegistrationActivityTwo.this,
                                android.R.layout.simple_spinner_dropdown_item, city_list);
                        // CountryAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
                        et_city.setThreshold(1);
                        et_city.setAdapter(CityAdapter);
                        et_city.showDropDown();
                    }
                    // countyed.showDropDown();
                    // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Get_Cities> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",t.getMessage());
                Toast.makeText(RegistrationActivityTwo.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistrationActivityTwo.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //*********************************************************************************
    private void StateExecuteTask(String id, String sortname) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();

        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("id is" , id);
        Log.d("sortname is" , sortname);
        Call<All_State_found_responce> Get_All_Country_New = AbloutApi.Get_State_Call(id,sortname);
        Get_All_Country_New.enqueue(new Callback<All_State_found_responce>() {
            @Override
            public void onResponse(Call<All_State_found_responce> call, Response<All_State_found_responce> response) {
                Log.e("getstate" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_state",""+response.body().getResponce().toString());

                    for (int i=0;i<response.body().getData().size();i++)
                    {
                        Log.e("state element" , ""+response.body().getData().get(i).getName());
                        Log.e("state element id" , ""+response.body().getData().get(i).getId());
                        Log.e("state element_size" , ""+response.body().getData().size());

                        state_list.add(response.body().getData().get(i).getName());

                        String state_id=response.body().getData().get(i).getId();
                        String state_name=response.body().getData().get(i).getName();

                        state_hashmap.put(i, new All_State_found(state_id,state_name));

                        Log.e("hashmap_des",""+country_hashmap);

                    }
                    StateAdapter = new ArrayAdapter<String>(RegistrationActivityTwo.this,
                            android.R.layout.simple_spinner_dropdown_item, state_list);
                    // CountryAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
                    stateed.setThreshold(1);
                    stateed.setAdapter(StateAdapter);
                    stateed.showDropDown();
                    // countyed.showDropDown();
                    // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<All_State_found_responce> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(RegistrationActivityTwo.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistrationActivityTwo.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

//*******************************************************************************************
    private void Get_All_Country(String s) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       // progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);

        Call<All_Country_State> Get_All_Country_New = AbloutApi.Get_Country_Call(s);
        Get_All_Country_New.enqueue(new Callback<All_Country_State>() {
            @Override
            public void onResponse(Call<All_Country_State> call, Response<All_Country_State> response) {
                 Log.e("getcountry" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_country",""+response.body().getResponce().toString());

                    for (int i=0;i<response.body().getData().size();i++)
                    {
                        Log.e("element" , ""+response.body().getData().get(i).getName());
                        Log.e("element_size" , ""+response.body().getData().size());

                        country_list.add(response.body().getData().get(i).getName());

                        String country_id=response.body().getData().get(i).getId();
                       String country_name=response.body().getData().get(i).getSortname();
                       String fullname=response.body().getData().get(i).getName();

                       country_hashmap.put(i, new Datum_Country(country_id,country_name,fullname));

                        Log.e("hashmap_des",""+country_hashmap);

                    }
                    CountryAdapter = new ArrayAdapter<String>(RegistrationActivityTwo.this,
                            android.R.layout.simple_spinner_dropdown_item, country_list);
                   // CountryAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
                    countyed.setThreshold(1);
                    countyed.setAdapter(CountryAdapter);
                    countyed.showDropDown();
                   // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<All_Country_State> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",t.getMessage());
                //Toast.makeText(AllCountries.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(AllCountries.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

//***************************************************************************
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
                                   String last_name, String comp_name, String pincode, String buss_type, String sele_subser,String city) {
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
        Call<Registration_only> get_aboutCall = AbloutApi.REGISTRATION_ONLY_CALL(country,state ,email,password,first_name,
                last_name,comp_name,pincode,buss_type,sele_subser,city);
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

    private class GETALLSTATE extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String id ,sortname;

        public GETALLSTATE(String id, String sortname) {
            this.id = id;
            this.sortname = sortname;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(RegistrationActivityTwo.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/getstate");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("country_id", id);
                postDataParams.put("name", sortname);


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
            if (result != null) {
                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

//                    jsonObject = new JSONObject(result);
//                    Boolean response = jsonObject.getBoolean("responce");
//                    if(response.booleanValue() ==false)
//                    {
//                        Toast.makeText(RegistrationActivityTwo.this, "Login failed,Contact to admin", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(RegistrationActivityTwo.this, "Successful", Toast.LENGTH_SHORT).show();
//                      //  sessionManager.serverEmailLogin(jsonObject.getJSONObject("data").getInt("id"));
//                      //  User_ID = jsonObject.getJSONObject("data").getString("id");
//                    //    Shared_Preference.setId(RegistrationActivityTwo.this,User_ID);
//
//                        Intent intent = new Intent(RegistrationActivityTwo.this , Business_Dashboard_Main.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.anim_slide_in_left,
//                                R.anim.anim_slide_out_left);
//                    }
                }catch (Exception e)
                {
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