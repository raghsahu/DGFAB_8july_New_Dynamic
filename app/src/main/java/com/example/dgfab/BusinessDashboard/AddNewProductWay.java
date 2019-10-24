package com.example.dgfab.BusinessDashboard;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Adapter.AddMorePayoptions;
import com.example.dgfab.Adapter.AddMoredetailesAdapter;
import com.example.dgfab.Adapter.AddMorekeysAdapter;
import com.example.dgfab.Adapter.AddNewsAdapter;
import com.example.dgfab.Adapter.SelectandProsAdapter;
import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Adapter.SelectndRemvDetailsAdapter;
import com.example.dgfab.AllParsings.GET_Services_Data;
import com.example.dgfab.AllParsings.MyServicesData;
import com.example.dgfab.Expandable.BasicInfo;
import com.example.dgfab.Expandable.ExpandableListAdapter;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddNewProductWay extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView basicinfoex;
    List<String> BasicListDataHeaderList;
    LinearLayout lifo;
    //    EditText editText;
//    HashMap<String, List<String>> listDataChild;
    HashMap<String, List<BasicInfo>> BasicinfoHashMap;
    //+++++++++++++++++++open for more details adapter and more keywords++++++++++++++++++++++++++++++++++++++++++++
//    private BroadcastReceiver onNotice= new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            //+++++++++++++++++++++++++++++++++remove list intent+++++++++++++++++++++++++++++++++++++++++++
//            String removeid = intent.getStringExtra("removekey");
//            try {
//                if (removeid.isEmpty()) ;
//            } catch (Exception e) {
//                removeid = "ok";
//                e.printStackTrace();
//            }
//            if (removeid.equals("ok")) {
//                //  Toast.makeText(context, "Done nothing", Toast.LENGTH_SHORT).show();
//            } else {
//                if (removeid == "1") {
//                    addmantype.setVisibility(View.VISIBLE);
//                }
//                if (removeid == "2") {
//                    addmorekey.setVisibility(View.VISIBLE);
//                }
//                if (removeid == "3") {
//                    addmrdet.setVisibility(View.VISIBLE);
//                }
//                if (removeid == "4") {
//                    addmorepro.setVisibility(View.VISIBLE);
//                }
//            }
//            //+++++++++++++++++++++++++++++++++++++++++++++++++++
//            // intent can contain anydata
//            Log.d("sohail","onReceive called");
//            //     Toast.makeText(context, "Broadcast received !", Toast.LENGTH_SHORT).show();
//            if (intent != null) {
//                String str = intent.getStringExtra("key");
//                try {
//                    if (str.isEmpty()) ;
//                } catch (Exception e) {
//                    Toast.makeText(context, "It is empty", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                    str = "null";
//                }
//                if (!str.equals("null")) {
//                    showkeys.setVisibility(View.VISIBLE);
//                    //  Toast.makeText(context, "Broadcast received KeysDetails!" + str, Toast.LENGTH_SHORT).show();
//                    if (SelectandRemoveList.size() > 0) {
//                        SelectandRemoveList.add(new AddNews(1, str));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
//                        showkeys.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showkeys.setLayoutManager(gridLayoutManager);
//                        selectandRemoveAdapter = new SelectandRemoveAdapter(context, SelectandRemoveList);
//                        showkeys.swapAdapter(selectandRemoveAdapter, false);
//                        selectandRemoveAdapter.notifyItemInserted(0);
//                    } else
//                    {
//                        SelectandRemoveList.add(new AddNews(1, str));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
//                        showkeys.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showkeys.setLayoutManager(gridLayoutManager);
//                        selectandRemoveAdapter = new SelectandRemoveAdapter(context, SelectandRemoveList);
//                        showkeys.setAdapter(selectandRemoveAdapter);
//                        selectandRemoveAdapter.notifyDataSetChanged();
//                    }
//                }
//                //++++++++++++++++++++++++For more details+++++++++++++++++++++
//                String dtr = intent.getStringExtra("Add_More_Details_Key");
//                try {
//                    if (dtr.isEmpty()) ;
//                } catch (Exception e) {
//                    dtr = "null2";
//                    e.printStackTrace();
//                }
//                if (!dtr.equals("null2")) {
//
//                    showmoredeatils.setVisibility(View.VISIBLE);
//                    //  Toast.makeText(context, "Broadcast received Other's Details !" + dtr, Toast.LENGTH_SHORT).show();
//                    if (SelectandRemovedetailsList.size() > 0) {
//                        SelectandRemovedetailsList.add(new AddNews(2, dtr));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
//                        showmoredeatils.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showmoredeatils.setLayoutManager(gridLayoutManager);
//                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(context, SelectandRemovedetailsList);
//                        showmoredeatils.swapAdapter(selectndRemvDetailsAdapter, false);
//                        selectndRemvDetailsAdapter.notifyItemInserted(0);
//                    } else {
//                        SelectandRemovedetailsList.add(new AddNews(2, dtr));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
//                        showmoredeatils.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showmoredeatils.setLayoutManager(gridLayoutManager);
//
//                        selectndRemvDetailsAdapter = new SelectndRemvDetailsAdapter(context, SelectandRemovedetailsList);
//                        showmoredeatils.setAdapter(selectndRemvDetailsAdapter);
//                        selectndRemvDetailsAdapter.notifyDataSetChanged();
//                    }
//                } else {
//                    String mainstr = intent.getStringExtra("Add_More_Details_Key");
//                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
//                }
//                // Toast.makeText(context, "it's null", Toast.LENGTH_SHORT).show();
//                //+++++++++++++++++++++++++++++++++++++++
//                // get all your data from intent and do what you want
//                String maintits = intent.getStringExtra("maintits");
//                String mainvalue = intent.getStringExtra("mainvalue");
//                try {
//                    if (maintits.isEmpty() && mainvalue.isEmpty()) ;
//                } catch (Exception e) {
//                    maintits = "null3";
//                    mainvalue = "null3";
//                    e.printStackTrace();
//                }
//                if (!maintits.equals("null3") && !mainvalue.equals("null3")) {
//
//                    addepros.setVisibility(View.VISIBLE);
//                    //  Toast.makeText(context, "Broadcast received Other's Details !" + maintits, Toast.LENGTH_SHORT).show();
//                    if (SelectandMainproList.size() > 0) {
//                        SelectandMainproList.add(new AddNews(4, maintits, mainvalue));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                        addepros.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        addepros.setLayoutManager(gridLayoutManager);
//                        selectandProsAdapter = new SelectandProsAdapter(context, SelectandMainproList);
//                        addepros.swapAdapter(selectandProsAdapter, false);
//                        selectandProsAdapter.notifyItemInserted(0);
//                    } else {
//                        SelectandMainproList.add(new AddNews(4, maintits, mainvalue));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                        addepros.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        addepros.setLayoutManager(gridLayoutManager);
//
//                        selectandProsAdapter = new SelectandProsAdapter(context, SelectandMainproList);
//                        addepros.setAdapter(selectandProsAdapter);
//                        selectandProsAdapter.notifyDataSetChanged();
//                    }
//                } else {
//                    // String mainstr = intent.getStringExtra("Add_More_Details_Key");
//                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
//                }
//                String radiotit = intent.getStringExtra("radiotit");
//                try {
//                    if (radiotit.isEmpty() && radiotit.isEmpty()) ;
//                } catch (Exception e) {
//                    radiotit = "null4";
//                    radiotit = "null4";
//                    e.printStackTrace();
//                }
//                if (!radiotit.equals("null4")) {
//
//                    showpays.setVisibility(View.VISIBLE);
//                    //  Toast.makeText(context, "Broadcast received Other's Details !" + maintits, Toast.LENGTH_SHORT).show();
//                    if (SelectandRadioproList.size() > 0) {
//                        SelectandRadioproList.add(new AddNews(5, radiotit));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                        showpays.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showpays.setLayoutManager(gridLayoutManager);
//                        selndRemradioAdapter = new SelndRemradioAdapter(context, SelectandRadioproList);
//                        showpays.swapAdapter(selndRemradioAdapter, false);
//                        selndRemradioAdapter.notifyItemInserted(0);
//
//                    } else {
//                        SelectandRadioproList.add(new AddNews(5, radiotit));
//                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                        showpays.addItemDecoration(new DividerItemDecoration(AddNewProductWay.this, LinearLayoutManager.VERTICAL));
//                        showpays.setLayoutManager(gridLayoutManager);
//
//                        selndRemradioAdapter = new SelndRemradioAdapter(context, SelectandRadioproList);
//                        showpays.setAdapter(selndRemradioAdapter);
//                        selndRemradioAdapter.notifyDataSetChanged();
//                    }
//                } else {
//                    // String mainstr = intent.getStringExtra("Add_More_Details_Key");
//                    // Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
//                }
//            }
//            //  tv.setText("Broadcast received !");
//
//        }
//    };
//    private RecyclerView mainrec;
//    private AddNewsAdapter addNewsAdapter;
//    ArrayList<String> get_sub_services_data = new ArrayList<>();
//    ArrayList<String> get_sub_services_data_id = new ArrayList<>();
//    ArrayAdapter<String> service_sub_adapter;
//    Spinner spicatpro, spinsubcat;
//    List<MyServicesData> add_services;
//    ArrayList<String> get_services_data = new ArrayList<>();
//    ArrayList<String> get_services_data_id = new ArrayList<>();
//    private ProgressDialog dialog;
//    private Bundle savedInstanceState;
//    private File pdfFile;
//    private List<GET_Services_Data> Get_services_data = new ArrayList<>();
//    private List<String> SpinnersStringList = new ArrayList<>();
//    private ArrayAdapter<String> service_adapter;
//    private EditText proname;
//    private Button addmore_sub_serpro;
//    private Spinner spinProductGroup;

    //++++++++++++++++++++++++++end of them++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.newproductway);
        super.onCreate(savedInstanceState);

        // get the listview
        basicinfoex = (ExpandableListView) findViewById(R.id.basicinfoex);

        lifo = findViewById(R.id.lifo);
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, BasicListDataHeaderList, BasicinfoHashMap);

        // setting list adapter
        basicinfoex.setAdapter(listAdapter);
