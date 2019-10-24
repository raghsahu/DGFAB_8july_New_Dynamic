package com.example.dgfab.Expandable;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.AddMorePayoptions;
import com.example.dgfab.Adapter.AddMoredetailesAdapter;
import com.example.dgfab.Adapter.AddMorekeysAdapter;
import com.example.dgfab.Adapter.AddNewsAdapter;
import com.example.dgfab.Adapter.SelectandProsAdapter;
import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Adapter.SelectndRemvDetailsAdapter;
import com.example.dgfab.AllParsings.AddSubService;
import com.example.dgfab.AllParsings.GET_Services_Data;
import com.example.dgfab.AllParsings.MyServicesData;
import com.example.dgfab.AllParsings.MySubservices;
import com.example.dgfab.BusinessDashboard.AddProductWay;
import com.example.dgfab.BusinessDashboard.AddProduct_Activity;
import com.example.dgfab.BusinessDashboard.SelndRemradioAdapter;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.example.dgfab.Utils.Utilities;

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
import java.util.BitSet;
import java.util.HashMap;
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

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    //For Adapters
    SelndRemradioAdapter selndRemradioAdapter;
    SelectandRemoveAdapter selectandRemoveAdapter;
    AddMorekeysAdapter addMorekeysAdapter;
    SelectandProsAdapter selectandProsAdapter;
    ArrayAdapter<String> service_sub_adapter;
    //
    //++++++++++++++++++++++++++++++++++++++++All Initializations++++++++++++++++++++
    Button addmantype, addmorekey, addmrdet, addmorepro, addmore_sub_serpro;
    Spinner spicatpro, spinsubcat;
    EditText Name;
    RecyclerView newkey, addmoredet, addmorepay, showkeys, showmoredeatils, addepros, showpays;
    EditText proname;
    //    RecyclerView showkeys,newkey;
    LayoutInflater infalInflater;
    Button btnsubmit;
    List<AddNews> addnewkeyList = new ArrayList<>();
    // child data in format of header title, child title
    List<MyServicesData> add_services;
    //All Lists
    List<AddNews> addNewsList = new ArrayList<AddNews>();
    List<AddNews> AllMoredetailesList = new ArrayList<>();
    View ExpandView;
    List<AddNews> AllMorepayList = new ArrayList<>();
    private Context _context;
    List<AddNews> SelectandRemoveList = new ArrayList<>();
    List<AddNews> SelectandRemovedetailsList = new ArrayList<>();
    List<AddNews> SelectandMainproList = new ArrayList<>();
    private List<String> _listDataHeader; // header titles
    List<AddNews> SelectandRadioproList = new ArrayList<>();
    private HashMap<String, List<BasicInfo>> _listDataChild;
    ArrayList<String> get_sub_services_data = new ArrayList<>();
    ArrayList<String> get_sub_services_data_id = new ArrayList<>();
    private SelectndRemvDetailsAdapter selectndRemvDetailsAdapter;
    private AddMoredetailesAdapter addMoredetailsAdapter;
    private RecyclerView mainrec;
    private AddMorePayoptions addMorePayoptions;
    private AddNewsAdapter addNewsAdapter;
    private EditText spinProductGroup;
    //++++++++++++++++++++++++++++++
    private File pdfFile;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++Broad Cast++++++++++++++++++++++++++++++
    //+++++++++++++++++++open for more details adapter and more keywords++++++++++++++++++++++++++++++++++++++++++++
    private BroadcastReceiver onNotice = new BroadcastReceiver() {

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
                Toast.makeText(context, "Remove id" + removeid, Toast.LENGTH_SHORT).show();
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
            Log.d("sohail", "onReceive called");
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
                GridLayoutManager gridLayoutManager;
                if (!str.equals("null")) {
                    showkeys.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "Broadcast received KeysDetails!" + str, Toast.LENGTH_SHORT).show();
                    if (SelectandRemoveList.size() > 0) {
                        SelectandRemoveList.add(new AddNews(1, str));
                        gridLayoutManager = new GridLayoutManager(context, 4);
                        showkeys.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
                        showkeys.setLayoutManager(gridLayoutManager);
                        selectandRemoveAdapter = new SelectandRemoveAdapter(context, SelectandRemoveList);
                        showkeys.swapAdapter(selectandRemoveAdapter, false);
                        selectandRemoveAdapter.notifyItemInserted(0);
                    } else {
                        SelectandRemoveList.add(new AddNews(1, str));
                        gridLayoutManager = new GridLayoutManager(context, 4);
                        showkeys.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
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
                        gridLayoutManager = new GridLayoutManager(_context, 4);
                        showmoredeatils.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
                        showmoredeatils.setLayoutManager(gridLayoutManager);
                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(context, SelectandRemovedetailsList);
                        showmoredeatils.swapAdapter(selectndRemvDetailsAdapter, false);
                        selectndRemvDetailsAdapter.notifyItemInserted(0);
                    } else {
                        SelectandRemovedetailsList.add(new AddNews(2, dtr));
                        gridLayoutManager = new GridLayoutManager(_context, 4);
                        showmoredeatils.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
                        showmoredeatils.setLayoutManager(gridLayoutManager);

                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(_context, SelectandRemovedetailsList);
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
                        gridLayoutManager = new GridLayoutManager(_context, 2);
                        addepros.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
                        addepros.setLayoutManager(gridLayoutManager);
                        selectandProsAdapter = new SelectandProsAdapter(context, SelectandMainproList);
                        addepros.swapAdapter(selectandProsAdapter, false);
                        selectandProsAdapter.notifyItemInserted(0);
                    } else {
                        SelectandMainproList.add(new AddNews(4, maintits, mainvalue));
                        gridLayoutManager = new GridLayoutManager(_context, 2);
                        addepros.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
                        addepros.setLayoutManager(gridLayoutManager);

                        selectandProsAdapter = new SelectandProsAdapter(_context, SelectandMainproList);
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
                        gridLayoutManager = new GridLayoutManager(_context, 2);
                        showpays.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
                        showpays.setLayoutManager(gridLayoutManager);
                        selndRemradioAdapter = new SelndRemradioAdapter(context, SelectandRadioproList);
                        showpays.swapAdapter(selndRemradioAdapter, false);
                        selndRemradioAdapter.notifyItemInserted(0);

                    } else {
                        SelectandRadioproList.add(new AddNews(5, radiotit));
                        gridLayoutManager = new GridLayoutManager(_context, 2);
                        showpays.addItemDecoration(new DividerItemDecoration(_context, LinearLayoutManager.VERTICAL));
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


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<BasicInfo>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(_context);
        // LocalBroadcastManager OthersDetailsBroadcastManager = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(onNotice, new IntentFilter("filter_string"));
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        //      Log.e("Expanded view" , "groupPosition"+groupPosition);
//        if(groupPosition)
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

//        final String childText =  getChild(groupPosition, childPosition);
        final BasicInfo BasicInfo = (BasicInfo) getChild(groupPosition, childPosition);
        Log.e("childPosition", "is " + childPosition);
        Log.e("groupPosition", "is groupPosition " + groupPosition);
        int itemType = getChildType(groupPosition, childPosition);
//        this.infalInflater = LayoutInflater.from(parent.getContext());
//        convertView.re
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (("Basic Information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Basic", Toast.LENGTH_SHORT).show();
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
            convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
            convertView.requestFocus();

            //++++++++++++++++++++++All Fields++++++++++++++++++++++++++++++++

            //+++++++++++++++++++++++++End++++++++++++++++++++++++++++++++++++++++++++++++++
//            proname =  convertView.findViewById(R.id.pronames);
            spinProductGroup = convertView.findViewById(R.id.spinProductGroup);
            addmantype = convertView.findViewById(R.id.addmantype);
            spinsubcat = convertView.findViewById(R.id.spinsubcat);
            addmrdet = convertView.findViewById(R.id.addmrdet);
            addmorekey = convertView.findViewById(R.id.addmorekey);
            Name = convertView.findViewById(R.id.pronames);
            newkey = convertView.findViewById(R.id.newkey);
            showkeys = convertView.findViewById(R.id.showkeys);
            addmantype = convertView.findViewById(R.id.addmantype);
            addmrdet = convertView.findViewById(R.id.addmrdet);
            addmoredet = convertView.findViewById(R.id.addmoredet);
            mainrec = convertView.findViewById(R.id.mainrec);
            addepros = convertView.findViewById(R.id.addepros);
            showmoredeatils = convertView.findViewById(R.id.showmoredeatils);


            addmore_sub_serpro = convertView.findViewById(R.id.addmore_sub_serpro);
            //++++++++++++++++++++++++++++++++++++++listenrs
            addmore_sub_serpro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(_context);
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

                                    if (add_services.get(i).getService().equals(spinProductGroup.getText())) {
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
                    if (addnewkeyList.size() > 0) {
                        addnewkeyList.add(new AddNews(1, new EditText(v.getContext()), new Button(v.getContext())));
                        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        newkey.setLayoutManager(llm);
                        addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                        newkey.swapAdapter(addMorekeysAdapter, false);
                        addMorekeysAdapter.notifyItemInserted(0);
                        newkey.requestFocus();
                    } else {
                        addnewkeyList.add(new AddNews(1, new EditText(v.getContext()), new Button(v.getContext())));
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
                    if (AllMoredetailesList.size() > 0) {
                        Toast.makeText(_context, "working", Toast.LENGTH_SHORT).show();
                        AllMoredetailesList.add(new AddNews(2, new EditText(v.getContext())));
                        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        addmoredet.setLayoutManager(llm);
                        addMoredetailsAdapter = new AddMoredetailesAdapter(v.getContext(), AllMoredetailesList);
                        addmoredet.swapAdapter(addMoredetailsAdapter, false);
                        addMoredetailsAdapter.notifyItemInserted(0);
                        addmoredet.requestFocus();
                    } else {
                        Toast.makeText(_context, "working", Toast.LENGTH_SHORT).show();
                        AllMoredetailesList.add(new AddNews(2, new EditText(v.getContext())));
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


            addmorekey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (showkeys.getVisibility() == View.GONE) {
                        showkeys.setVisibility(View.VISIBLE);
                        GridLayoutManager gridLayoutManager;
                        if (addnewkeyList.size() > 0) {
                            addnewkeyList.add(new AddNews(1, new EditText(v.getContext()), new Button(v.getContext())));
                            LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            newkey.setLayoutManager(llm);
                            addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                            newkey.swapAdapter(addMorekeysAdapter, false);
                            addMorekeysAdapter.notifyItemInserted(0);
                            newkey.requestFocus();
                        } else {
                            addnewkeyList.add(new AddNews(1, new EditText(v.getContext()), new Button(v.getContext())));
                            LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            newkey.setLayoutManager(llm);
                            addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                            newkey.setAdapter(addMorekeysAdapter);
                            newkey.requestFocus();
                            addmorekey.setVisibility(View.GONE);
                        }
//                        if (SelectandRemoveList.size() > 0) {
//                            SelectandRemoveList.add(new AddNews(1, ""));
//                            gridLayoutManager = new GridLayoutManager(v.getContext(), 4);
//                            showkeys.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
//                            showkeys.setLayoutManager(gridLayoutManager);
//                            selectandRemoveAdapter = new SelectandRemoveAdapter(v.getContext(), SelectandRemoveList);
//                            showkeys.swapAdapter(selectandRemoveAdapter, false);
//                            selectandRemoveAdapter.notifyItemInserted(0);
//                        } else {
//                            SelectandRemoveList.add(new AddNews(1, ""));
//                            gridLayoutManager = new GridLayoutManager(v.getContext(), 4);
//                            showkeys.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
//                            showkeys.setLayoutManager(gridLayoutManager);
//                            selectandRemoveAdapter = new SelectandRemoveAdapter(v.getContext(), SelectandRemoveList);
//                            showkeys.setAdapter(selectandRemoveAdapter);
//                            selectandRemoveAdapter.notifyDataSetChanged();
//                        }
                    } else {
                        showkeys.setVisibility(View.GONE);
                    }
                }
            });
            Name.setText(BasicInfo.getBrandName());

            Name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    BasicInfo.setBrandName(s.toString());
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    BasicInfo.setBrandName(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    BasicInfo.setBrandName(s.toString());
                }
            });
//            Name.setText("heyyyyyyyyyyyyyyyyyyyyyyy");

            //update your views here
        } else if (("Trade Information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Trade", Toast.LENGTH_SHORT).show();
            convertView = infalInflater.inflate(R.layout.tradeinfos, null);
            convertView.requestFocus();
            addmorepro = convertView.findViewById(R.id.addmorepro);
            addmorepay = convertView.findViewById(R.id.addmorepay);
            showpays = convertView.findViewById(R.id.showpays);
            //TRade Liiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiistnerrrrrrrrrrrrrrrrsssssssssssssssssss
            ////////////////////////////////////////add more//////////////////////////////////

            addmorepro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(_context, "hjusdoigh", Toast.LENGTH_SHORT).show();
                    if (AllMorepayList.size() > 0) {
                        AllMorepayList.add(new AddNews(5, new RadioButton(v.getContext())));
                        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        addmorepay.setLayoutManager(llm);

                        addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                        addmorepay.swapAdapter(addMorePayoptions, false);
                        addMorePayoptions.notifyItemInserted(0);
                        addmorepay.requestFocus();
                    } else {
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

            //+++++++++++++++++++++++++++++++++Add More Main Cats+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //+++++++++++++++++++++
            //update your views here
        } else if (("Logistics information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Trade", Toast.LENGTH_SHORT).show();
            convertView = infalInflater.inflate(R.layout.logisticinfo, null);
            convertView.requestFocus();
            btnsubmit = convertView.findViewById(R.id.btnsubmit);
            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("list 1", "" + SelectandRemoveList.toArray());
                    Log.e("list 1", "" + SelectandRemovedetailsList.toArray());
                    Log.e("list 1", "" + SelectandMainproList);
//                    new ADDonlyProducttest(pro).execute()
//                        new ADDonlyProducttest().execute();
                }
            });
            //update your views here
        }
