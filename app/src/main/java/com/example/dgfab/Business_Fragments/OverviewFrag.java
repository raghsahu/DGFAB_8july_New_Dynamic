package com.example.dgfab.Business_Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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


public class OverviewFrag extends Fragment {
    LinearLayout tenpro ;
    EditText edTender,memail,emsalary,mmobile,mpincode,mcomp,mmdn,mteam,mcomab,madd,mmdname,maboutcomp;
    LinearLayout savetender,saveemail,savemouth,savemobile,savepin,savecompany,savemdname,saveteam,savecompdes,saveadd;
    TextView tentxt;
    String savetenderString,saveemailString,savemouthString,savemobileString,savepinString,savecompanyString,savemdnameString,saveteamString,savecompdesString,saveaddString;
    String user_idString,nameString,lastnameString,mobileString,emailString,countryString,stateString,cityString,pinString;
    ImageView edtx,emailedit,monthedt,mobEdt,pinedt,comedt,mdnm,termEdt,abtEdit;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View b = inflater.inflate(R.layout.fragment_overview , container , false);
        emsalary = b.findViewById(R.id.msalary);

        //image saves
        savetender = b.findViewById(R.id.savetender);

        saveemail = b.findViewById(R.id.saveemail);
        savemouth = b.findViewById(R.id.savemonth);
        savemobile = b.findViewById(R.id.savemobile);
        savepin = b.findViewById(R.id.savepin);
        savecompany = b.findViewById(R.id.savecompany);
        savemdname = b.findViewById(R.id.savemd);
        saveteam = b.findViewById(R.id.saveteam);
        savecompdes = b.findViewById(R.id.savecomdes);
        saveadd = b.findViewById(R.id.saveadd);

        //Visible falese
        savetender.setVisibility(View.GONE);
        savetender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
            }
        });
        saveadd.setVisibility(View.GONE);
        saveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(saveadd).execute();
            }
        });
        savecompany.setVisibility(View.GONE);

        savecompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savecompany.getRootView()).execute();

            }
        });
        savecompdes.setVisibility(View.GONE);
        savecompdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savecompdes).execute();

            }
        });
        saveemail.setVisibility(View.GONE);
        saveemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(saveemail).execute();
            }
        });
        savemdname.setVisibility(View.GONE);
        savemdname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savemdname).execute();

            }
        });
        savepin.setVisibility(View.GONE);
        savepin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savepin).execute();

            }
        });
        saveteam.setVisibility(View.GONE);
        saveteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(saveteam).execute();

            }
        });
        savemouth.setVisibility(View.GONE);
        savemouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savemouth).execute();

            }
        });
        savemobile.setVisibility(View.GONE);
        savemobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetenderString = edTender.getText().toString();
                saveemailString = memail.getText().toString();
                savemouthString = emsalary.getText().toString();
                savemobileString = mmobile.getText().toString();
                savepinString = mpincode.getText().toString();
                savecompanyString = mcomp.getText().toString();
                savemdnameString = mmdname.getText().toString();
                saveteamString = mteam.getText().toString();
                savecompdesString = maboutcomp.getText().toString();
                saveaddString = madd.getText().toString();
                new UpdateProfile(savemobile).execute();

            }
        });
        //

        //
      //  emsalary.setFocusable(false);
        emsalary.setEnabled(false);
        mmobile = b.findViewById(R.id.mmobile);
        maboutcomp = b.findViewById(R.id.maboutcomp);
        mpincode = b.findViewById(R.id.mpincode);
        abtEdit = b.findViewById(R.id.abtEdit);
        mcomab = b.findViewById(R.id.mcomp);
        mmdname = b.findViewById(R.id.mmdname);
        madd = b.findViewById(R.id.madd);
        mcomp = b.findViewById(R.id.mcomp);
