package com.example.dgfab.LoginandReg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dgfab.BusinessDashboard.Business_Dashboard_Main;
import com.example.dgfab.R;

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

public class ManuLoginActivity extends AppCompatActivity {
    EditText email ,password;

    Button LogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        LogIn=findViewById(R.id.logmid);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().length()>0 && password.getText().toString().length()>0) {
                    new TRYLOGIN().execute();
                }
//                Intent in = new Intent(ManuLoginActivity.this, Business_Dashboard_Main.class);
//                startActivity(in);
//
//                overridePendingTransition(R.anim.anim_slide_in_left,
//                        R.anim.anim_slide_out_left);
            }
        });

    }

    private class TRYLOGIN extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(ManuLoginActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://sdltechserv.in/dgfeb/api/api/login");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", email.getText().toString());
                postDataParams.put("password", password.getText().toString());


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
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONObject(result);
                    Boolean response = jsonObject.getBoolean("responce");
                    if(response.booleanValue() ==false)
                    {
                        Toast.makeText(ManuLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }else {
//                        sessionManager.serverEmailLogin(jsonObject.getJSONObject("data").getInt("id"));
//                        User_ID = jsonObject.getJSONObject("data").getInt("id");
                        Intent intent = new Intent(ManuLoginActivity.this , Business_Dashboard_Main.class);
                        startActivity(intent);
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