//            switch (itemType) {
//                case 0:
//                    Toast.makeText(_context, "we are at"+itemType, Toast.LENGTH_SHORT).show();
//                    convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
//                    break;
//                case 1:
//                    Toast.makeText(_context, "we are at"+itemType, Toast.LENGTH_SHORT).show();
//                    convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                    break;
//            }
//            if(groupPosition==0) {
//                Toast.makeText(_context, "at"+groupPosition, Toast.LENGTH_SHORT).show();
//                 infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
//                notifyDataSetChanged();
//            }
//            if(groupPosition ==1)
//            {
//                Toast.makeText(_context, "at"+groupPosition, Toast.LENGTH_SHORT).show();
//                 infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                notifyDataSetChanged();
//            }

//        EditText Proname = convertView.findViewById(R.id.pronames);
//        //  Proname.getText().clear();
//        Proname.setHint("Product Name");

//        TextView txtListChild = (TextView) convertView
//                .findViewById(R.id.lblListItem);
//
//        txtListChild.setText(childText);
        return convertView;
    }

    private void Add_New_Sub_Service(String id, String name, int i) {


        ProgressDialog progressDialog = new ProgressDialog(_context);
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
                Toast.makeText(_context, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(_context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GETAllSubServiceS(String selectedItem) {
        ProgressDialog progressDialog = new ProgressDialog(_context);
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

                            service_sub_adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);
                            spinsubcat.setAdapter(service_sub_adapter);
                            progressDialog.dismiss();
                        }
                    } catch (Exception e) {
                        get_sub_services_data.add("None Found");

                        service_sub_adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

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

                service_sub_adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_dropdown_item, get_sub_services_data);

                spinsubcat.setAdapter(service_sub_adapter);
                progressDialog.dismiss();
                Log.e("failer", "" + t.getMessage());
                Toast.makeText(_context, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(_context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //***************************************************************************************************************

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        this.ExpandView = convertView;
        //   Log.e("groupPosition", "is " + groupPosition);
        if (convertView == null) {
//            if(headerTitle.equals("Basic Information")) {
            Toast.makeText(_context, "BAsic", Toast.LENGTH_SHORT).show();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.basicinfogroup, null);

//            }
//            else {
//                Toast.makeText(_context, "Trading", Toast.LENGTH_SHORT).show();
//                LayoutInflater infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                TextView lblListHeader = (TextView) convertView
//                        .findViewById(R.id.lblListHeader);
//
//                lblListHeader.setTypeface(null, Typeface.BOLD);
//                lblListHeader.setText(headerTitle);
//            }
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void ChangeIconUp() {
        Toast.makeText(_context, "I called", Toast.LENGTH_SHORT).show();
        ImageView expaicon = ExpandView.findViewById(R.id.expaicon);
        expaicon.setImageResource(R.drawable.expandup);
        //  expaicon.setVisibility(View.GONE);
        //notify();
    }

    public void ChangeIconDown() {
        Toast.makeText(_context, "I called", Toast.LENGTH_SHORT).show();
        ImageView expaicon = ExpandView.findViewById(R.id.expaicon);
        expaicon.setImageResource(R.drawable.expanddown);
        //  expaicon.setVisibility(View.GONE);
        //notify();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++APi Calling++++++++++++++++++++++++++++++++++++
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
            dialog = new ProgressDialog(_context);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/addProduct");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", new SessionManager(_context).getUS());
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
                        Toast.makeText(_context, "Success", Toast.LENGTH_SHORT).show();
                        if (pdfFile.exists()) {
                            Toast.makeText(_context, "yeah", Toast.LENGTH_SHORT).show();
                            new OnlyImageupload(String.valueOf(new SessionManager(_context).getUS()), data).execute();
                        } else {
                            ((Activity) _context).finish();
                            Intent intent = new Intent(_context, AddProduct_Activity.class);
                            _context.startActivity(intent);
                            ((Activity) _context).finish();
                            //finish();
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
                    Intent intent = new Intent(_context, AddProductWay.class);
                    _context.startActivity(intent);
                    ((Activity) _context).finish();
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
        private ProgressDialog dialog;


        public OnlyImageupload(String id, String data) {
            this.Productid = data;

            this.id = id;
        }


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(_context);
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
                ((Activity) context).finish();
                Intent intent = new Intent(_context, AddProduct_Activity.class);
                _context.startActivity(intent);
                ((Activity) context).finish();
                Toast.makeText(_context, "Documents Uploaded Successfully ", Toast.LENGTH_LONG).show();


                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(_context, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
    }

    ////////////////////////////////////////////////////////////////end of/////////////////////////
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
