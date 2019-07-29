package com.example.dgfab.Business_fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OverviewFrag extends Fragment {
    LinearLayout tenpro ;
    EditText edTender,memail,emsalary,mmobile,mpincode,mcomp,mmdn,mteam,mcomab,madd;
    TextView tentxt;
    ImageView edtx,emailedit,monthedt,mobEdt,pinedt,comedt,mdnm,termEdt,abtEdit;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View b = inflater.inflate(R.layout.fragment_overview , container , false);
        emsalary = b.findViewById(R.id.msalary);
        emsalary.setFocusable(false);
        emsalary.setEnabled(false);
        mmobile = b.findViewById(R.id.mmobile);
        mpincode = b.findViewById(R.id.mpincode);
        madd = b.findViewById(R.id.madd);
        mcomp = b.findViewById(R.id.mcomp);
        tenpro = b.findViewById(R.id.tenpro);
        tentxt= b.findViewById(R.id.tentxt);
        sessionManager= new SessionManager(getActivity());
        tenpro = b.findViewById(R.id.tenpro);
        memail = b.findViewById(R.id.memail);
        mteam = b.findViewById(R.id.mteam);
        memail.setEnabled(false);
        edTender = b.findViewById(R.id.edTender);
        edTender.setEnabled(false);

        edtx = b.findViewById(R.id.edtx);
        edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTender.setFocusable(true);
                edTender.setEnabled(true);
            }
        });

        emailedit = b.findViewById(R.id.emailedit);
        emailedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memail.setFocusable(true);
                memail.setEnabled(true);
            }
        });

        monthedt = b.findViewById(R.id.monthedt);
        monthedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emsalary.setFocusable(true);
                emsalary.setEnabled(true);
            }
        });

        mobEdt = b.findViewById(R.id.mobEdt);
        mobEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmobile.setFocusable(true);
                mmobile.setEnabled(true);
            }
        });

        pinedt = b.findViewById(R.id.pinedt);
        pinedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpincode.setFocusable(true);
                mpincode.setEnabled(true);
            }
        });

        comedt = b.findViewById(R.id.comedt);
        comedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcomp.setFocusable(true);
                mcomp.setEnabled(true);
            }
        });

        mdnm = b.findViewById(R.id.mdnm);
        mdnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        termEdt = b.findViewById(R.id.termEdt);
        termEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mteam.setFocusable(true);
                mteam.setEnabled(true);
            }
        });

        abtEdit = b.findViewById(R.id.abtEdit);
        abtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcomab.setFocusable(true);
                mcomab.setEnabled(true);
            }
        });


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
       /* edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "XYZ", Toast.LENGTH_SHORT).show();
                memail.setText("xyz");
            }
        });*/
        tenpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity() , My_tender.class);
//                startActivity(intent);
            }
        });
//        return inflater.inflate(R.layout.fragment_overview, container, false);
        return  b;

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

    //-----------------------------------------

    /*class SendJsonDataToServer extends AsyncTask<String, String, String> {

        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://sdltechserv.in/dgfeb/api/api/updateprofile");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("name", EtMob);
                postDataParams.put("email", EtPass);
                postDataParams.put("passout_year", EtPass);
                postDataParams.put("collage_name", EtPass);
                postDataParams.put("address", EtPass);
                postDataParams.put("profile_image", EtPass);
                postDataParams.put("sign_image", EtPass);
                postDataParams.put("degree_upload", EtPass);
                postDataParams.put("student_id", EtPass);


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 *//* milliseconds*//*);
                conn.setConnectTimeout(15000  *//*milliseconds*//*);
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

                *//*BufferedReader in = new BufferedReader(new
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
                return sb.toString(); *//*

                    BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        result.append(line);
                    }
                    r.close();
                    return result.toString();

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

                // JSONObject jsonObject = null;
                Log.e("SendJsonDataToServer>>>", result.toString());
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    String responce = jsonObject.getString("responce");
                    JSONObject object = jsonObject.getJSONObject("data");
                    user_id = object.getString("user_id");
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String password = object.getString("password");
                    String mobile = object.getString("mobile");
                    String address = object.getString("address");
                    String type = object.getString("type");
                    String reffercode = object.getString("reffercode");
                    String bankstatus = object.getString("bankstatus");
                    String walletbal = object.getString("walletbal");
                    String image = object.getString("image");
                    String status = object.getString("status");


                    if (responce.equalsIgnoreCase("true")) {
                     *//*   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("Type", strType);
                        startActivity(intent);
                        finish();*//*
                    }

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

    }*/
}
