package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.Search_All_Adapter;
import com.example.dgfab.AllParsings.Get_Cities;
import com.example.dgfab.AllParsings.Get_Cities_Data;
import com.example.dgfab.AllParsings.Searching_Manufacturers;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.LoginandReg.RegistrationActivityTwo;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_All_Users extends AppCompatActivity {
    RecyclerView search_all;
    SearchView searc_all;
    SessionManager sessionManager;
    Search_All_Adapter search_all_adapter;
    private ProgressDialog progressDialog;
    ArrayList<Searching_Manufacturers_Data> searching_manufacturers_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__all__users);
        search_all = findViewById(R.id.search_all);
        searc_all = findViewById(R.id.searc_all);
        sessionManager = new SessionManager(this);
        GETALLUSERS("");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        search_all.setLayoutManager(llm);
        searc_all.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try{
                    searching_manufacturers_data.clear();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                GETALLUSERS(s.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try{
                    searching_manufacturers_data.clear();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                GETALLUSERS(s.toString());
                return false;
            }
        });

    }

    private void GETALLUSERS(String namesearch) {
        progressDialog = new ProgressDialog(this);
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
        Log.d("sortname is" , namesearch);
        Call<Searching_Manufacturers> Get_All_Country_New = AbloutApi.SEARCH_ALL_USERS_CALL(namesearch,String.valueOf(sessionManager.getUS()));
        Get_All_Country_New.enqueue(new Callback<Searching_Manufacturers>() {
            @Override
            public void onResponse(Call<Searching_Manufacturers> call, Response<Searching_Manufacturers> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        for(int i=0;i<response.body().getData().size(); i++)
                        {
                            if(response.body().getData().get(i).getName().length() !=0) {
                                Log.d("sortname is" , response.body().getData().get(i).getName());
                                searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage()));
                            }
                        }
                        search_all_adapter = new Search_All_Adapter(Search_All_Users.this , searching_manufacturers_data);
                        search_all.setAdapter(search_all_adapter);
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
            public void onFailure(Call<Searching_Manufacturers> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(Search_All_Users.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Search_All_Users.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