//        basicinfoex.
        // Listview Group click listener
        basicinfoex.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {


                Toast.makeText(getApplicationContext(),
                        "Group Clicked " + BasicListDataHeaderList.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
//                if(groupPosition ==0) {
//                    basicinfoex.setSelectedGroup(groupPosition);
//                    TextView lblListHeader = (TextView) v
//                            .findViewById(R.id.pronames);
//                    lblListHeader.setText("ghhhhhhhhhhhhhhhyj");
//                }
                return false;
            }
        });

        // Listview Group expanded listener
        basicinfoex.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                Toast.makeText(getApplicationContext(),
                        BasicListDataHeaderList.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
//                    if(groupPosition ==0) {
//                basicinfoex.setSelectedGroup(groupPosition);
//                TextView lblListHeader = (TextView) basicinfoex.getChildAt(groupPosition).getRootView()
//                        .findViewById(R.id.pronames);
//                        lblListHeader.setText("ghhhhhhhhhhhhhhhyj");
//                    }
//                basicinfoex.requestFocus();
                for (int i = 0; i < BasicListDataHeaderList.size(); i++) {
                    if (i != groupPosition) {
                        basicinfoex.collapseGroup(i);

                    } else {


                        //     Toast.makeText(AddNewProductWay.this, "Nothing", Toast.LENGTH_SHORT).show();
                    }
                }

                // listAdapter.ChangeIconUp();
                //   listAdapter.notify();

            }
        });

        // Listview Group collasped listener
        basicinfoex.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        BasicListDataHeaderList.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                // listAdapter.ChangeIconDown();
            }
        });

        // Listview on child click listener
        basicinfoex.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Log.e("position " , ""+groupPosition);
