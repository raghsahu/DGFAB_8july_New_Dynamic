package com.example.dgfab.BusinessDashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.Adapter.Service_Adapter;
import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.GET_Services;
import com.example.dgfab.AllParsings.GET_Services_Data;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProduct_Activity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    Spinner spicatpro ,spinsubcat;

    ArrayList<String> get_services_data = new ArrayList<>();
    ArrayAdapter<String>  service_adapter;
    private TextView addmore_service;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_);
        spicatpro= findViewById(R.id.spicatpro);
        spinsubcat = findViewById(R.id.spinsubcat);
        GETAllServiceS();


        addmore_service = findViewById(R.id.addmore_service);

        next = findViewById(R.id.nxpx);

        addmore_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddProduct_Activity.this);
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
                Toast.makeText(AddProduct_Activity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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

                //    get_services_data.add(new GET_Services_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId() ,response.body().getData().get(k).getService() ,response.body().getData().get(k).getImage(),response.body().getData().get(k).getStatus()));
                    // type_sub_user_data.add(new Type_Sub_User_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId(),response.body().getData().get(k).getSubtypename() ));
                    //SubTypestrings[k] = response.body().getData().get(k).getSubtypename();
                }
                // sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_expandable_list_item_1 , SubTypestrings));
                //   sub_main_type.setVisibility(View.VISIBLE);
                service_adapter = new ArrayAdapter<String>(AddProduct_Activity.this ,android.R.layout.simple_spinner_dropdown_item ,get_services_data);
                //   gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                //  serv_id.addItemDecoration(new DividerItemDecoration(Registration_Step_1.this, LinearLayoutManager.VERTICAL));
               // serv_id.setLayoutManager(gridLayoutManager);
                spicatpro.setAdapter(service_adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GET_Services> call, Throwable t) {
                Toast.makeText(AddProduct_Activity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}


