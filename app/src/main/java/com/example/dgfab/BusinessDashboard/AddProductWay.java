package com.example.dgfab.BusinessDashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Activity.Registration_Step_1;
import com.example.dgfab.Adapter.AddMorePayoptions;
import com.example.dgfab.Adapter.AddMoredetailesAdapter;
import com.example.dgfab.Adapter.AddMorekeysAdapter;
import com.example.dgfab.Adapter.AddNewsAdapter;
import com.example.dgfab.Adapter.SelectandProsAdapter;
import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Adapter.SelectndRemvDetailsAdapter;
import com.example.dgfab.Adapter.Service_Adapter;
import com.example.dgfab.AllParsings.AddSubService;
import com.example.dgfab.AllParsings.GET_Services;
import com.example.dgfab.AllParsings.GET_Services_Data;
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
import java.util.AbstractCollection;
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

import static com.example.dgfab.BusinessDashboard.AddProduct_Activity.BITMAP_SAMPLE_SIZE_TRD;
import static com.example.dgfab.BusinessDashboard.AddProduct_Activity.IMAGE_EXTENSION_TRD;
import static com.example.dgfab.BusinessDashboard.AddProduct_Activity.KEY_IMAGE_STORAGE_PATH_TRD;
import static com.example.dgfab.BusinessDashboard.AddProduct_Activity.MEDIA_TYPE_IMAGE_TRD;

