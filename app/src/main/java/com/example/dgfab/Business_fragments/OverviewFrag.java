package com.example.dgfab.Business_fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.Search_All_Adapter;
import com.example.dgfab.AllParsings.MyInfo;
import com.example.dgfab.AllParsings.Searching_Manufacturers;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.BusinessDashboard.Search_All_Users;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OverviewFrag extends Fragment {
    LinearLayout tenpro ;
    EditText memail,emsalary,mmobile,mpincode,mcomp,mmdn,mteam,mcomab,madd;
    TextView tentxt;
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View b = inflater.inflate(R.layout.fragment_overview , container , false);
        tenpro = b.findViewById(R.id.tenpro);
        memail = b.findViewById(R.id.memail);
        emsalary = b.findViewById(R.id.msalary);
        mmobile = b.findViewById(R.id.mmobile);
        mpincode = b.findViewById(R.id.mpincode);
        madd = b.findViewById(R.id.madd);
        tenpro = b.findViewById(R.id.tenpro);
        tentxt= b.findViewById(R.id.tentxt);
        sessionManager= new SessionManager(getActivity());
        GETMYINFORMATION(sessionManager.getUS());
        Toast.makeText(getActivity(), "sdfhkjsdfh", Toast.LENGTH_SHORT).show();
        tentxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity() , My_tender.class);
//                startActivity(intent);
            }
        });
        tenpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity() , My_tender.class);
//                startActivity(intent);
            }
        });
        return inflater.inflate(R.layout.fragment_overview, container, false);

    }

    private void GETMYINFORMATION(int us) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
        Log.d("sortname is" , ""+us);
        Call<MyInfo> Get_All_Country_New = AbloutApi.MY_INFO_CALL(String.valueOf(sessionManager.getUS()));
        Get_All_Country_New.enqueue(new Callback<MyInfo>() {
            @Override
            public void onResponse(Call<MyInfo> call, Response<MyInfo> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        memail.setText(response.body().getData().getEmail());
                        mmobile.setText(response.body().getData().getMobile());
                        madd.setText(response.body().getData().getAddress());
                        mcomp.setText(response.body().getData().getCompanyName());
                        mcomab.setText(response.body().getData().getBrandName());
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
            public void onFailure(Call<MyInfo> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tenpro = view.findViewById(R.id.tenpro);
        tentxt= view.findViewById(R.id.tentxt);
        Toast.makeText(getActivity(), "pxxxxxxxxxxxxxxxx", Toast.LENGTH_SHORT).show();
        tentxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity() , My_tender.class);
//                startActivity(intent);
            }
        });
        tenpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity() , My_tender.class);
//                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);

    }
}
