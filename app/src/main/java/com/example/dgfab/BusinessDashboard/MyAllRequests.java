package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.CommingConnAdapter;
import com.example.dgfab.Adapter.MyAllSentRequestAdapter;
import com.example.dgfab.Adapter.Search_All_Adapter;
import com.example.dgfab.AllParsings.All_Sent_Request;
import com.example.dgfab.AllParsings.All_Sent_Request_Data;
import com.example.dgfab.AllParsings.CommingRequest;
import com.example.dgfab.AllParsings.CommingRequestData;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAllRequests extends AppCompatActivity {
  RecyclerView sentrecy ,reciverec;
  MyAllSentRequestAdapter myallSentRequestAdapter;
  SessionManager sessionManager;
  ArrayList<All_Sent_Request_Data> all_sent_request_data = new ArrayList<>();
    RecyclerView myconsreq, crmnoti;
    CommingConnAdapter commingConnAdapter;
    List<CommingRequestData> commingRequestData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_requests);
        sentrecy = findViewById(R.id.sentrecy);
        crmnoti = findViewById(R.id.crmnoti);
        myconsreq = findViewById(R.id.reciverec);
        sessionManager = new SessionManager(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        sentrecy.setLayoutManager(llm);
        GETAllSENTREQUESTS(sessionManager.getUS());
        GETALLRecivedRequests(sessionManager.getUS());
        GETALLREMAINDER(sessionManager.getUS());

    }

    private void GETALLREMAINDER(int us) {

    }

    private void GETAllSENTREQUESTS(int us) {

        ProgressDialog progressDialog = new ProgressDialog(this);
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
        Call<All_Sent_Request> Get_All_Country_New = AbloutApi.ALL_SENT_REQUEST_CALL(String.valueOf(us));
        Get_All_Country_New.enqueue(new Callback<All_Sent_Request>() {
            @Override
            public void onResponse(Call<All_Sent_Request> call, Response<All_Sent_Request> response) {
                Log.e("getcity" , ""+response.toString());
                Log.e("getcity" , ""+us);
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        for(int i=0;i<response.body().getData().size(); i++)
                        {
                            if(response.body().getData().get(i).getName().length() !=0) {
                                Log.d("sortname is" , response.body().getData().get(i).getName());
                                if(response.body().getData().get(i).getStatus().equals("1")) {
                                    all_sent_request_data.add(new All_Sent_Request_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage() ,response.body().getData().get(i).getStatus() , "Pending"));
                                }else {

                                }
                            }
                        }
                        myallSentRequestAdapter = new MyAllSentRequestAdapter(MyAllRequests.this , all_sent_request_data);
                        sentrecy.setAdapter(myallSentRequestAdapter);
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
            public void onFailure(Call<All_Sent_Request> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(MyAllRequests.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MyAllRequests.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    private void GETALLRecivedRequests(int us) {
        ProgressDialog progressDialog = new ProgressDialog(MyAllRequests.this);
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
        Call<CommingRequest> Get_All_Country_New = AbloutApi.COMMING_REQUEST_CALL(String.valueOf(us));
        Get_All_Country_New.enqueue(new Callback<CommingRequest>() {
            @Override
            public void onResponse(Call<CommingRequest> call, Response<CommingRequest> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        for(int i=0;i<response.body().getData().size(); i++)
                        {
                            if(response.body().getData().get(i).getName().length() !=0) {
                                Log.d("sortme GETALLRequests is", response.body().getData().get(i).getId());
                                if(response.body().getData().get(i).getUstatus().equals("1")) {
                                    commingRequestData.add(new CommingRequestData(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), response.body().getData().get(i).getReceiverid(), response.body().getData().get(i).getSenderid(), response.body().getData().get(i).getUstatus()));
                                }else {
                                    Toast.makeText(MyAllRequests.this, "already taken care", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        LinearLayoutManager llm = new LinearLayoutManager(MyAllRequests.this);
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        commingConnAdapter = new CommingConnAdapter(MyAllRequests.this , commingRequestData);
                        myconsreq.setLayoutManager(llm);
                        myconsreq.setAdapter(commingConnAdapter);
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
            public void onFailure(Call<CommingRequest> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(MyAllRequests.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MyAllRequests.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