public class AddProductWay extends AppCompatActivity {
    Button addmorekey, nxt, addmrdet, addmorepro, addmantype;
    RecyclerView newkey, addmoredet, addmorepay, showkeys, showmoredeatils, addepros, showpays;
    public static final int MEDIA_TYPE_IMAGE_TRD = 1;
    List<AddNews> addNewsList = new ArrayList<AddNews>();
    List<AddNews> AllMoredetailesList  = new ArrayList<>();
    List<AddNews> AllMorepayList  = new ArrayList<>();
    List<AddNews> SelectandRemoveList = new ArrayList<>();
    List<AddNews> SelectandRemovedetailsList = new ArrayList<>();
    List<AddNews> SelectandMainproList = new ArrayList<>();
    List<AddNews> SelectandRadioproList = new ArrayList<>();
    SelectandRemoveAdapter selectandRemoveAdapter;
    SelndRemradioAdapter selndRemradioAdapter;
    public static final String IMAGE_EXTENSION_TRD = "jpg";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE_TRD = 200;
    private static String imageStoragePath_TRD;
    LinearLayout productliimg;
    ImageView productimage;
    int Gallery_view = 2;
    AddMorekeysAdapter addMorekeysAdapter;
    SelectandProsAdapter selectandProsAdapter;
    AddMoredetailesAdapter addMoredetailsAdapter;
    AddMorePayoptions addMorePayoptions;
    List<AddNews> addnewkeyList = new ArrayList<>();
    private SelectndRemvDetailsAdapter selectndRemvDetailsAdapter;
    private GridLayoutManager gridLayoutManager;
    //+++++++++++++++++++open for more details adapter and more keywords++++++++++++++++++++++++++++++++++++++++++++
    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //+++++++++++++++++++++++++++++++++remove list intent+++++++++++++++++++++++++++++++++++++++++++
            String removeid = intent.getStringExtra("removekey");
            try {
                if (removeid.isEmpty()) ;
            } catch (Exception e) {
                removeid = "ok";
                e.printStackTrace();
            }
            if (removeid.equals("ok")) {
                //  Toast.makeText(context, "Done nothing", Toast.LENGTH_SHORT).show();
            } else {
                if (removeid == "1") {
                    addmantype.setVisibility(View.VISIBLE);
                }
                if (removeid == "2") {
                    addmorekey.setVisibility(View.VISIBLE);
                }
                if (removeid == "3") {
                    addmrdet.setVisibility(View.VISIBLE);
                }
                if (removeid == "4") {
                    addmorepro.setVisibility(View.VISIBLE);
                }
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++
            // intent can contain anydata
            Log.d("sohail","onReceive called");
            //     Toast.makeText(context, "Broadcast received !", Toast.LENGTH_SHORT).show();
            if (intent != null) {
                String str = intent.getStringExtra("key");
                try {
                    if (str.isEmpty()) ;
                } catch (Exception e) {
                    Toast.makeText(context, "It is empty", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    str = "null";
                }
                if (!str.equals("null")) {
                    showkeys.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "Broadcast received KeysDetails!" + str, Toast.LENGTH_SHORT).show();
                    if (SelectandRemoveList.size() > 0) {
                        SelectandRemoveList.add(new AddNews(1, str));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                        showkeys.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showkeys.setLayoutManager(gridLayoutManager);
                        selectandRemoveAdapter = new SelectandRemoveAdapter(context, SelectandRemoveList);
                        showkeys.swapAdapter(selectandRemoveAdapter, false);
                        selectandRemoveAdapter.notifyItemInserted(0);
                    } else {
                        SelectandRemoveList.add(new AddNews(1, str));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                        showkeys.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showkeys.setLayoutManager(gridLayoutManager);
                        selectandRemoveAdapter = new SelectandRemoveAdapter(context, SelectandRemoveList);
                        showkeys.setAdapter(selectandRemoveAdapter);
                        selectandRemoveAdapter.notifyDataSetChanged();
                    }
                }
                //++++++++++++++++++++++++For more details+++++++++++++++++++++
                String dtr = intent.getStringExtra("Add_More_Details_Key");
                try {
                    if (dtr.isEmpty()) ;
                } catch (Exception e) {
                    dtr = "null2";
                    e.printStackTrace();
                }
                if (!dtr.equals("null2")) {

                    showmoredeatils.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "Broadcast received Other's Details !" + dtr, Toast.LENGTH_SHORT).show();
                    if (SelectandRemovedetailsList.size() > 0) {
                        SelectandRemovedetailsList.add(new AddNews(2, dtr));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                        showmoredeatils.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showmoredeatils.setLayoutManager(gridLayoutManager);
                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(context, SelectandRemovedetailsList);
                        showmoredeatils.swapAdapter(selectndRemvDetailsAdapter, false);
                        selectndRemvDetailsAdapter.notifyItemInserted(0);
                    } else {
                        SelectandRemovedetailsList.add(new AddNews(2, dtr));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                        showmoredeatils.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showmoredeatils.setLayoutManager(gridLayoutManager);

                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(context, SelectandRemovedetailsList);
                        showmoredeatils.setAdapter(selectndRemvDetailsAdapter);
                        selectndRemvDetailsAdapter.notifyDataSetChanged();
                    }
                } else {
                    String mainstr = intent.getStringExtra("Add_More_Details_Key");
                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(context, "it's null", Toast.LENGTH_SHORT).show();
                //+++++++++++++++++++++++++++++++++++++++
                // get all your data from intent and do what you want
                String maintits = intent.getStringExtra("maintits");
                String mainvalue = intent.getStringExtra("mainvalue");
                try {
                    if (maintits.isEmpty() && mainvalue.isEmpty()) ;
                } catch (Exception e) {
                    maintits = "null3";
                    mainvalue = "null3";
                    e.printStackTrace();
                }
                if (!maintits.equals("null3") && !mainvalue.equals("null3")) {

                    addepros.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "Broadcast received Other's Details !" + maintits, Toast.LENGTH_SHORT).show();
                    if (SelectandMainproList.size() > 0) {
                        SelectandMainproList.add(new AddNews(4, maintits, mainvalue));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        addepros.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        addepros.setLayoutManager(gridLayoutManager);
                        selectandProsAdapter = new SelectandProsAdapter(context, SelectandMainproList);
                        addepros.swapAdapter(selectandProsAdapter, false);
                        selectandProsAdapter.notifyItemInserted(0);
                    } else {
                        SelectandMainproList.add(new AddNews(4, maintits, mainvalue));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        addepros.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        addepros.setLayoutManager(gridLayoutManager);

                        selectandProsAdapter = new SelectandProsAdapter(context, SelectandMainproList);
                        addepros.setAdapter(selectandProsAdapter);
                        selectandProsAdapter.notifyDataSetChanged();
                    }
                } else {
                    // String mainstr = intent.getStringExtra("Add_More_Details_Key");
                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
                }
                String radiotit = intent.getStringExtra("radiotit");
                try {
                    if (radiotit.isEmpty() && radiotit.isEmpty()) ;
                } catch (Exception e) {
                    radiotit = "null4";
                    radiotit = "null4";
                    e.printStackTrace();
                }
                if (!radiotit.equals("null4")) {

                    showpays.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "Broadcast received Other's Details !" + maintits, Toast.LENGTH_SHORT).show();
                    if (SelectandRadioproList.size() > 0) {
                        SelectandRadioproList.add(new AddNews(5, radiotit));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        showpays.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showpays.setLayoutManager(gridLayoutManager);
                        selndRemradioAdapter = new SelndRemradioAdapter(context, SelectandRadioproList);
                        showpays.swapAdapter(selndRemradioAdapter, false);
                        selndRemradioAdapter.notifyItemInserted(0);

                    } else {
                        SelectandRadioproList.add(new AddNews(5, radiotit));
                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        showpays.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                        showpays.setLayoutManager(gridLayoutManager);

                        selndRemradioAdapter = new SelndRemradioAdapter(context, SelectandRadioproList);
                        showpays.setAdapter(selndRemradioAdapter);
                        selndRemradioAdapter.notifyDataSetChanged();
                    }
                } else {
                    // String mainstr = intent.getStringExtra("Add_More_Details_Key");
                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
                }
            }
            //  tv.setText("Broadcast received !");

        }
    };
    private RecyclerView mainrec;
    private AddNewsAdapter addNewsAdapter;
    ArrayList<String> get_sub_services_data = new ArrayList<>();
    ArrayList<String> get_sub_services_data_id = new ArrayList<>();
    ArrayAdapter<String> service_sub_adapter;
    Spinner spicatpro, spinsubcat;
    List<MyServicesData> add_services;
    ArrayList<String> get_services_data = new ArrayList<>();
    ArrayList<String> get_services_data_id = new ArrayList<>();
    private ProgressDialog dialog;
    private Bundle savedInstanceState;
    private File pdfFile;
    private List<GET_Services_Data> Get_services_data = new ArrayList<>();
    private List<String> SpinnersStringList = new ArrayList<>();
    private ArrayAdapter<String> service_adapter;
    private EditText proname;
    private Button addmore_sub_serpro;
    private Spinner spinProductGroup;

    //++++++++++++++++++++++++++end of them++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++For Others Details+++++++++++++++++++++++++++++++++++++
