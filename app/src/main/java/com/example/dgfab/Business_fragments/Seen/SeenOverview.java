package com.example.dgfab.Business_fragments.Seen;

import android.app.ProgressDialog;
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
import com.example.dgfab.AllParsings.MyInfo;
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

import static com.example.dgfab.Connections.SeenProfile.Theirid;

public class SeenOverview extends Fragment {
    LinearLayout tenpro ;
    TextView seentender,seenemail,seenmonth,seemob,seepin,seencom,seenvin,sennteam,seencomdes,seenadd;
    LinearLayout savetender,saveemail,savemouth,savemobile,savepin,savecompany,savemdname,saveteam,savecompdes,saveadd;
    TextView tentxt;
    String savetenderString,saveemailString,savemouthString,savemobileString,savepinString,savecompanyString,savemdnameString,saveteamString,savecompdesString,saveaddString;
    String user_idString,nameString,lastnameString,mobileString,emailString,countryString,stateString,cityString,pinString;
    ImageView edtx,emailedit,monthedt,mobEdt,pinedt,comedt,mdnm,termEdt,abtEdit;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View b = inflater.inflate(R.layout.seenoverview , container , false);
        seentender = b.findViewById(R.id.seentender);
        seenemail = b.findViewById(R.id.seenemail);
        seenmonth = b.findViewById(R.id.seenmonth);
        seemob = b.findViewById(R.id.seemob);
        seepin = b.findViewById(R.id.seepin);
        seencom = b.findViewById(R.id.seencom);
        seenvin = b.findViewById(R.id.seenvin);
        sennteam = b.findViewById(R.id.sennteam);
        seenadd = b.findViewById(R.id.seenadd);
        sessionManager = new SessionManager(getActivity());
        Log.e("seen over" , ""+sessionManager.getUS());



