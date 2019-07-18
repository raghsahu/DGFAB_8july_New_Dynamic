package com.example.dgfab.LoginandReg;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.dgfab.AllParsings.Registration_only;
import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.Dataregistration;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.HashMap;
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
    AutoCompleteTextView et_city,countyed ,stateed ;
    public static String Mycountry;
    EditText pincodeed,emailed,passworded,firstnameed,lastnameed,companyed,conf_password;
    String country,email,password,confirm_password,first_name,last_name,comp_name,buss_type,sele_subser,state,pincode;
    private ProgressDialog progressDialog;
    String City;

    ArrayList<String> country_list=new ArrayList<>();
    private ArrayAdapter<String> CountryAdapter;
    HashMap<Integer,Datum_Country>country_hashmap=new HashMap<>();
     String country_id;

    ArrayList<String> state_list=new ArrayList<>();
    private ArrayAdapter<String> StateAdapter;
    HashMap<Integer,All_State_found>state_hashmap=new HashMap<>();
    String state_id;

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
        submmbtn = findViewById(R.id.submmbtn);
        conf_password = findViewById(R.id.conf_password);

        Get_All_Country();


        //******************spinner***designation********************
        countyed.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem=countyed.getAdapter().getItem(position).toString();

                for (int i = 0; i < country_hashmap.size(); i++) {
                    if (country_hashmap.get(i).getName().equals(countyed.getAdapter().getItem(position))){

                        country_id=country_hashmap.get(i).getId();
                         StateExecuteTask(country_hashmap.get(i).getId());
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


        submmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   // country =countyed.getSelectedItem().toString();
                   // state =  stateed.getSelectedItem().toString();
                    email =  emailed.getText().toString();
                   // City =  et_city.getSelectedItem().toString();
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
//*********************************************************************************
    private void StateExecuteTask(String id) {
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

        Call<All_State_found_responce> Get_All_Country_New = AbloutApi.Get_State_Call(id);
        Get_All_Country_New.enqueue(new Callback<All_State_found_responce>() {
            @Override
            public void onResponse(Call<All_State_found_responce> call, Response<All_State_found_responce> response) {
                Log.e("getstate" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_state",""+response.body().getResponce().toString());

                    for (int i=0;i<response.body().getData().size();i++)
                    {
                        Log.e("element" , ""+response.body().getData().get(i).getName());
                        Log.e("element_size" , ""+response.body().getData().size());

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
                    // countyed.showDropDown();
                    // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<All_State_found_responce> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",t.getMessage());
                //Toast.makeText(AllCountries.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(AllCountries.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

//*******************************************************************************************
    private void Get_All_Country() {

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

        Call<All_Country_State> Get_All_Country_New = AbloutApi.Get_Country_Call();
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
                       String country_name=response.body().getData().get(i).getName();

                       country_hashmap.put(i, new Datum_Country(country_id,country_name));

                        Log.e("hashmap_des",""+country_hashmap);

                    }
                    CountryAdapter = new ArrayAdapter<String>(RegistrationActivityTwo.this,
                            android.R.layout.simple_spinner_dropdown_item, country_list);
                   // CountryAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
                    countyed.setThreshold(1);
                    countyed.setAdapter(CountryAdapter);
                   // countyed.showDropDown();
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
}