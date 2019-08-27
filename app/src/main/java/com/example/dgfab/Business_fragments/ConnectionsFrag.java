package com.example.dgfab.Business_fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.CommingConnAdapter;
import com.example.dgfab.Adapter.ConnectionsOnlyAdapter;
import com.example.dgfab.Adapter.FriendsAdapter;
import com.example.dgfab.AllParsings.CommingRequest;
import com.example.dgfab.AllParsings.CommingRequestData;
import com.example.dgfab.AllParsings.Friends;
import com.example.dgfab.AllParsings.Friendsdata;
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


public class ConnectionsFrag extends Fragment {
    RecyclerView myconsreq;
    SessionManager sessionManager;
    FriendsAdapter friendsAdapter;
    List<Friendsdata> commingRequestData = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connections , container , false);
        myconsreq = view.findViewById(R.id.myconsreqs);
        sessionManager = new SessionManager(getActivity());

        GETALLRecivedRequests(sessionManager.getUS());
//        return inflater.inflate(R.layout.fragment_connections, container, false);
        return view;

    }

    private void GETALLRecivedRequests(int us) {
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
        Call<Friends> Get_All_Country_New = AbloutApi.FRIENDS_CALL(String.valueOf(us));
        Get_All_Country_New.enqueue(new Callback<Friends>() {
            @Override
            public void onResponse(Call<Friends> call, Response<Friends> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        for(int i=0;i<response.body().getData().size(); i++)
                        {
                            if(response.body().getData().get(i).getName().length() !=0) {
                                Log.d("sortname is" , response.body().getData().get(i).getName());
//                                if(!response.body().getData().get(i).getUstatus().equals("0")) {
                                commingRequestData.add(new Friendsdata(response.body().getData().get(i).getUserId(), response.body().getData().get(i).getName(), response.body().getData().get(i).getUstatus(), response.body().getData().get(i).getSenderid(), response.body().getData().get(i).getReceiverid(), response.body().getData().get(i).getMobile(), "", "", "", ""));
//                                }
                            }
                        }
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        friendsAdapter = new FriendsAdapter(getActivity() , commingRequestData);
                        myconsreq.setLayoutManager(llm);
                        myconsreq.setAdapter(friendsAdapter);
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
            public void onFailure(Call<Friends> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }
}