        GETMYINFORMATION(Integer.valueOf(Theirid));
        Toast.makeText(getActivity(), "sdfhkjsdfh", Toast.LENGTH_SHORT).show();
//        tentxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity() , My_tender.class);
////                startActivity(intent);
//            }
//        });
       /* edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "XYZ", Toast.LENGTH_SHORT).show();
                memail.setText("xyz");
            }
        });*/
//        tenpro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity() , My_tender.class);
////                startActivity(intent);
//            }
//        });
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
        Call<MyInfo> Get_All_Country_New = AbloutApi.MY_INFO_CALL(String.valueOf(us));
        Get_All_Country_New.enqueue(new Callback<MyInfo>() {
            @Override
            public void onResponse(Call<MyInfo> call, Response<MyInfo> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        seenemail.setText(response.body().getData().getEmail());
                        seemob.setText(response.body().getData().getMobile());
                        seenadd.setText(response.body().getData().getAddress());
                        seencom.setText(response.body().getData().getCompanyName());
                        seepin.setText(response.body().getData().getPin());

                        user_idString = response.body().getData().getId();
                        nameString  =response.body().getData().getName();
                        lastnameString = response.body().getData().getLastname();
                        mobileString = response.body().getData().getMobile();
                        emailString = response.body().getData().getEmail();
                        countryString = response.body().getData().getCountry();
                        stateString = response.body().getData().getState();
                        cityString = response.body().getData().getCity();
                        pinString = response.body().getData().getPin();
//                        savemouthString = response.body().getData().getEmail();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
//                        lastnameString = response.body().getData().getLastname();
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
//        tentxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity() , My_tender.class);
////                startActivity(intent);
//            }
//        });
//        tenpro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getActivity(), "jfbhsd", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity() , My_tender.class);
////                startActivity(intent);
//            }
//        });
        super.onViewCreated(view, savedInstanceState);

    }

    //-----------------------------------------

//    public  class UpdateProfile extends AsyncTask<String, String, String> {
//        View save;
//        ProgressDialog dialog;
//
//        public UpdateProfile(View rootView) {
//            this.save = rootView;
//        }
//
//        protected void onPreExecute() {
//            dialog = new ProgressDialog(getActivity());
//            dialog.show();
//
//        }
//
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("https://sdltechserv.in/dgfeb/api/api/updateuserdata");
//
//
//                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("user_id", user_idString);
//                postDataParams.put("name", nameString);
//                postDataParams.put("lastname", lastnameString);
//                postDataParams.put("mobile", savemobileString);
//                postDataParams.put("email", saveemailString);
//                postDataParams.put("country", countryString);
//                postDataParams.put("state", stateString);
//                postDataParams.put("city", cityString);
//                postDataParams.put("pin", pinString);
//                postDataParams.put("mdname", savemdnameString);
//                postDataParams.put("team", saveteamString);
//                postDataParams.put("about", savecompdesString);
//                postDataParams.put("company", savecompanyString);
//                postDataParams.put("monthly", savemouthString);
//                postDataParams.put("average", savemouthString);
//                postDataParams.put("purchase", savemouthString);
//                postDataParams.put("primary", saveaddString);
//             /*   postDataParams.put("name", EtMob);
//                postDataParams.put("email", EtPass);
//                postDataParams.put("passout_year", EtPass);
//                postDataParams.put("collage_name", EtPass);
//                postDataParams.put("address", EtPass);
//                postDataParams.put("profile_image", EtPass);
//                postDataParams.put("sign_image", EtPass);
//                postDataParams.put("degree_upload", EtPass);
//                postDataParams.put("student_id", EtPass);*/
//
//
//                Log.e("postDataParams", postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000  /*milliseconds*/);
//                conn.setConnectTimeout(15000  /*milliseconds*/);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in = new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line = "";
//
//                    while ((line = in.readLine()) != null) {
//
//                        StringBuffer Ss = sb.append(line);
//                        Log.e("Ss", Ss.toString());
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                    /*BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuilder result = new StringBuilder();
//                   // String line;
//                  *//*  while ((line = r.readLine()) != null) {
//                        result.append(line);
//                    }*//*
//                    r.close();
//                    return result.toString();*/
//
//                } else {
//                    return new String("false : " + responseCode);
//                }
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                dialog.dismiss();
//
//                // JSONObject jsonObject = null;
//                Log.e("SendJsonDataToServer>>>", result.toString());
//                try {
//
//                    JSONObject jsonObject = new JSONObject(result);
//                    String responce = jsonObject.getString("responce");
//                    JSONObject object = jsonObject.getJSONObject("massage");
//                    if(responce.equals("true")) {
//                        Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
////                        if(save.getVisibility() == View.VISIBLE)
////                        {
////                            Toast.makeText(getActivity(), "goning view", Toast.LENGTH_SHORT).show();
//////                                 save.setVisibility(View.GONE);
////                        }else {
////                            Toast.makeText(getActivity(), "done with it", Toast.LENGTH_SHORT).show();
////                        }
//                        //     save.setVisibility(View.GONE);
//                    }else {
//                        Toast.makeText(getActivity(), "Profile update failed", Toast.LENGTH_SHORT).show();
//                    }
////                    user_id = object.getString("user_id");
////                     name = object.getString("name");
////                    String email = object.getString("email");
////                    String password = object.getString("password");
////                    String mobile = object.getString("mobile");
////                    String address = object.getString("address");
////                    String type = object.getString("type");
////                    String reffercode = object.getString("reffercode");
////                    String bankstatus = object.getString("bankstatus");
////                    String walletbal = object.getString("walletbal");
////                    String image = object.getString("image");
////                    String status = object.getString("status");
//
//
//                    if (responce.equalsIgnoreCase("true")) {
//                       /* Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                        intent.putExtra("Type", strType);
//                        startActivity(intent);
//                        finish();*/
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        public String getPostDataString(JSONObject params) throws Exception {
//
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//
//            Iterator<String> itr = params.keys();
//
//            while (itr.hasNext()) {
//
//                String key = itr.next();
//                Object value = params.get(key);
//
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(key, "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//            }
//            return result.toString();
//        }
//
//    }
}