//        mcomab = b.findViewById(R.id.mcomab);
        tenpro = b.findViewById(R.id.tenpro);
        tentxt= b.findViewById(R.id.tentxt);
        sessionManager= new SessionManager(getActivity());
        tenpro = b.findViewById(R.id.tenpro);
        memail = b.findViewById(R.id.memail);
        mteam = b.findViewById(R.id.mteam);
        memail.setEnabled(false);
        edTender = b.findViewById(R.id.edTender);
        edTender.setEnabled(false);
     //   abtEdit.setEnabled(false);
        mcomp.setEnabled(false);
   //     mmobile.setFocusable(false);
     //   mmdname.setFocusable(false);
     //   mpincode.setFocusable(false);
     //   mcomp.setFocusable(false);
     //   mteam.setFocusable(false);
      //  abtEdit.setFocusable(false);
      //  maboutcomp.setFocusable(false);
        mmobile.setEnabled(false);
        maboutcomp.setEnabled(false);
        mteam.setEnabled(false);
        mmdname.setEnabled(false);
        mpincode.setEnabled(false);

        edtx = b.findViewById(R.id.edtx);
        edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTender.setFocusable(true);
                edTender.setEnabled(true);
                savetender.setVisibility(View.VISIBLE);

            }
        });

        emailedit = b.findViewById(R.id.emailedit);
        emailedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memail.setFocusable(true);
                memail.setEnabled(true);
                saveemail.setVisibility(View.VISIBLE);
            }
        });

        monthedt = b.findViewById(R.id.monthedt);
        monthedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emsalary.setFocusable(true);
                emsalary.setEnabled(true);
                savemouth.setVisibility(View.VISIBLE);
            }
        });

        mobEdt = b.findViewById(R.id.mobEdt);
        mobEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmobile.setFocusable(true);
                mmobile.setEnabled(true);
                savemobile.setVisibility(View.VISIBLE);
            }
        });

        pinedt = b.findViewById(R.id.pinedt);
        pinedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpincode.setFocusable(true);
                mpincode.setEnabled(true);
                savepin.setVisibility(View.VISIBLE);
            }
        });

        comedt = b.findViewById(R.id.comedt);
        comedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcomp.setFocusable(true);
                mcomp.setEnabled(true);
                savecompany.setVisibility(View.VISIBLE);
            }
        });

        mdnm = b.findViewById(R.id.mdnm);
        mdnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmdname.setFocusable(true);
                mmdname.setEnabled(true);
                savemdname.setVisibility(View.VISIBLE);
            }
        });

        termEdt = b.findViewById(R.id.termEdt);
        termEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mteam.setFocusable(true);
                mteam.setEnabled(true);
                saveteam.setVisibility(View.VISIBLE);
            }
        });

        abtEdit = b.findViewById(R.id.abtEdit);
        abtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maboutcomp.setFocusable(true);
                maboutcomp.setEnabled(true);
                savecompdes.setVisibility(View.VISIBLE);
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

 public  class UpdateProfile extends AsyncTask<String, String, String> {
        View save;
        ProgressDialog dialog;

     public UpdateProfile(View rootView) {
         this.save = rootView;
     }

     protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/updateuserdata");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", user_idString);
                postDataParams.put("name", nameString);
                postDataParams.put("lastname", lastnameString);
                postDataParams.put("mobile", savemobileString);
                postDataParams.put("email", saveemailString);
                postDataParams.put("country", countryString);
                postDataParams.put("state", stateString);
                postDataParams.put("city", cityString);
                postDataParams.put("pin", pinString);
                postDataParams.put("mdname", savemdnameString);
                postDataParams.put("team", saveteamString);
                postDataParams.put("about", savecompdesString);
                postDataParams.put("company", savecompanyString);
                postDataParams.put("monthly", savemouthString);
                postDataParams.put("average", savemouthString);
                postDataParams.put("purchase", savemouthString);
                postDataParams.put("primary", saveaddString);
             /*   postDataParams.put("name", EtMob);
                postDataParams.put("email", EtPass);
                postDataParams.put("passout_year", EtPass);
                postDataParams.put("collage_name", EtPass);
                postDataParams.put("address", EtPass);
                postDataParams.put("profile_image", EtPass);
                postDataParams.put("sign_image", EtPass);
                postDataParams.put("degree_upload", EtPass);
                postDataParams.put("student_id", EtPass);*/


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

                    /*BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder result = new StringBuilder();
                   // String line;
                  *//*  while ((line = r.readLine()) != null) {
                        result.append(line);
                    }*//*
                    r.close();
                    return result.toString();*/

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
                    JSONObject object = jsonObject.getJSONObject("massage");
                    if(responce.equals("true")) {
                        Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                        if(save.getVisibility() == View.VISIBLE)
//                        {
//                            Toast.makeText(getActivity(), "goning view", Toast.LENGTH_SHORT).show();
////                                 save.setVisibility(View.GONE);
//                        }else {
//                            Toast.makeText(getActivity(), "done with it", Toast.LENGTH_SHORT).show();
//                        }
             //     save.setVisibility(View.GONE);
                    }else {
                        Toast.makeText(getActivity(), "Profile update failed", Toast.LENGTH_SHORT).show();
                    }
//                    user_id = object.getString("user_id");
//                     name = object.getString("name");
//                    String email = object.getString("email");
//                    String password = object.getString("password");
//                    String mobile = object.getString("mobile");
//                    String address = object.getString("address");
//                    String type = object.getString("type");
//                    String reffercode = object.getString("reffercode");
//                    String bankstatus = object.getString("bankstatus");
//                    String walletbal = object.getString("walletbal");
//                    String image = object.getString("image");
//                    String status = object.getString("status");


                    if (responce.equalsIgnoreCase("true")) {
                       /* Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("Type", strType);
                        startActivity(intent);
                        finish();*/
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

    }
}
