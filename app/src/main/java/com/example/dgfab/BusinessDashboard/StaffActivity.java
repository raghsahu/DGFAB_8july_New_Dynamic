package com.example.dgfab.BusinessDashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dgfab.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class StaffActivity extends AppCompatActivity {
    ImageView staffimg;
    private int RESULT_LOAD_IMAGE = 101;
    private int RESULT_PICK_IMAGE = 141;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS =101 ;
    EditText Staffname,Staffemail,Staffuserid,Staffpassword,Designationstaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        staffimg = findViewById(R.id.staffimg);
        Staffname = findViewById(R.id.staffname);
        Staffemail = findViewById(R.id.staffemail);
        Staffuserid = findViewById(R.id.staffid);
        Staffpassword = findViewById(R.id.staffpassword);
        Designationstaff = findViewById(R.id.staffdes);
            checkPermissions();
    }

    private Boolean checkPermissions() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        //  int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        Log.e("FirstPermissionResult" , ""+FirstPermissionResult);
        Log.e("SecondPermissionResult" , ""+SecondPermissionResult);
        Log.e("ThirdPermissionResult" , ""+ThirdPermissionResult);
        FirstPermissionResult =0;
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED ;
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
}
