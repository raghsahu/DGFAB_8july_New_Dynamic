package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.dgfab.LoginandReg.ManuLoginActivity;
import com.example.dgfab.LoginandReg.RegistrationActivityTwo;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.example.dgfab.SessionManage.Shared_Preference;
import com.google.gson.JsonParser;

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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

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
    List<Searching_Manufacturers_Data> manufacturers_dataHashSet;
    private ProgressDialog progressDialog;
    ArrayList<Searching_Manufacturers_Data> searching_manufacturers_data = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__all__users);
        search_all = findViewById(R.id.search_all);
        searc_all = findViewById(R.id.searc_all);
        sessionManager = new SessionManager(this);
        GETALLUSERS("");
       /* LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        search_all.setLayoutManager(llm);*/
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Search_All_Users.this,2);
        search_all.setLayoutManager(gridLayoutManager);
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
        new GetAllUsers(namesearch,sessionManager.getUS()).execute();

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Getting Country");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//        Log.d("sortname is" , namesearch);
//        Call<Searching_Manufacturers> Get_All_Country_New = AbloutApi.SEARCH_ALL_USERS_CALL(namesearch,String.valueOf(sessionManager.getUS()));
//        Get_All_Country_New.enqueue(new Callback<Searching_Manufacturers>() {
//            @Override
//            public void onResponse(Call<Searching_Manufacturers> call, Response<Searching_Manufacturers> response) {
//                Log.e("getcity" , ""+response.toString());
//                if (response!=null){
//                    Log.e("Get_City",""+response.body().getResponce());
//                    try {
//                        for(int i=0;i<response.body().getData().size(); i++)
//                        {
//                            if(response.body().getData().get(i).getName().length() !=0) {
//                                Log.d("sortname is", response.body().getData().get(i).getName());
//                                Log.d("sortname status is", "" + response.body().getData().get(i).getReqstatus());
//                                String rx =response.body().getData().get(i).getReqstatus();
//                                String sx =response.body().getData().get(i).getSenderid();
//                                try {
//                                    if(String.valueOf(sessionManager.getUS()).equals(response.body().getData().get(i).getSenderid())) {
//                                        if(response.body().getData().get(i).getReqstatus().equals("1")) {
//                                            searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Already Connected" ,response.body().getData().get(i).getCoverImage()));
//                                        }else {
//                                            searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Request Sent",response.body().getData().get(i).getCoverImage()));
//                                        }
//
//                                    }else if(String.valueOf(sessionManager.getUS()).equals(response.body().getData().get(i).getReceiverid()))
//                                    {
//                                        if(response.body().getData().get(i).getReqstatus().equals("1")) {
//                                            searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Already Connected",response.body().getData().get(i).getCoverImage()));
//                                        }else {
//                                            searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Requested",response.body().getData().get(i).getCoverImage()));
//                                        }
//                                    }else {
//                                        searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Connect Now",response.body().getData().get(i).getCoverImage()));
//                                    }
//                                  //  if (response.body().getData().get(i).getReqstatus().equals("0") || response.body().getData().get(i).getReqstatus().equals("null")) {
////                                        if (response.body().getData().get(i).getReqstatus().equals("0")) {
//
//
////                                        } else {
////                                            searching_manufacturers_data.add(new Searching_Manufacturers_Data(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), "Connect Now"));
////                                        }
////                                    } else {
////                                        Toast.makeText(Search_All_Users.this, "Already connected", Toast.LENGTH_SHORT).show();
////                                    }
//                                }catch (Exception e)
//                                {
//                                    e.printStackTrace();
//
//                                }
//
//                            }
//                        }
//                        int sp = searching_manufacturers_data.size();
//                        //removeDuplicate(searching_manufacturers_data.get(0) ,0);
//
////                        for(int i =0;i<searching_manufacturers_data.size();i++) {
////                            for(int p=0;p<i+1;p++)
////                            {
////                                String s1 =searching_manufacturers_data.get(p).getName();
////                                String s2 =searching_manufacturers_data.get(i).getName();
////
////                                if(searching_manufacturers_data.get(p).getName().equals(searching_manufacturers_data.get(i).getName()))
////                                {
////                                    searching_manufacturers_data.remove(i);
////                                }else {
////                                    Toast.makeText(Search_All_Users.this, "DO nothing", Toast.LENGTH_SHORT).show();
////                                }
////                            }
////
//////                       .
////                        }
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        search_all_adapter = new Search_All_Adapter(Search_All_Users.this ,manufacturers_dataHashSet);
//                        }else {
//                            search_all_adapter = new Search_All_Adapter(Search_All_Users.this ,searching_manufacturers_data);
//                        }
//                        LinearLayoutManager llm = new LinearLayoutManager(Search_All_Users.this);
//                           llm.setOrientation(LinearLayoutManager.VERTICAL);
//                           search_all.setLayoutManager(llm);
//                           search_all.setAdapter(search_all_adapter);
//                        // countyed.showDropDown();
//                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Searching_Manufacturers> call, Throwable t) {
//                progressDialog.dismiss();
//                Log.e("error_country",""+t.getMessage());
//                Toast.makeText(Search_All_Users.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(Search_All_Users.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private List<Searching_Manufacturers_Data> removeDuplicate(Searching_Manufacturers_Data i , int ui) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manufacturers_dataHashSet = searching_manufacturers_data.stream().distinct().collect(Collectors.toList());
            return  manufacturers_dataHashSet;

        }else {
            for(int high=0;high<searching_manufacturers_data.size();high++)
            {
                for(int low=0;low<searching_manufacturers_data.size();low++)
                {
                    if(searching_manufacturers_data.get(high).equals(searching_manufacturers_data.get(low)))
                    {
                        searching_manufacturers_data.remove(low);
                    }else {
                        Toast.makeText(this, ""+searching_manufacturers_data.get(low), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            return searching_manufacturers_data;
        }
//        // Hash to store seen values
//      manufacturers_dataHashSet = new HashSet<>();
//
//        /* Pick elements one by one */
//        Searching_Manufacturers_Data current = i;
//        Searching_Manufacturers_Data prev = null;
//        while (current != null)
//        {
//            Searching_Manufacturers_Data curval = current;
//
//            // If current value is seen before
//            if (manufacturers_dataHashSet.contains(curval)) {
//                prev.NextaNode.equals(current.NextaNode);
//            } else {
//                manufacturers_dataHashSet.add(curval);
//                prev = current;
//            }
//            current = current.NextaNode;
//            if(ui < searching_manufacturers_data.size()  -1) {
//                ui++;
//
//                removeDuplicate(searching_manufacturers_data.get(ui), ui);
//            }else {
//                return;
//            }
//        }
    }

    private class GetAllUsers extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String namesearch;
        int us;

        public GetAllUsers(String namesearch, int us) {
            this.namesearch =namesearch;
            this.us = us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(Search_All_Users.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/searchbyusers");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", us);
                postDataParams.put("name", namesearch);


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

                    jsonObject = new JSONObject(result);

                    Boolean response = jsonObject.getBoolean("responce");
                    if(response) {
                        for (int ip = 0; ip < jsonObject.getInt("usercount"); ip++)
                        {
                            JsonParser parser = new JsonParser();
                           // String retVal = parser.parse(String.valueOf("user"+ip+1)).getAsString();
                           JSONObject object = jsonObject.getJSONObject("user"+ip);
                     //      1== accept , 2== reject,0=pending ,null= not sent
                            try {
                                String px= object.getString("status");
                                if(object.getString("status").equals("2")) {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Accepted"));
                                }else if(object.getString("status").equals("1"))
                                {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Pending"));
                                }else if(object.getString("status").equals("0"))
                                {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                }else if(object.getString("status").equals(""))
                                {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                }
                            }catch (Exception e)
                            {
                                searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                        object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                e.printStackTrace();
                            }

//                           object.get()
                        }
                        search_all_adapter = new Search_All_Adapter(Search_All_Users.this , searching_manufacturers_data);
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        search_all.addItemDecoration(new DividerItemDecoration(Search_All_Users.this, LinearLayoutManager.VERTICAL));
                        search_all.setLayoutManager(gridLayoutManager);
                        search_all.setAdapter(search_all_adapter);
                    }
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

//    private void heapify(ArrayList<Searching_Manufacturers_Data> searching_manufacturers_data, int n, int i) {
//
//        int largest = i; // Initialize largest as root
//        int l = 2*i + 1; // left = 2*i + 1
//        int r = 2*i + 2; // right = 2*i + 2
//
//        // If left child is larger than root
//        if (l < n && searching_manufacturers_data.get(l).equals(searching_manufacturers_data.get(largest)))
//            largest = l;
//
//        // If right child is larger than largest so far
//        if (r < n && searching_manufacturers_data.get(r).equals(searching_manufacturers_data.get(largest)) )
//            largest = r;
//
//        // If largest is not root
//        if (largest != i)
//        {
//            Searching_Manufacturers_Data swap = searching_manufacturers_data.get(i);
//            searching_manufacturers_data.get(i).se =   searching_manufacturers_data.get(largest);
//            searching_manufacturers_data.get(largest) = swap;
//
//            // Recursively heapify the affected sub-tree
//            heapify(arr, n, largest);
//        }
//    }
}
