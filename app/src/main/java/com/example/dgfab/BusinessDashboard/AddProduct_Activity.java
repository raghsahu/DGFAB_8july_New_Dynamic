package com.example.dgfab.BusinessDashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.AddNewsAdapter;
import com.example.dgfab.AllParsings.AddSubService;
import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.MyServices;
import com.example.dgfab.AllParsings.MyServicesData;
import com.example.dgfab.AllParsings.MySubservices;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.example.dgfab.Utils.Utilities;
import com.example.dgfab.Utilssss.CameraUtils__TRD;
import com.example.dgfab.Utilssss.CommonUtils_TRD;
import com.example.dgfab.Utilssss.Utility;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
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
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Looper.getMainLooper;
import static com.example.dgfab.Adapter.AddNewsAdapter.Myproductsdescrip;
import static com.example.dgfab.Adapter.AddNewsAdapter.Myproducttiles;

public class AddProduct_Activity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    RecyclerView addfieldrec;
    Spinner spicatpro, spinsubcat;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE_TRD = 200;
    int Gallery_view = 2;
    Handler someHandler;
    public File pdfFile;
    private static String imageStoragePath_TRD;
    Button nxpx;
    ArrayList<String> get_services_data = new ArrayList<>();
    ArrayList<String> get_services_data_id = new ArrayList<>();
    ArrayList<String> get_sub_services_data = new ArrayList<>();
    ArrayList<String> get_sub_services_data_id = new ArrayList<>();
    ArrayAdapter<String> service_adapter;
    List<MyServicesData> add_services;
    public static final int BITMAP_SAMPLE_SIZE_TRD = 8;
    ArrayAdapter<String> service_sub_adapter;
    private TextView addmore_service ,addmore_sub_service;
    AddNewsAdapter addNewsAdapter;
    EditText product_name , description,mrp,price,qty,stock;
    List<AddNews> addNewsList = new ArrayList<AddNews>();
    SessionManager sessionManager;
    Button next,addnv;
    public static final int MEDIA_TYPE_IMAGE_TRD = 1;
    ImageView productimage;
    public static final String IMAGE_EXTENSION_TRD = "jpg";
    private ProgressDialog dialog;
    public static final String KEY_IMAGE_STORAGE_PATH_TRD = "image_path";
    private Bundle savedInstanceState;
    String cat_id,sub_cat_id;
    String productstitles ;
    String productsdescrip ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_);
        spicatpro = findViewById(R.id.spicatpro);
        spinsubcat = findViewById(R.id.spinsubcat);
        addfieldrec = findViewById(R.id.addfieldrec);
        product_name = findViewById(R.id.product_name);
        productimage = findViewById(R.id.productimage);
        description = findViewById(R.id.description);
        mrp = findViewById(R.id.mrp);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        stock = findViewById(R.id.stock);
        addmore_sub_service = findViewById(R.id.addmore_sub_service);
        sessionManager = new SessionManager(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        addfieldrec.setLayoutManager(llm);
        GETAllServiceS();
        nxpx = findViewById(R.id.nxpx);

        someHandler = new Handler(getMainLooper());
//        TimeTaken =0;
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//
                someHandler.postDelayed(this, 1000);
            }
        }, 10);
//        radio_grp.findViewById(R.id.ans1);
//        radio_grp.findViewById(R.id.ans2);
//        radio_grp.findViewById(R.id.ans3);
//        radio_grp.findViewById(R.id.ans3);


