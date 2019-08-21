package com.example.dgfab.Activity.Buyer_guest_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.Buyers.Buyer_Main_Navigation;
import com.example.dgfab.LoginandReg.ManuLoginActivity;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.Shared_Preference;

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

import javax.net.ssl.HttpsURLConnection;

public class OtpActivity extends AppCompatActivity {

    TextView tv_opt_verify,txtmobile;
    String Otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        tv_opt_verify=findViewById(R.id.tv_opt_verify);
        txtmobile=findViewById(R.id.txtmobile);
//        if(getIntent().getData() !=null)
//        {
         new SENDOTP(getIntent().getStringExtra("mob") , getIntent().getStringExtra("pinco")).execute();
//        }
        tv_opt_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Register_Them(getIntent().getStringExtra("mob"),getIntent().getStringExtra("pinco"),Otp , 1).execute();
//                Intent in = new Intent(OtpActivity.this, Buyer_Main_Navigation.class);
//                startActivity(in);
//                overridePendingTransition(R.anim.anim_slide_in_left,
//                        R.anim.anim_slide_out_left);
//                finish();
            }
        });



    }

    private class SENDOTP extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String mobs , pincode;

        public SENDOTP(String mobs, String pincode) {
            this.mobs = mobs;
            this.pincode = pincode;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(OtpActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/sendotp");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("mobile", mobs);
//                postDataParams.put("password", );


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
                    if(response.booleanValue() !=false)
                    {

                       String massage =  jsonObject.getString("massage");
                       String otp = massage.substring(22);
                       Log.d("otp is" , ""+otp);
                        Otp = otp;
                        txtmobile.setText(otp);
                    }else {
                        Toast.makeText(OtpActivity.this, "OTP Could not be sent", Toast.LENGTH_SHORT).show();
//                        overridePendingTransition(R.anim.anim_slide_in_left,
//                                R.anim.anim_slide_out_left);
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

    private class Register_Them extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String mobs , pincode,otp;
        int user_type;

        public Register_Them(String mob, String pincode, String otp, int user_type) {
            this.mobs = mob;
            this.pincode = pincode;
            this.otp = otp;
            this.user_type = user_type;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(OtpActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/buyerragistration");
                Log.d("string" , ""+otp);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("mobile", mobs);
                postDataParams.put("pincode", pincode);
                postDataParams.put("otp", Otp);
                postDataParams.put("user_type", 1);
//                postDataParams.put("password", );


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
                    if(response.booleanValue() !=false)
                    {
                        Intent intent = new Intent(OtpActivity.this , Buyer_Main_Navigation.class);
                        startActivity(intent);
                        Toast.makeText(OtpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                        String massage =  jsonObject.getString("massage");
//                        String otp = massage.substring(29);
//                        Log.d("otp is" , ""+otp);
//                        txtmobile.setText(otp);
                    }else {
                        Toast.makeText(OtpActivity.this, "OTP Could not be sent", Toast.LENGTH_SHORT).show();
//                        overridePendingTransition(R.anim.anim_slide_in_left,
//                                R.anim.anim_slide_out_left);
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
}