//    private BroadcastReceiver  OthersDetails = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // intent can contain anydata
//            Log.d("sohail","onReceive called");
//         //   Toast.makeText(context, "Broadcast received !", Toast.LENGTH_SHORT).show();
//            if (intent != null) {
//                String str = intent.getStringExtra("Add_More_Details");
//                Toast.makeText(context, "Broadcast received Other's Details !"+str, Toast.LENGTH_SHORT).show();
//                if(selectandRemoveList.size() >0) {
//                    selectandRemoveList.add(new AddNews(2,str));
//                    gridLayoutManager = new GridLayoutManager(getApplicationContext(),6);
//                    showmoredeatils.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
//                    showmoredeatils.setLayoutManager(gridLayoutManager);
//                    selectandRemoveAdapter = new SelectandRemoveAdapter(context, selectandRemoveList);
//                    showmoredeatils.swapAdapter(selectandRemoveAdapter, false);
//                    selectandRemoveAdapter.notifyItemInserted(0);
//                }else {
//                    selectandRemoveList.add(new AddNews(2,str));
//                    gridLayoutManager = new GridLayoutManager(getApplicationContext(),6);
//                    showmoredeatils.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
//                    showmoredeatils.setLayoutManager(gridLayoutManager);
//
//                    selectandRemoveAdapter = new SelectandRemoveAdapter(context, selectandRemoveList);
//                    showmoredeatils.setAdapter(selectandRemoveAdapter);
//                    selectandRemoveAdapter.notifyItemInserted(0);
//                }
//                // get all your data from intent and do what you want
//            }
//          //  tv.setText("Broadcast received !");
//
//        }
//    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add_product);

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        // LocalBroadcastManager OthersDetailsBroadcastManager = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(onNotice, new IntentFilter("filter_string"));
        //    OthersDetailsBroadcastManager.registerReceiver(OthersDetails, new IntentFilter("Add_More_Details_Key"));
        AllViwsbyid();
        GETAllCAtegories();

      //  ++++++++++++++++++++++++++++++++all clicks+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        spinProductGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GETAllSubServiceS(get_services_data_id.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        productliimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        //for moreeeeeeeeeeeeeeeeeeeeeeeeeee keywqordssssssssssssssssssssssssssssssssssss//
