package com.example.dgfab.BusinessDashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dgfab.Adapter.AddMorePayoptions;
import com.example.dgfab.Adapter.AddMoredetailesAdapter;
import com.example.dgfab.Adapter.AddMorekeysAdapter;
import com.example.dgfab.Adapter.AddNewsAdapter;
import com.example.dgfab.Adapter.SelectandProsAdapter;
import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Adapter.SelectndRemvDetailsAdapter;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductWay extends AppCompatActivity {
    Button addmorekey, nxt, addmrdet, addmorepro, addmantype;
    RecyclerView newkey, addmoredet, addmorepay, showkeys, showmoredeatils, addepros, showpays;
    //    List<String> AllKeywordsList,AlldetailsList,AllpayList;
    List<AddNews> addNewsList = new ArrayList<AddNews>();
    List<AddNews> AllMoredetailesList  = new ArrayList<>();
    List<AddNews> AllMorepayList  = new ArrayList<>();
    List<AddNews> SelectandRemoveList = new ArrayList<>();
    List<AddNews> SelectandRemovedetailsList = new ArrayList<>();
    List<AddNews> SelectandMainproList = new ArrayList<>();
    SelectandRemoveAdapter selectandRemoveAdapter;
    SelndRemradioAdapter selndRemradioAdapter;
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
                Toast.makeText(context, "Done nothing", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Broadcast received !", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Broadcast received KeysDetails!" + str, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Broadcast received Other's Details !" + dtr, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context, "it's null", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Broadcast received Other's Details !" + maintits, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "we got it", Toast.LENGTH_SHORT).show();
                }

            }
            //  tv.setText("Broadcast received !");

        }
    };
    private RecyclerView mainrec;
    private AddNewsAdapter addNewsAdapter;
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
      //  ++++++++++++++++++++++++++++++++all clicks+++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

        //+++++++++++++++++++++++++++++++++Add More Main Cats+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        addmantype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if(AllMorepayList.size() >0) {
                    AllMorepayList.add(new AddNews(4, new RadioButton(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmorepay.setLayoutManager(llm);
                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmorepay.swapAdapter(addMorePayoptions, false);
                    addMorePayoptions.notifyItemInserted(0);
                    addmorepay.requestFocus();
                }else {
                    AllMorepayList.add(new AddNews(4, new RadioButton(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmorepay.setLayoutManager(llm);
                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmorepay.setAdapter(addMorePayoptions);
                    addmorepay.requestFocus();
                    addmorepay.setVisibility(View.GONE);
                }
            }
        });
        ////////////////////////////////add pore /////////////////////////////////////////
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  SelectandRemoveList
                //SelectandRemovedetailsList
                //SelectandMainproList

//                Intent intent = new Intent("filter_string");
//                intent.putExtra("key", "My Data");
//                // put your all data using put extra
//
//                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
//               for(int p=0;p<addMorekeysAdapter.getItemCount();p++)
//               {
//                   Toast.makeText(AddProductWay.this, "key s details"+addMorekeysAdapter.Doc.get(p).getName().getText().toString(), Toast.LENGTH_SHORT).show();
//                   Toast.makeText(AddProductWay.this, "Name detaikles"+addMoredetailsAdapter.Doc.get(p).getNameDetails().getText().toString(), Toast.LENGTH_SHORT).show();
//                   AllKeywordsList.add(addMorekeysAdapter.Doc.get(p).getName().getText().toString());
//                 // Toast.makeText(AddProductWay.this, ""+addMorekeysAdapter.Doc.get(p).getTilte().getText(), Toast.LENGTH_SHORT).show();
//               }
            }
        });
    }

    private void AllViwsbyid() {
        addmorekey = findViewById(R.id.addmorekey);
        //Recylerviews+++++++++++++++++++++++++++++++++
        mainrec = findViewById(R.id.mainrec);
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

    //+++++++++++++++++++++++++++++++++++++++++end of on pause+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