//        Runnable updateTimerThread = new Runnable() {
//
//            public void run() {
//
//                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
////                timeInMilliseconds = System.uptimeMillis() - startTime;
//
//                updatedTime = timeSwapBuff + timeInMilliseconds;
//
//
//                    int secs = (int) (updatedTime / 1000);
//                    int mins = secs / 60;
//                    secs = secs % 60;
//                    int milliseconds = (int) (updatedTime % 1000);
//                    clock.setText("" + mins + ":"
//                            +secs/* + ":"
//                    + String.format("%03d", milliseconds)*/);
//
//                    TimeTaken = secs;
////                        long start = System.currentTimeMillis();
////                        long runTime = System.currentTimeMillis() - start;
////                        Log.e("System time" , ""+runTime);
////                            clock.setText("" + mins + ":"
////                                    + String.format("%02d", secs)/* + ":"
////                    + String.format("%03d", milliseconds)*/);
//                    //    TimeTaken = String.format("%02d, secs);
//                    //    Log.e("updatedTime is" , ""+updatedTime);
//
////                        if(secs ==59)
////                        {
////                            TimeTaken = secs +1;
////                            Log.d("before 59" , "time is "+TimeTaken);
////                        }else {
////
////                            TimeTaken = TimeTaken+secs;
////                            Log.d("after 59" , "time is "+TimeTaken);
////                        }
//
//
//                    //  Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
////                            questionsJJavaHashMap.remove(queposition);
////                            questionsJJavaHashMap.put(queposition, new Questions_jJava("Not answered", TimeTaken));
//                    //      Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
//
//
//
//
//
//
//
//                // Toast.makeText(getActivity(), ""+String.format("%02d", secs), Toast.LENGTH_SHORT).show();
//                customHandler.postDelayed(this, 0);
//            }
//
//        };

        nxpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product_name.getText().toString().length() !=0 &&description.getText().toString().length() !=0&&mrp.getText().toString().length() !=0 &&
                price.getText().toString().length() !=0 && qty.getText().toString().length() !=0&&stock.getText().toString().length() !=0)
                {
                    for(int p =0;p<get_services_data.size();p++)
                    {
                        if(get_services_data.get(p).equals(spicatpro.getSelectedItem().toString()))
                        {
                            cat_id = get_services_data_id.get(p).toString();
                        }
                    }
                    for(int px=0;px<get_sub_services_data.size();px++)
                    {
                        if(get_sub_services_data.get(px).equals(spinsubcat.getSelectedItem().toString())){
                            try {
                                sub_cat_id = get_sub_services_data_id.get(px);
                            }catch (Exception e)
                            {
                                Log.e("no" , "subservice id found");
                                sub_cat_id =String.valueOf(0);
                                e.printStackTrace();
                            }
                        }
                    }
                    for(int i=0;i<Myproducttiles.size();i++)
                    {
                        int size = Myproducttiles.size();
                        String titts = Myproducttiles.get(i);
                        String destits = Myproductsdescrip.get(i);
                        Log.e("My pros" , ""+titts);
                        Log.e("My pros" , ""+destits);
                        if(i!=0) {
//                            if(i==Myproducttiles.size()-1)
//                            {
//                              //  productstitles.concat(titts.concat("]"));
//                                productstitles = productstitles ;
//                             //   productsdescrip.concat(destits.concat("]"));
//                                productsdescrip = productsdescrip +;
//                            }

                             {
                               // productstitles.concat(",".concat(titts));
                                productstitles = productstitles +","+ titts;
                            //    productsdescrip.concat(",".concat(destits));
                                productsdescrip = productsdescrip +","+destits;
                            }

                        }else if(i==0){
                            productstitles = titts;
                            //productsdescrip.concat(destits);
                            productsdescrip =  destits;

                        }
                    }
                    new ADDonlyProducttest(productstitles,productsdescrip ,mrp.getText().toString() , price.getText().toString(),stock.getText().toString() ,qty.getText().toString()).execute();
                  //  new  PostProduct(AddProduct_Activity.this).execute();
                 //    FirsttextProduct();
                }else {
                    Toast.makeText(AddProduct_Activity.this, "Please fill a  fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        addmore_service = findViewById(R.id.addmore_service);
        addnv = findViewById(R.id.addnv);
        addnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(addNewsList.size() >0) {
                     //   addNewsList.add(addNewsList.size(),new AddNews(new EditText(AddProduct_Activity.this), new EditText(AddProduct_Activity.this), new Button(AddProduct_Activity.this)));

                   //     addNewsAdapter.notifyDataSetChanged();
                        addfieldrec.swapAdapter(addNewsAdapter, false);
                        addNewsAdapter.notifyItemRemoved(0);
//                        addfieldrec.setAdapter(addNewsAdapter);
                    }else {
                        Toast.makeText(AddProduct_Activity.this, "list i", Toast.LENGTH_SHORT).show();
                      //  addNewsList.add(new AddNews(new EditText(AddProduct_Activity.this), new EditText(AddProduct_Activity.this), new Button(AddProduct_Activity.this)));
                        addNewsAdapter = new AddNewsAdapter(AddProduct_Activity.this, addNewsList);
                        addfieldrec.setAdapter(addNewsAdapter);
                        addNewsAdapter.notifyItemRemoved(0);
                    }
                  //  addNewsAdapter = new AddNewsAdapter(AddProduct_Activity.this, addNewsList);
                  //  addfieldrec.setAdapter(addNewsAdapter);

                }catch (Exception e)
                {
                    Toast.makeText(AddProduct_Activity.this, "list i", Toast.LENGTH_SHORT).show();
                  //  addNewsList.add(new AddNews(new EditText(AddProduct_Activity.this), new EditText(AddProduct_Activity.this), new Button(AddProduct_Activity.this)));
                    addNewsAdapter = new AddNewsAdapter(AddProduct_Activity.this, addNewsList);
                    addfieldrec.setAdapter(addNewsAdapter);
                    e.printStackTrace();
                }

            }
        });
        addmore_sub_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddProduct_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.addnewsubservice);

                EditText et_add_service = (EditText) dialog.findViewById(R.id.text_dialog);
                String add_new_service = et_add_service.getText().toString();

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        if (!et_add_service.getText().toString().equals("")) {
                            Log.e("et_add_ser", "" + et_add_service.getText().toString());
                       //     Add_New_Service(et_add_service.getText().toString());
                            for(int i=0;i<add_services.size();i++) {

                                if(add_services.get(i).getService().equals(spicatpro.getSelectedItem())) {
                                    Add_New_Sub_Service(add_services.get(i).getId(),et_add_service.getText().toString() ,i);
                                }
                            }

                        } else {
                            et_add_service.setError("This field can not be empty");
                            et_add_service.requestFocus();
                        }

                    }
                });

                dialog.show();
            }
        });
        spicatpro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                GETAllSubServiceS(get_services_data_id.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addmore_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddProduct_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_service_layout);

                EditText et_add_service = (EditText) dialog.findViewById(R.id.text_dialog);
                String add_new_service = et_add_service.getText().toString();

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        if (!et_add_service.getText().toString().equals("")) {
                            Log.e("et_add_ser", "" + et_add_service.getText().toString());
                            Add_New_Service(et_add_service.getText().toString());
                        } else {
                            et_add_service.setError("This field can not be empty");
                            et_add_service.requestFocus();
                        }

                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       /* if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);

            else */
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE_TRD) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                //   Toast.makeText(Single_user_act_TRD.this, " your pan no is "+pan, Toast.LENGTH_SHORT).show();
                // CameraUtils__TRD.refreshGallery(getApplicationContext(), imageStoragePath);
                //  Toast.makeText(this, "called once", Toast.LENGTH_SHORT).show();
                previewCapturedImage();
                captureImage();

            } else if (resultCode == RESULT_CANCELED) {

                //  selectImage();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to upload image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == Gallery_view && data != null) {
            try {
                Toast.makeText(this, "Please upload from SD card only", Toast.LENGTH_SHORT).show();
                Uri pickedImage = data.getData();
                pdfFile = null;
                String[] filePath = {MediaStore.Images.Media.DATA};
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                    //     getRealPathFromURI(Single_user_act_TRD.this , pickedImage);


//                  pickedImage =   FileProvider.getUriForFile(Single_user_act_TRD.this, BuildConfig.APPLICATION_ID + ".provider",pdfFile);
                    //   pickedImage =   FileProvider.getUriForFile(Single_user_act_TRD.this, getApplicationContext().getPackageName() + ".provider",pdfFile);

                    Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                    Log.e("Gallery image path is:", "" + imagePath);
                    imageStoragePath_TRD = imagePath;
                    pdfFile = new File(imagePath);

                    cursor.close();
                    if (pdfFile != null) {

                        if (pdfFile.exists()) {
                            Log.e("PDF ", "" + pdfFile.exists());
                            //   new ImageUploadPDF(pdfFile).execute();
                            imageStoragePath_TRD = imagePath;

                        }
                        Toast.makeText(this, "Please upload from SD card only", Toast.LENGTH_SHORT).show();
                        //   new ImageCompression().execute(imagePath);
                        //   select_registrationplate.setImageURI(Uri.fromFile(imgFile));
                        //  show(imgFile);
                        // new ImageUploadTask(new File(imagePath)).execute();
                        //   Toast.makeText(ReportSummary.this, "data:=" + imgFile, Toast.LENGTH_LONG).show();
                    }

                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(getApplicationContext(), pickedImage)) {
//                        if (isExternalStorageDocument(uri)) {
//                            final String docId = DocumentsContract.getDocumentId(uri);
//                            final String[] split = docId.split(":");
//                            return Environment.getExternalStorageDirectory() + "/" + split[1];
//                        }
//                    }
                    File extStore = Environment.getExternalStorageDirectory();
//                    File myFile = new File(extStore.getAbsolutePath() + "/book1/page2.html");
                    String pdf_file_path;
                    Uri uri = data.getData();
                    pdfFile = new File(uri.getPath());//create path from uri
                    final String[] split = pdfFile.getPath().split(":");//split the path.
                    pdf_file_path = split[1];
                    Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                    Log.e("Gallery image path is:", "" + imagePath);
                    imageStoragePath_TRD = imagePath;

                    pdfFile = new File(extStore.getAbsolutePath(), pdf_file_path);

                    cursor.close();
                    if (pdfFile != null) {

                        if (pdfFile.exists()) {
                            Log.e("PDF ", "" + pdfFile.exists());
                            //   new ImageUploadPDF(pdfFile).execute();


//                            new ImageUploadTask(pdfFile).execute();
                        }
                        Toast.makeText(this, "Please upload from SD card only", Toast.LENGTH_SHORT).show();
                    }


                } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                    Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                    Log.e("Gallery image path is:", "" + imagePath);
                    imageStoragePath_TRD = imagePath;
                    pdfFile = new File(imagePath);
                    if (pdfFile != null) {

                        if (pdfFile.exists()) {
                            Log.e("PDF ", "" + pdfFile.exists());


                            //   new ImageUploadPDF(pdfFile).execute();
//                            new ImageUploadTask(pdfFile).execute();
                        }

                    }
                    Toast.makeText(this, "SD Card"+pdfFile.getName(), Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                Toast.makeText(this, "Not getting file from phone please upload valid .pdf", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void selectImage() {

        final CharSequence[] items = {"Upload", "Upload from Gallery", "Back"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct_Activity.this);
        builder.setTitle("Upload!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(AddProduct_Activity.this);

                if (items[item].equals("Upload")) {
                    if (result)
                        if (CameraUtils__TRD.checkPermissions(AddProduct_Activity.this)) {
                            //    Toast.makeText(Single_user_act_TRD.this, "Capture image called", Toast.LENGTH_SHORT).show();
                            captureImage();
                            //     Toast.makeText(Single_user_act_TRD.this, "we got result back", Toast.LENGTH_SHORT).show();

                            restoreFromBundle(savedInstanceState);
                        } else {
                            requestCameraPermission(MEDIA_TYPE_IMAGE_TRD);
                        }
                    // cameraIntent();

                } else if (items[item].equals("Back")) {

                    dialog.dismiss();
                } else if (items[item].equals("Upload from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), Gallery_view);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);

    }

    private void captureImage() {

        int ask_again = 0;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = CameraUtils__TRD.getOutputMediaFile_TRD(MEDIA_TYPE_IMAGE_TRD);
        if (file != null) {
            imageStoragePath_TRD = file.getAbsolutePath();
            //  Toast.makeText(this, " Uploading", Toast.LENGTH_SHORT).show();
//            CameraUtils__TRD.refreshGallery(getApplicationContext(), imageStoragePath);
            // start the image capture Intent

//                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            //   Toast.makeText(getApplicationContext() ,"Step 2",Toast.LENGTH_LONG).show();
        }
        if (file == null) {
            Toast.makeText(this, "Not getting scan images please try again", Toast.LENGTH_SHORT).show();
        }
        Uri fileUri = CameraUtils__TRD.getOutputMediaFileUri_TRD(AddProduct_Activity.this, file);
        //    Toast.makeText(this, ""+fileUri, Toast.LENGTH_SHORT).show();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE_TRD);
    }

    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH_TRD)) {
                imageStoragePath_TRD = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH_TRD);
                if (!TextUtils.isEmpty(imageStoragePath_TRD)) {
                    if (imageStoragePath_TRD.substring(imageStoragePath_TRD.lastIndexOf(".")).equals("." + IMAGE_EXTENSION_TRD)) {
                        previewCapturedImage();
                    }
                }
            }
        }
    }

    private void previewCapturedImage() {

        Bitmap bitmap = CameraUtils__TRD.optimizeBitmap_TRD(BITMAP_SAMPLE_SIZE_TRD, imageStoragePath_TRD);
        if (imageStoragePath_TRD != null) {
            productimage.setImageResource(R.drawable.docfound);
            //   Toast.makeText(this, "Uploading", Toast.LENGTH_SHORT).show();
            new ImageCompression().execute(imageStoragePath_TRD);
        }

    }

    private void requestCameraPermission(int mediaTypeImageTrd) {

        Dexter.withActivity(this)
                .withPermissions(android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (mediaTypeImageTrd == MEDIA_TYPE_IMAGE_TRD) {
                                // capture picture
                                captureImage();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void showPermissionsAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils__TRD.openSettings(AddProduct_Activity.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void Add_New_Sub_Service(String id, String name, int i) {


        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);

        Log.e("et_add", "" + name);
        Call<AddSubService> get_aboutCall = AbloutApi.ADD_SUB_SERVICE_CALL(id, name);


        get_aboutCall.enqueue(new Callback<AddSubService>() {
            @Override
            public void onResponse(Call<AddSubService> call, Response<AddSubService> response) {
                progressDialog.dismiss();

                Log.e("Add_new_service", "" + response.toString());
                Log.e("Add_service_res_msg", "" + response.body().getResponce());
                Log.e("Add_service_res_msg_id", "" + response.body().getMassage().getId());
                Log.e("Add_service_res_suc", "" + response.isSuccessful());

                if (response.isSuccessful()) {

                    get_sub_services_data.clear();
                    service_sub_adapter.notifyDataSetChanged();

                    GETAllSubServiceS(String.valueOf(i));

                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<AddSubService> call, Throwable t) {

                Log.e("failer", "" + t.getMessage());
                Toast.makeText(AddProduct_Activity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GETAllSubServiceS(String selectedItem) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);

        Log.e("et_add", "" + selectedItem);
        Call<MySubservices> get_aboutCall = AbloutApi.MY_SUBSERVICES_CALL(selectedItem);


        get_aboutCall.enqueue(new Callback<MySubservices>() {
            @Override
            public void onResponse(Call<MySubservices> call, Response<MySubservices> response) {
                progressDialog.dismiss();

                Log.e("Add_new_service", "" + response.toString());
                Log.e("Add_service_res_msg", "" + response.body().getResponce());
             //   Log.e("Add_service_res_msg_id", "" + response.body().getMassage().getId());
                Log.e("Add_service_res_suc", "" + response.isSuccessful());
               // get_sub_services_data.clear();
              //  service_sub_adapter.notifyDataSetChanged();

                if (response.isSuccessful()) {
                        try{
                            progressDialog.dismiss();
                            // Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                            //   SubTypestrings = new String[response.body().getData().size()];
//                Log.e("getact" , ""+response.body().getData().size());
                            for (int k = 0; k < response.body().getData().size(); k++) {
                                Log.e("getact msain", "" + response.body().getData().get(k).getId());
                                get_sub_services_data.add(response.body().getData().get(k).getName());
                                get_sub_services_data_id.add(response.body().getData().get(k).getId());

                                service_sub_adapter = new ArrayAdapter<String>(AddProduct_Activity.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

                                spinsubcat.setAdapter(service_sub_adapter);
                                progressDialog.dismiss();
                            }
                        }catch (Exception e)
                        {
                            get_sub_services_data.add("None Found");

                            service_sub_adapter = new ArrayAdapter<String>(AddProduct_Activity.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

                            spinsubcat.setAdapter(service_sub_adapter);
                            progressDialog.dismiss();

                            e.printStackTrace();
                        }


              //      GETAllServiceS();

                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MySubservices> call, Throwable t) {
                get_sub_services_data.add("None Found");

                service_sub_adapter = new ArrayAdapter<String>(AddProduct_Activity.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

                spinsubcat.setAdapter(service_sub_adapter);
                progressDialog.dismiss();
                Log.e("failer", "" + t.getMessage());
                Toast.makeText(AddProduct_Activity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //*******************************************************************************************
    private void Add_New_Service(String add_new_service) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);

        Log.e("et_add", "" + add_new_service);
        Call<Add_Services> get_aboutCall = AbloutApi.Add_Services_Call("3", add_new_service, "pp");


        get_aboutCall.enqueue(new Callback<Add_Services>() {
            @Override
            public void onResponse(Call<Add_Services> call, Response<Add_Services> response) {
                progressDialog.dismiss();

                Log.e("Add_new_service", "" + response.toString());
                Log.e("Add_service_res_msg", "" + response.body().getResponce());
                Log.e("Add_service_res_msg_id", "" + response.body().getMassage().getId());
                Log.e("Add_service_res_suc", "" + response.isSuccessful());

                if (response.isSuccessful()) {

                    get_services_data.clear();
                    service_adapter.notifyDataSetChanged();

                    GETAllServiceS();

                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Add_Services> call, Throwable t) {

                Log.e("failer", "" + t.getMessage());
                Toast.makeText(AddProduct_Activity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
//***************************************************************************************************************


    private void GETAllServiceS() {
        Log.e("logs at" , ""+sessionManager.getUS());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<MyServices> get_aboutCall = AbloutApi.MY_SERVICES_CALL(sessionManager.getUS());
        get_aboutCall.enqueue(new Callback<MyServices>() {
            @Override
            public void onResponse(Call<MyServices> call, Response<MyServices> response) {
                progressDialog.dismiss();
                add_services = response.body().getData();
                // Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                //   SubTypestrings = new String[response.body().getData().size()];
//                Log.e("getact" , ""+response.body().getData().size());
                for (int k = 0; k < response.body().getData().size(); k++) {
                    Log.e("getact msain", "" + response.body().getData().get(k).getTypeId());
                    get_services_data.add(response.body().getData().get(k).getService());
                    get_services_data_id.add(response.body().getData().get(k).getId());

                    //    get_services_data.add(new GET_Services_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId() ,response.body().getData().get(k).getService() ,response.body().getData().get(k).getImage(),response.body().getData().get(k).getStatus()));
                    // type_sub_user_data.add(new Type_Sub_User_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId(),response.body().getData().get(k).getSubtypename() ));
                    //SubTypestrings[k] = response.body().getData().get(k).getSubtypename();
                }
                // sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_expandable_list_item_1 , SubTypestrings));
                //   sub_main_type.setVisibility(View.VISIBLE);
                service_adapter = new ArrayAdapter<String>(AddProduct_Activity.this, android.R.layout.simple_spinner_dropdown_item, get_services_data);
                //   gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                //  serv_id.addItemDecoration(new DividerItemDecoration(Registration_Step_1.this, LinearLayoutManager.VERTICAL));
                // serv_id.setLayoutManager(gridLayoutManager);
                spicatpro.setAdapter(service_adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MyServices> call, Throwable t) {
                Toast.makeText(AddProduct_Activity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProduct_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class PostProduct extends AsyncTask<Void, Void, String> {


        String id;
        String result = "";
        File pic0, pic1, pic2, pic3, pic4;
        String urlpart;
        Context context;
        MultipartEntity multipartEntity;

        public PostProduct( Context applicationContext) {

            this.context = applicationContext;
        }


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Processing");

            dialog.setCancelable(true);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {


            try {


                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);
                entity.addPart("admin_id", new StringBody("" + sessionManager.getUS()));
                entity.addPart("id", new StringBody("" + id));
                entity.addPart("attachment", new FileBody(pic0));
////                        result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
                result = Utilities.postEntityAndFindJson("http://ihisaab.in/vertical_homes/Api/"+urlpart, entity);

//                    entity.addPart("return_copy_upload", new FileBody(pic0));
//                    result = Utilities.postEntityAndFindJson("http://ihisaab.com/ihisaabv2/Api/taxreturn", entity);
//                    c1=0;


                return result;


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

                Toast.makeText(context, "Documents Uploaded Successfully ", Toast.LENGTH_LONG).show();


                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(context, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
    }

    private class ImageCompression extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (strings.length == 0 || strings[0] == null)
                return null;

            return CommonUtils_TRD.compressImage_TRD(strings[0]);
        }

        protected void onPostExecute(String imagePath) {
            // imagePath is path of new compressed image.
//            mivImage.setImageBitmap(BitmapFactory.decodeFile(new File(imagePath).getAbsolutePath()));
            try {


                pdfFile = new File(imagePath);
                //Toast.makeText(Single_user_act_TRD.this, ""+imgFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                if (pdfFile.exists()) {
                    Toast.makeText(AddProduct_Activity.this, "successfully taken", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddProduct_Activity.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                }
            } catch (NullPointerException e) {
                Toast.makeText(AddProduct_Activity.this, "Having some problem with image ,please try again registering", Toast.LENGTH_SHORT).show();
            }
        }
    }


        //*******************************************************************************************
//        private void FirsttextProduct() {
//            progressDialog = new ProgressDialog(this);
//            progressDialog.setMax(1000);
//            progressDialog.setTitle("Creating New Product");
//            progressDialog.setCancelable(false);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.show();
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(100, TimeUnit.SECONDS)
//                    .readTimeout(100, TimeUnit.SECONDS).build();
//            Retrofit RetroLogin = new Retrofit.Builder()
//                    .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            Api AbloutApi = RetroLogin.create(Api.class);
//
//          //  Log.e("et_add", "" + add_new_service);
//            for(int i=0;i<Myproducttiles.size();i++)
//            {
//                if(i!=0) {
//                     if(i==Myproducttiles.size()-1)
//                    {
//                        productstitles.concat(Myproducttiles.get(i).concat("]"));
//                        productsdescrip.concat(Myproductsdescrip.get(i).concat("]"));
//                    }else {
//                         productstitles.concat(",".concat(Myproducttiles.get(i)));
//                         productsdescrip.concat(",".concat(Myproductsdescrip.get(i)));
//                     }
//
//                }else if(i==0){
//                    productstitles.concat(Myproducttiles.get(i));
//                    productsdescrip.concat(Myproductsdescrip.get(i));
//
//                }
//            }
//            Call<ProductReply> get_aboutCall = AbloutApi.PRODUCT_REPLY_CALL(cat_id,sub_cat_id,product_name.getText().toString() ,description.getText().toString(),mrp.getText().toString(),price.getText().toString(),stock.getText().toString(),qty.getText().toString(),productstitles,productsdescrip);
//
//
//            get_aboutCall.enqueue(new Callback<ProductReply>() {
//                @Override
//                public void onResponse(Call<ProductReply> call, Response<ProductReply> response) {
//                    progressDialog.dismiss();
//
//                    Log.e("Add_new_service", "" + response.toString());
//                    Log.e("Add_service_res_msg", "" + response.body().getResponce());
//                    Log.e("Add_service_res_suc", "" + response.isSuccessful());
//
//                    if (response.isSuccessful()) {
//
//                      //  get_services_data.clear();
//                        //service_adapter.notifyDataSetChanged();
//
//                     //   GETAllServiceS();
//
//                    }
//
//                    progressDialog.dismiss();
//                }
//
//                @Override
//                public void onFailure(Call<ProductReply> call, Throwable t) {
//
//                    Log.e("failer", "" + t.getMessage());
//                    Toast.makeText(AddProduct_Activity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(AddProduct_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }

    private class ADDonlyProducttest extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String productstitles,productsdescrip ,mrp,price,stock,qty;

        public ADDonlyProducttest(String productstitles, String productsdescrip, String mrp, String price, String stock, String qty) {
            this.productstitles = productstitles;
            this.productsdescrip =productsdescrip;
            this.mrp =mrp;
            this.price =price;
            this.stock =stock;
            this.qty =qty;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(AddProduct_Activity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/addProduct");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("category",cat_id);
                postDataParams.put("user_id",sessionManager.getUS());
                postDataParams.put("subcategory", sub_cat_id);
                postDataParams.put("product_name", product_name.getText().toString());
                postDataParams.put("description", description.getText().toString());
                postDataParams.put("mrp", mrp);
                postDataParams.put("price", price);
                postDataParams.put("qty", qty);
                postDataParams.put("stock", stock);
                postDataParams.put("title", productstitles);
                postDataParams.put("discription", productsdescrip);
                postDataParams.put("othqty", productsdescrip);


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
                    String data = jsonObject.getString("data");
                    if(response)
                    {
                        Toast.makeText(AddProduct_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                        if(pdfFile.exists())
                        {
                            Toast.makeText(AddProduct_Activity.this, "yeah", Toast.LENGTH_SHORT).show();
                            new OnlyImageupload(String.valueOf(sessionManager.getUS()),data).execute();
                        }else {
                            finish();
                            Intent intent = new Intent(AddProduct_Activity.this , AddProduct_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
//                    if(response.booleanValue() ==false)
//                    {
//                        Toast.makeText(ManuLoginActivity.this, "Login failed,Contact to admin", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(ManuLoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//                        sessionManager.serverEmailLogin(jsonObject.getJSONObject("data").getInt("id"));
//                        sessionManager.serverEmailLogin(String.valueOf(jsonObject.getJSONObject("data").getInt("id")) , jsonObject.getJSONObject("data").getString("user_type"));
//                        User_ID = jsonObject.getJSONObject("data").getString("id");
//                        Shared_Preference.setId(ManuLoginActivity.this,User_ID);
//
//                        Intent intent = new Intent(ManuLoginActivity.this , Business_Dashboard_Main.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.anim_slide_in_left,
//                                R.anim.anim_slide_out_left);
//                    }
                }catch (Exception e)
                {
                    Intent intent = new Intent(AddProduct_Activity.this , AddProduct_Activity.class);
                    startActivity(intent);
                    finish();
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

    private class OnlyImageupload extends AsyncTask<Void, Void, String> {


        String id;
        String result = "";
        File pic0, pic1, pic2, pic3, pic4;
        String urlpart;
        Context context;
        String Productid;
        MultipartEntity multipartEntity;

        public OnlyImageupload(String id, String data) {
            this.Productid = data;

            this.id = id;
        }


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AddProduct_Activity.this);
            dialog.setMessage("Processing");

            dialog.setCancelable(true);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {


            try {


                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);
                entity.addPart("product_id", new StringBody("" + Productid));
                entity.addPart("image", new FileBody(pdfFile));
                        result = Utilities.postEntityAndFindJson("https://neareststore.in/api/api/updateProduct", entity);
    //            result = Utilities.postEntityAndFindJson("", entity);

//                    entity.addPart("return_copy_upload", new FileBody(pic0));
//                    result = Utilities.postEntityAndFindJson("http://ihisaab.com/ihisaabv2/Api/taxreturn", entity);
//                    c1=0;


                return result;


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
                finish();
                Intent intent = new Intent(AddProduct_Activity.this , AddProduct_Activity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AddProduct_Activity.this, "Documents Uploaded Successfully ", Toast.LENGTH_LONG).show();


                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(AddProduct_Activity.this, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
    }
//***************************************************************************************************************




}


