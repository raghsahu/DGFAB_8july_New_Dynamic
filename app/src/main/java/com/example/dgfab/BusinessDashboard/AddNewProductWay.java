package com.example.dgfab.BusinessDashboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Expandable.BasicInfo;
import com.example.dgfab.Expandable.ExpandableListAdapter;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddNewProductWay extends AppCompatActivity {
    //    ExpandableListView basicinfoex;
    ExpandableListAdapter listAdapter;
    ExpandableListView basicinfoex;
    List<String> BasicListDataHeaderList;
    LinearLayout lifo;
    //    EditText editText;
//    HashMap<String, List<String>> listDataChild;
    HashMap<String, List<BasicInfo>> BasicinfoHashMap;

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
                TextView lblListHeader = (TextView) basicinfoex.getRootView()
                        .findViewById(R.id.lblListHeader);
                lifo.requestFocus();
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
        BasicInfos.add(new BasicInfo(this, new EditText(AddNewProductWay.this), new EditText(this), new EditText(this)
                , new EditText(this), new Button(this), new Button(this), new Button(this)
                , new Button(this), new RecyclerView(this), new RecyclerView(this), new Spinner(this), new Spinner(this)
                , new Spinner(this), new Spinner(this)));

        TradeBasicInfos.add(new BasicInfo(new RadioButton(this), new RadioButton(this)));
        TradeBasicInfos.add(new BasicInfo(new RadioButton(this), new RadioButton(this)));

//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");

//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");

//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");

        BasicinfoHashMap.put(BasicListDataHeaderList.get(0), BasicInfos); // Header, Child data
        BasicinfoHashMap.put(BasicListDataHeaderList.get(1), TradeBasicInfos); // Header, Child data
        BasicinfoHashMap.put(BasicListDataHeaderList.get(2), TradeBasicInfos); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
