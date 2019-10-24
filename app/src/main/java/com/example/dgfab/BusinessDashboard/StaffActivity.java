package com.example.dgfab.BusinessDashboard;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dgfab.Adapter.MyStffAdapter;
import com.example.dgfab.Java_Adapter_Files.MyStaffs;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.example.dgfab.Utils.Utilities;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class StaffActivity extends AppCompatActivity {
    ImageView staffimg;
    private int RESULT_LOAD_IMAGE = 101;
    String Mainpath;
    android.app.AlertDialog.Builder builder;
    Button addnvdes;
    RecyclerView staffrec;
    private int RESULT_PICK_IMAGE = 141;
    File Staffprofile;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS =101 ;
    EditText Staffname, Staffemail, Staffuserid, Staffpassword;
    Spinner Designationstaff;
    Button Createstaff;
    ArrayList<String> DesArrayList = new ArrayList<>();
    private ProgressDialog dialog;
    MyStffAdapter myStffAdapter;
    List<MyStaffs> myStaffsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        staffimg = findViewById(R.id.staffimg);
        Staffname = findViewById(R.id.staffname);
        staffrec = findViewById(R.id.staffrec);
        Staffemail = findViewById(R.id.staffemail);
        Staffuserid = findViewById(R.id.staffid);
        Staffpassword = findViewById(R.id.staffpassword);
        addnvdes = findViewById(R.id.addnvdes);
        Designationstaff = findViewById(R.id.staffdes);
        Createstaff = findViewById(R.id.add_staff);
        Log.e("id is", "" + new SessionManager(this).getUS());

        new GETAllStaffDesignation(new SessionManager(this).getUS()).execute();

        new GetAllMyStaff(new SessionManager(StaffActivity.this).getUS()).execute();

        checkPermissions();


        addnvdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(StaffActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.addnewsubservice);

                EditText et_add_service = (EditText) dialog.findViewById(R.id.text_dialog);

                et_add_service.setHint("Add New Designation");
                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        if (!et_add_service.getText().toString().equals("")) {
                            Log.e("et_add_ser", "" + et_add_service.getText().toString());
                            //     Add_New_Service(et_add_service.getText().toString());
                            String add_new_service = et_add_service.getText().toString();
                            new POStAllDesignation(add_new_service).execute();
                        } else {
                            et_add_service.setError("This field can not be empty");
                            et_add_service.requestFocus();
                        }

                    }
                });

                dialog.show();
            }
        });
        staffimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissions())
                {
                    captureit();
                }
            }
        });
        Createstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(StaffActivity.this, "ok ", Toast.LENGTH_SHORT).show();

                        if (Mainpath !=null) {
                            new CreatedStaffApi(Staffuserid.getText().toString(), Staffname.getText().toString(), Staffemail.getText().toString(), Staffpassword.getText().toString(), Designationstaff.getSelectedItemPosition() + 1).execute();
                        }

