package com.example.dgfab.Business_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.BusinessDashboard.AddProduct_Activity;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductFrag extends Fragment {
   // ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView add_product;
    TextView chatnow,inquries;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product ,container,false);

        return inflater.inflate(R.layout.fragment_product, container, false);

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.ex_lv);
        add_product=view.findViewById(R.id.add_product);
        chatnow=view.findViewById(R.id.chatnow);
        inquries=view.findViewById(R.id.inquries);

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddProduct_Activity.class);
                startActivity(intent);
            }
        });

        // preparing list data
        prepareListData();
//
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        //listAdapter = new com.ics.dgfabapp.adapter.ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        //expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
//                Intent intent = new Intent(v.getContext() , Main3Activity.class);
//                intent.putExtra("namewa" ,  listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
//                startActivity(intent);
                return false;
            }
        });

        chatnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ChatActivity.class);
//                startActivity(intent);
            }
        });

        inquries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), InquiryScreen.class);
//                startActivity(intent);
            }
        });


        super.onViewCreated(view, savedInstanceState);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Mobiles & Tablets");
        listDataHeader.add("Electronics");
        listDataHeader.add("Fashion");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Sumsung");
        top250.add("IBall");
        top250.add("IPhone");
        top250.add("Micromax");
        top250.add("China Phone");
        top250.add("SmartPhones");
        top250.add("Feacher Phones");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Laptop Stores");
        nowShowing.add("Desktop Stores");
        nowShowing.add("Camera & Accessories");
        nowShowing.add("Storage Device");
        nowShowing.add("Mobile Accessories");
        nowShowing.add("Gaming");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Mens Fashion Store");
        comingSoon.add("Women Fashion Store");
        comingSoon.add("Kids Wear");
        comingSoon.add("Sandals & Floters");
        comingSoon.add("Jewellery");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