//                if (groupPosition == 0) {
//                    EditText Name = v.findViewById(R.id.pronames);
//                    Name.setText("hhhhhhhhhhhhhhhhhhhhhhhhhh");
//                }
                // Toast.makeText(AddNewProductWay.this, ""+BasicinfoHashMap.get(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(
//                        getApplicationContext(),
//                        BasicListDataHeaderList.get(groupPosition)
//                                + " : "
//                                + BasicinfoHashMap.get(
//                                BasicListDataHeaderList.get(groupPosition)).get(
//                                childPosition).getProductName().getHint(), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        BasicListDataHeaderList = new ArrayList<String>();
        BasicinfoHashMap = new HashMap<String, List<BasicInfo>>();

        // Adding child data
        BasicListDataHeaderList.add("Basic Information");
        BasicListDataHeaderList.add("Trade Information");
        BasicListDataHeaderList.add("Logistics information");
        // BasicListDataHeaderList.add("Logistic Information");
//        listDataHeader.add("Basic Information2");
//        listDataHeader.add("Trade Information");
//        listDataHeader.add("Logistic");
//        listDataHeader.add("Logistic");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<BasicInfo> BasicInfos = new ArrayList<>();
        List<BasicInfo> TradeBasicInfos = new ArrayList<>();
        List<BasicInfo> LogisticTradeBasicInfos = new ArrayList<>();
        BasicInfos.add(new BasicInfo(this, "", "", ""
                , "", new Button(this), new Button(this), new Button(this)
                , new Button(this), new RecyclerView(this), new RecyclerView(this), new Spinner(this), new Spinner(this)
                , new Spinner(this), new Spinner(this)));

        TradeBasicInfos.add(new BasicInfo(new RadioButton(this), new RadioButton(this)));
        LogisticTradeBasicInfos.add(new BasicInfo(new RadioButton(this), new RadioButton(this)));

        BasicinfoHashMap.put(BasicListDataHeaderList.get(0), BasicInfos); // Header, Child data
        BasicinfoHashMap.put(BasicListDataHeaderList.get(1), TradeBasicInfos); // Header, Child data
        BasicinfoHashMap.put(BasicListDataHeaderList.get(2), LogisticTradeBasicInfos); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