//        newkey.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, newkey ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
////                        Toast.makeText(AddProductWay.this, "onItemClick Item clicked", Toast.LENGTH_SHORT).show();
//    //                   Toast.makeText(AddProductWay.this, "onItemCl "+newkey.findViewHolderForItemId(R.id.add), Toast.LENGTH_SHORT).show();
////
////                       if(newkey.fi == R.id.addnewkey)
////                       {
////                           Toast.makeText(AddProductWay.this, "at position"+addMorekeysAdapter.Doc.get(position).getButton().toString(), Toast.LENGTH_SHORT).show();
////                       }else {
////                           Toast.makeText(AddProductWay.this, "no it was nto at position", Toast.LENGTH_SHORT).show();
////                       }
//
//                        // do whatever
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        Toast.makeText(AddProductWay.this, "onLongItemClick Item clicked", Toast.LENGTH_SHORT).show();
//                        // do whatever
//                    }
//                })
//        );
        addmore_sub_serpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddProductWay.this);
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
                            for (int i = 0; i < add_services.size(); i++) {

                                if (add_services.get(i).getService().equals(spinProductGroup.getSelectedItem())) {
                                    Add_New_Sub_Service(add_services.get(i).getId(), et_add_service.getText().toString(), i);
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
        //+++++++++++++++++++++++++++++++++Add More Main Cats+++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        addmantype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainrec.setVisibility(View.VISIBLE);
                if (addNewsList.size() > 0) {
                    addNewsList.add(new AddNews(1, new EditText(v.getContext()), new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    mainrec.setLayoutManager(llm);
                    addNewsAdapter = new AddNewsAdapter(v.getContext(), addNewsList);
                    mainrec.swapAdapter(addMorekeysAdapter, false);
                    addMorekeysAdapter.notifyItemInserted(0);
                    mainrec.requestFocus();
                } else {
                    addNewsList.add(new AddNews(1, new EditText(v.getContext()), new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    mainrec.setLayoutManager(llm);
                    addNewsAdapter = new AddNewsAdapter(v.getContext(), addNewsList);
                    mainrec.setAdapter(addNewsAdapter);
                    mainrec.requestFocus();
                    addmantype.setVisibility(View.GONE);
                }
            }
        });
        //++++++++++++++++++++++++++++++++++End of it++++++++++++++++++++++++++++++++++++++++++++++++++++++

        addmorekey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addnewkeyList.size() >0) {
                    addnewkeyList.add(new AddNews(1,new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    newkey.setLayoutManager(llm);
                    addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                    newkey.swapAdapter(addMorekeysAdapter, false);
                    addMorekeysAdapter.notifyItemInserted(0);
                    newkey.requestFocus();
                }else {
                    addnewkeyList.add(new AddNews(1,new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    newkey.setLayoutManager(llm);
                    addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                    newkey.setAdapter(addMorekeysAdapter);
                    newkey.requestFocus();
                    addmorekey.setVisibility(View.GONE);
                }

            }

        });
        //////////////////////////////////////////////////////
        //for add more details////////////////////////////

        addmrdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoredet.setVisibility(View.VISIBLE);
            //    AllMoredetailesList.add(new AddNews(new EditText(v.getContext())));
                if(AllMoredetailesList.size() >0) {
                    Toast.makeText(AddProductWay.this, "working", Toast.LENGTH_SHORT).show();
                    AllMoredetailesList.add(new AddNews(2,new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmoredet.setLayoutManager(llm);
                    addMoredetailsAdapter = new AddMoredetailesAdapter(v.getContext(), AllMoredetailesList);
                    addmoredet.swapAdapter(addMoredetailsAdapter, false);
                    addMoredetailsAdapter.notifyItemInserted(0);
                    addmoredet.requestFocus();
                }else {
                    Toast.makeText(AddProductWay.this, "working", Toast.LENGTH_SHORT).show();
                    AllMoredetailesList.add(new AddNews(2,new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);

                    addMoredetailsAdapter = new AddMoredetailesAdapter(v.getContext(), AllMoredetailesList);
                    addmoredet.setLayoutManager(llm);
                    addmoredet.setAdapter(addMoredetailsAdapter);
                    //   addMoredetailsAdapter.notifyItemChanged(AllMoredetailesList.size() + 1);
                    addmoredet.requestFocus();
                    addmrdet.setVisibility(View.GONE);
                }
            }
        });
////////////////////////////////////////add more//////////////////////////////////

        addmorepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddProductWay.this, "hjusdoigh", Toast.LENGTH_SHORT).show();
                if(AllMorepayList.size() >0) {
                    AllMorepayList.add(new AddNews(5, new RadioButton(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmorepay.setLayoutManager(llm);

                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmorepay.swapAdapter(addMorePayoptions, false);
                    addMorePayoptions.notifyItemInserted(0);
                    addmorepay.requestFocus();
                }else {
                    AllMorepayList.add(new AddNews(5, new RadioButton(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmorepay.setLayoutManager(llm);
                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmorepay.setAdapter(addMorePayoptions);
                    addmorepay.requestFocus();
                    // addmorepay.setVisibility(View.GONE);
                }
            }
        });
        ////////////////////////////////add pore /////////////////////////////////////////
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  SelectandRemoveList
                //SelectandRemovedetailsList
                //SelectandRemovedetailsList

                //       new ADDonlyProducttest(SelectandRemoveList,SelectandRemovedetailsList ,SelectandRemovedetailsList.getText().toString() , price.getText().toString(),stock.getText().toString() ,qty.getText().toString()).execute();
            }
        });
    }

    private void Add_New_Sub_Service(String id, String name, int i) {


        ProgressDialog progressDialog = new ProgressDialog(this);
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

                    GETAllSubServiceS(String.valueOf(id));

                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<AddSubService> call, Throwable t) {

                Log.e("failer", "" + t.getMessage());
                Toast.makeText(AddProductWay.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProductWay.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GETAllSubServiceS(String selectedItem) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        // get_sub_services_data.clear();
        //  get_sub_services_data_id.clear();
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
                //progressDialog.dismiss();

                Log.e("Add_new_service", "" + response.toString());
                Log.e("Add_service_res_msg", "" + response.body().getResponce());
                Log.e("Add_service_res_msg_id", "" + response.body().getData().get(0).getName());
                Log.e("Add_service_res_suc", "" + response.isSuccessful());
                get_sub_services_data.clear();
                //     service_sub_adapter.notifyDataSetChanged();

                if (response.isSuccessful()) {
                    try {
                        //   progressDialog.dismiss();
                        // Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                        //   SubTypestrings = new String[response.body().getData().size()];
//                Log.e("getact" , ""+response.body().getData().size());
                        for (int k = 0; k < response.body().getData().size(); k++) {
                            Log.e("getact msain", "" + response.body().getData().get(k).getId());
                            get_sub_services_data.add(response.body().getData().get(k).getName());
                            get_sub_services_data_id.add(response.body().getData().get(k).getId());

                            service_sub_adapter = new ArrayAdapter<String>(AddProductWay.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);
                            spinsubcat.setAdapter(service_sub_adapter);
                            progressDialog.dismiss();
                        }
                    } catch (Exception e) {
                        get_sub_services_data.add("None Found");

                        service_sub_adapter = new ArrayAdapter<String>(AddProductWay.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

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

                service_sub_adapter = new ArrayAdapter<String>(AddProductWay.this, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

                spinsubcat.setAdapter(service_sub_adapter);
                progressDialog.dismiss();
                Log.e("failer", "" + t.getMessage());
                Toast.makeText(AddProductWay.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProductWay.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //***************************************************************************************************************
    private void GETAllCAtegories() {
        Log.e("logs at", "" + new SessionManager(AddProductWay.this).getUS());
        ProgressDialog progressDialog = new ProgressDialog(this);
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
        Call<MyServices> get_aboutCall = AbloutApi.MY_SERVICES_CALL(new SessionManager(AddProductWay.this).getUS());
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

                }

                service_adapter = new ArrayAdapter<String>(AddProductWay.this, android.R.layout.simple_spinner_dropdown_item, get_services_data);
                spinProductGroup.setAdapter(service_adapter);
                GETAllSubServiceS(String.valueOf(response.body().getData().get(0).getId()));
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MyServices> call, Throwable t) {
                Toast.makeText(AddProductWay.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProductWay.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void selectImage() {

        final CharSequence[] items = {"Upload", "Upload from Gallery", "Back"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProductWay.this);
        builder.setTitle("Upload!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(AddProductWay.this);

                if (items[item].equals("Upload")) {
                    if (result)
                        if (CameraUtils__TRD.checkPermissions(AddProductWay.this)) {
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
        Uri fileUri = CameraUtils__TRD.getOutputMediaFileUri_TRD(AddProductWay.this, file);
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
                        CameraUtils__TRD.openSettings(AddProductWay.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }
    private void AllViwsbyid() {
        addmorekey = findViewById(R.id.addmorekey);
        //++++++++++++++++++++++All Fields++++++++++++++++++++++++++++++++
        proname = findViewById(R.id.pronames);
        spinProductGroup = findViewById(R.id.spinProductGroup);
        spinsubcat = findViewById(R.id.spinsubcat);

        //+++++++++++++++++++++++++End++++++++++++++++++++++++++++++++++++++++++++++++++
        //Recylerviews+++++++++++++++++++++++++++++++++
        mainrec = findViewById(R.id.mainrec);
        productliimg = findViewById(R.id.productliimg);
        addepros = findViewById(R.id.addepros);
        newkey = findViewById(R.id.newkey);
        addmoredet = findViewById(R.id.addmoredet);
        addmantype = findViewById(R.id.addmantype);
        showmoredeatils = findViewById(R.id.showmoredeatils);
        showkeys = findViewById(R.id.showkeys);
        nxt = findViewById(R.id.nxt);
        addmorepay = findViewById(R.id.addmorepay);
        showpays = findViewById(R.id.showpays);
        addmrdet = findViewById(R.id.addmrdet);
        addmorepro = findViewById(R.id.addmorepro);
        addmore_sub_serpro = findViewById(R.id.addmore_sub_serpro);
    }
    //+++++++++++++++++++++++++++++++++++++++++end of clicks++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //+++++++++++++++++++++++++++++++++++++++++on Resume++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected void onResume() {
        super.onResume();

     //   IntentFilter iff= new IntentFilter("filter_string");
  //     LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, iff);
    }
    //++++++++++++++++++++++++++++++++++++++++End of on resume+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++On Pause+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onNotice);
        //    LocalBroadcastManager.getInstance(this).unregisterReceiver(OthersDetails);
        super.onDestroy();
    }

    //+++++++++++++++++++++++++++++++++++++++++end of on destreiy+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
                    Toast.makeText(AddProductWay.this, "successfully taken", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddProductWay.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                }
            } catch (NullPointerException e) {
                Toast.makeText(AddProductWay.this, "Having some problem with image ,please try again registering", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //////////////////////////////////////////for image uplaod//////////////////////////////////////////////////////////////////
    private class ADDonlyProducttest extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String productstitles, productsdescrip, mrp, price, stock, qty;

        public ADDonlyProducttest(String productstitles, String productsdescrip, String mrp, String price, String stock, String qty) {
            this.productstitles = productstitles;
            this.productsdescrip = productsdescrip;
            this.mrp = mrp;
            this.price = price;
            this.stock = stock;
            this.qty = qty;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(AddProductWay.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/addProduct");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", new SessionManager(AddProductWay.this).getUS());
                postDataParams.put("category", "category");
                postDataParams.put("subcategory", "subcategory");
                postDataParams.put("product_name", "product_name");
                // postDataParams.put("description", description.getText().toString());
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
                    if (response) {
                        Toast.makeText(AddProductWay.this, "Success", Toast.LENGTH_SHORT).show();
                        if (pdfFile.exists()) {
                            Toast.makeText(AddProductWay.this, "yeah", Toast.LENGTH_SHORT).show();
                            new OnlyImageupload(String.valueOf(new SessionManager(AddProductWay.this).getUS()), data).execute();
                        } else {
                            finish();
                            Intent intent = new Intent(AddProductWay.this, AddProduct_Activity.class);
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
                } catch (Exception e) {
                    Intent intent = new Intent(AddProductWay.this, AddProductWay.class);
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
            dialog = new ProgressDialog(AddProductWay.this);
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
                Intent intent = new Intent(AddProductWay.this, AddProduct_Activity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AddProductWay.this, "Documents Uploaded Successfully ", Toast.LENGTH_LONG).show();


                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(AddProductWay.this, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
    }

    ////////////////////////////////////////////////////////////////end of/////////////////////////


}