//                }
            }
        });
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


    private void captureit() {
        new AlertDialog.Builder(StaffActivity.this)
                .setMessage("From which you want to upload?")
                .setPositiveButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(cameraIntent, RESULT_PICK_IMAGE);
                        Toast.makeText(StaffActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Take it from gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                }).setNeutralButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StaffActivity.this, "back pressed", Toast.LENGTH_SHORT).show();
            }
        })
                .create()
                .show();
    }

    private Boolean checkPermissions() {
        int permissionCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStorage1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionPhone = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCamara != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionStorage1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionPhone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
//        int permissionCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int permissionStorage1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
//        int permissionPhone = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
//
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (permissionCamara != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
//        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (permissionStorage1 != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//        if (permissionPhone != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }
//        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == RESULT_PICK_IMAGE && resultCode == Activity.RESULT_OK && null != resultData) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("We go somethihbygf", "Uri: " + resultData.getData());
                Toast.makeText(this, "is " + resultData.getData(), Toast.LENGTH_SHORT).show();
                //   showImage(uri,resultData);
               // onCaptureImageResult(resultData);

            }

        }
        Toast.makeText(this, "" + resultData, Toast.LENGTH_SHORT).show();
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != resultData) {
            Uri selectedImage = resultData.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("We go somethihbygf", "Uri: " + resultData.getData());
            Staffprofile  = new File(picturePath);
            staffimg.setImageResource(R.drawable.docfound);
            Mainpath = picturePath;
//
//           else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }
            Toast.makeText(this, "is " + resultData.getData(), Toast.LENGTH_SHORT).show();
            cursor.close();
            // ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

    }

    private class GETAllStaffDesignation extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        int us;

        public GETAllStaffDesignation(int us) {
            this.us = us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(StaffActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://neareststore.in/api/api/getdesignation");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", String.valueOf(us));


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


                Log.e("PostRegistration at get", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        DesArrayList.add(jsonArray.getJSONObject(i).getString("designation").toUpperCase().toString());
                    }
                    Designationstaff.setAdapter(new ArrayAdapter<String>(StaffActivity.this, android.R.layout.simple_list_item_1, DesArrayList));
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

    private class GetAllMyStaff extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        int us;

        public GetAllMyStaff(int us) {
            this.us = us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(StaffActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://neareststore.in/api/api/getstaff ");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", String.valueOf(us));


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


                Log.e("PostRegistration", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        myStaffsList.add(new MyStaffs(jsonArray.getJSONObject(i).getString("id"), jsonArray.getJSONObject(i).getString("user_id"), jsonArray.getJSONObject(i).getString("staff_id"),
                                jsonArray.getJSONObject(i).getString("name"), jsonArray.getJSONObject(i).getString("email"), jsonArray.getJSONObject(i).getString("password"), jsonArray.getJSONObject(i).getString("designation"),
                                jsonArray.getJSONObject(i).getString("image"), jsonArray.getJSONObject(i).getString("status")));
                    }

                    LinearLayoutManager llm = new LinearLayoutManager(StaffActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    staffrec.setLayoutManager(llm);
                    myStffAdapter = new MyStffAdapter(StaffActivity.this, myStaffsList);
                    staffrec.setAdapter(myStffAdapter);
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

    private class CreatedStaffApi extends AsyncTask<Void, Void, String> {
        File trandmark_cerFile, copyright_cerFile, others_cerFile, gate_photo_file, gate_sign_file;
        String stafid;
        String staffname;
        String email;
        String password;
        int destination;
        String result = "";

        public CreatedStaffApi(String stafid, String staffname, String email, String password, int destination) {
            this.stafid = stafid;
            this.staffname = staffname;
            this.email = email;
            this.password = password;
            this.destination = destination;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(StaffActivity.this);
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... Void) {
            try {

                MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);


                entity.addPart("staff_id", new StringBody("" + stafid));
                entity.addPart("user_id", new StringBody("" + new SessionManager(StaffActivity.this).getUS()));
                entity.addPart("name", new StringBody("" + staffname));
                entity.addPart("email", new StringBody("" + email));
                entity.addPart("password", new StringBody("" + password));
                entity.addPart("designation", new StringBody("" + destination));
//                entity.addPart("sub_type", new StringBody(""+concatService));
//                entity.addPart("trandmark_cer", new FileBody(trandmark_cerFile));
//                entity.addPart("copyright_cer", new FileBody(copyright_cerFile));
//                entity.addPart("others_cer", new FileBody(others_cerFile));
//                entity.addPart("gst_cer", new FileBody(gst_cerFile));
                entity.addPart("image", new FileBody(Staffprofile));
//                    result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
//                 //   result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
                result = Utilities.postEntityAndFindJson("https://neareststore.in/api/api/addstaff", entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            String result1 = result;
            if (result1 != null) {

                dialog.dismiss();
                Log.e("result1", result1);

                Toast.makeText(StaffActivity.this, " Successfully Registered", Toast.LENGTH_LONG).show();
                new GetAllMyStaff(new SessionManager(StaffActivity.this).getUS()).execute();
                //  Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(StaffActivity.this, "Staff add Success", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(StaffActivity.this, StaffActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(StaffActivity.this , S.class);
//                startActivity(intent);
//                finish();

            }

        }
    }

    private class POStAllDesignation extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        int us;
        String add_new_service;

        public POStAllDesignation(String add_new_service) {
            this.add_new_service = add_new_service;
        }


        protected void onPreExecute() {
            dialog = new ProgressDialog(StaffActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://neareststore.in/api/api/adddesignation");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", new SessionManager(StaffActivity.this).getUS());
                postDataParams.put("designation", add_new_service);


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
//                DesArrayList.clear();

                Log.e("PostRegistration at get", result.toString());
                if (result != null) {
                    DesArrayList.clear();
                    new GETAllStaffDesignation(new SessionManager(StaffActivity.this).getUS()).execute();
                }

            }
        }
    }
}
