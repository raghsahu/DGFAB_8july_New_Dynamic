package com.example.dgfab.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.Service_Adapter;
import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.GET_Services;
import com.example.dgfab.AllParsings.GET_Services_Data;
import com.example.dgfab.AllParsings.Registration_only;
import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.R;
import com.example.dgfab.LoginandReg.RegistrationActivityTwo;
import com.example.dgfab.SessionManage.SessionManager;
import com.example.dgfab.SessionManage.Shared_Preference;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dgfab.Adapter.Service_Adapter.Servicenames;
import static com.example.dgfab.Adapter.Service_Adapter.Service_names;
import static com.example.dgfab.LoginandReg.RegistrationActivityTwo.dataregistration;

public class Registration_Step_1 extends AppCompatActivity {
    RecyclerView serv_id;
    Service_Adapter service_adapter;
    TextView next, addmore_service;
    private ProgressDialog progressDialog;
    String ConcatService;
    String ConcatService_name;
  // public String fulname, email, com_name, password, address, mobile;
    ArrayList<GET_Services_Data> get_services_data = new ArrayList<>();
    GridLayoutManager gridLayoutManager;

    String country,email,password,first_name,last_name,comp_name,buss_type,state,pincode;
     SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        sessionManager=new SessionManager(Registration_Step_1.this);
        serv_id = findViewById(R.id.serv_id);
        serv_id = findViewById(R.id.serv_id);
        addmore_service = findViewById(R.id.addmore_service);

        next = findViewById(R.id.next_back_reg);
        addmore_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Registration_Step_1.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_service_layout);

                EditText et_add_service = (EditText) dialog.findViewById(R.id.text_dialog);
                String add_new_service=et_add_service.getText().toString();

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        Log.e("et_add_ser",""+et_add_service.getText().toString());
                        Add_New_Service(et_add_service.getText().toString());

                    }
                });

                dialog.show();
            }
        });

        //******************************************************************
        try {
            country=getIntent().getStringExtra("country");
            state=getIntent().getStringExtra("state");
            email=getIntent().getStringExtra("email");
            password=getIntent().getStringExtra("password");
            first_name=getIntent().getStringExtra("first_name");
            last_name=getIntent().getStringExtra("last_name");
            comp_name=getIntent().getStringExtra("comp_name");
            buss_type=getIntent().getStringExtra("buss_type");
            pincode=getIntent().getStringExtra("pincode");
        }catch (Exception e){
            Log.e("No Data","Inent null data");
        }


//*****************************************************************************
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int k=0 ; k<Servicenames.size();k++) {
                    if(k==0) {
                      // ConcatService= ConcatService.concat(Servicenames.get(k));
                       ConcatService= Servicenames.get(k);
                    }else {
                       ConcatService = ConcatService.concat(","+Servicenames.get(k));
                    }
                    }
                //******************************************************************
                for(int k=0 ; k<Service_names.size();k++) {
                    if(k==0) {
                        ConcatService_name= Service_names.get(k);
                    }else {
                        ConcatService_name = ConcatService_name.concat(","+Service_names.get(k));
                    }
                }
                Log.e("concate_service",""+ConcatService);
                Log.e("concate_service_name",""+ConcatService);
//                    Intent intent = new Intent(Registration_Step_1.this, RegistrationActivityTwo.class);
//                    intent.putExtra("com_name", ConcatService_name);
//                    intent.putExtra("ConcatService", ConcatService);
//                dataregistration.setSub_bus_type(ConcatService_name);
//                    startActivity(intent);

                RegisteronlyStaff(country, state, email, password, first_name, last_name, comp_name,
                 pincode, "3", ConcatService);

            }
        });

         gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
       // set LayoutManager to RecyclerView
        GETAllServiceS();

    }
//***************************************************************************************************
    private void RegisteronlyStaff(String country, String state, String email, String password, String first_name,
                                   String last_name, String comp_name, String pincode, String business_type, String concatService) {

        progressDialog = new ProgressDialog(Registration_Step_1.this);
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
                last_name,comp_name,pincode,business_type,concatService);
        get_aboutCall.enqueue(new Callback<Registration_only>() {
            @Override
            public void onResponse(Call<Registration_only> call, Response<Registration_only> response) {
                progressDialog.dismiss();
                Log.e("responce is ", "" + response.body().getResponce());
                Log.e("responce message is ", "" + response.body().getMassage());
                Log.e("responce id is ", "" + response.body().getMassage().getId());

                if (response.body().getResponce().booleanValue() == true) {
                   sessionManager.serverEmailLogin(Integer.valueOf(response.body().getMassage().getId()));
                Shared_Preference.setId(Registration_Step_1.this,response.body().getMassage().getId());

                Intent intent = new Intent(Registration_Step_1.this, Business_Dashboard_Main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                finish();
                Toast.makeText(Registration_Step_1.this, "Successful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Registration_only> call, Throwable t) {
                Log.e("Failer",""+t.getMessage());
//                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }
//*******************************************************************************************
    private void Add_New_Service(String add_new_service) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);

        Log.e("et_add",""+add_new_service);
        Call<Add_Services> get_aboutCall = AbloutApi.Add_Services_Call("3",add_new_service,"pp");


        get_aboutCall.enqueue(new Callback<Add_Services>() {
            @Override
            public void onResponse(Call<Add_Services> call, Response<Add_Services> response) {
                progressDialog.dismiss();

                Log.e("Add_new_service" , ""+response.toString());
                Log.e("Add_service_res_msg" , ""+response.body().getResponce());
                Log.e("Add_service_res_msg_id" , ""+response.body().getMassage().getId());
                Log.e("Add_service_res_suc" , ""+response.isSuccessful());

               if (response.isSuccessful()){

              get_services_data.clear();
              service_adapter.notifyDataSetChanged();

              GETAllServiceS();

               }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Add_Services> call, Throwable t) {

                Log.e("failer",""+t.getMessage());
                Toast.makeText(Registration_Step_1.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Registration_Step_1.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
//***************************************************************************************************************
    private void GETAllServiceS() {
                progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<GET_Services> get_aboutCall = AbloutApi.Get_ServicesUsersCall("3");
        get_aboutCall.enqueue(new Callback<GET_Services>() {
            @Override
            public void onResponse(Call<GET_Services> call, Response<GET_Services> response) {
                progressDialog.dismiss();
               // Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
             //   SubTypestrings = new String[response.body().getData().size()];
//                Log.e("getact" , ""+response.body().getData().size());
                for(int k=0;k<response.body().getData().size();k++)
                {
                    Log.e("getact msain" , ""+response.body().getData().get(k).getTypeId());
                    get_services_data.add(new GET_Services_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId() ,response.body().getData().get(k).getService() ,response.body().getData().get(k).getImage(),response.body().getData().get(k).getStatus()));
                   // type_sub_user_data.add(new Type_Sub_User_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId(),response.body().getData().get(k).getSubtypename() ));
                    //SubTypestrings[k] = response.body().getData().get(k).getSubtypename();
                }
               // sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_expandable_list_item_1 , SubTypestrings));
             //   sub_main_type.setVisibility(View.VISIBLE);
                service_adapter = new Service_Adapter(Registration_Step_1.this , get_services_data);
             //   gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
              //  serv_id.addItemDecoration(new DividerItemDecoration(Registration_Step_1.this, LinearLayoutManager.VERTICAL));
                serv_id.setLayoutManager(gridLayoutManager);
                serv_id.setAdapter(service_adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GET_Services> call, Throwable t) {
                Toast.makeText(Registration_Step_1.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Registration_Step_1.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
