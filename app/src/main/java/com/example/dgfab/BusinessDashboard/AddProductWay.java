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
import android.widget.Toast;

import com.example.dgfab.Adapter.AddMorePayoptions;
import com.example.dgfab.Adapter.AddMoredetailesAdapter;
import com.example.dgfab.Adapter.AddMorekeysAdapter;
import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductWay extends AppCompatActivity {
    Button addmorekey,nxt,addmrdet,addmorepro;
    RecyclerView newkey,addmoredet,addmorepay,showkeys;
    List<String> AllKeywordsList;
    List<AddNews> AllMoredetailesList  = new ArrayList<>();
    List<AddNews> AllMorepayList  = new ArrayList<>();
    List<AddNews> selectandRemoveList  = new ArrayList<>();
    SelectandRemoveAdapter selectandRemoveAdapter;
    AddMorekeysAdapter addMorekeysAdapter;
    AddMoredetailesAdapter addMoredetailsAdapter;
    AddMorePayoptions addMorePayoptions;
    List<AddNews> addnewkeyList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // intent can contain anydata
            Log.d("sohail","onReceive called");
            Toast.makeText(context, "Broadcast received !", Toast.LENGTH_SHORT).show();
            if (intent != null) {
                String str = intent.getStringExtra("key");
                Toast.makeText(context, "Broadcast received !"+str, Toast.LENGTH_SHORT).show();
                if(selectandRemoveList.size() >0) {
                    selectandRemoveList.add(new AddNews(str));
//                    LinearLayoutManager llm = new LinearLayoutManager(context);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    showkeys.setLayoutManager(llm);
                    gridLayoutManager = new GridLayoutManager(getApplicationContext(),6);
                    showkeys.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                    showkeys.setLayoutManager(gridLayoutManager);
                //    showkeys.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
                    selectandRemoveAdapter = new SelectandRemoveAdapter(context, selectandRemoveList);
                    showkeys.swapAdapter(selectandRemoveAdapter, false);
                    selectandRemoveAdapter.notifyItemInserted(0);
                }else {
                    selectandRemoveList.add(new AddNews(str));
//                    LinearLayoutManager llm = new LinearLayoutManager(context);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    showkeys.setLayoutManager(llm);
                    gridLayoutManager = new GridLayoutManager(getApplicationContext(),6);
                    showkeys.addItemDecoration(new DividerItemDecoration(AddProductWay.this, LinearLayoutManager.VERTICAL));
                    showkeys.setLayoutManager(gridLayoutManager);

                    selectandRemoveAdapter = new SelectandRemoveAdapter(context, selectandRemoveList);
                    showkeys.setAdapter(selectandRemoveAdapter);
                    selectandRemoveAdapter.notifyItemInserted(0);
                }
                // get all your data from intent and do what you want
            }
          //  tv.setText("Broadcast received !");

        }
    };


    //    protected BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, final Intent intent) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if(intent.hasExtra("type")){
//                        Toast.makeText(context, "fjsdfsd"+intent.getData(), Toast.LENGTH_SHORT).show();
//                        // Do some action
//                    }
//                }
//            });
//
//        }
//    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add_product);

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(onNotice, new IntentFilter("filter_string"));
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
        addmorekey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addnewkeyList.size() >0) {
                    addnewkeyList.add(new AddNews(new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    newkey.setLayoutManager(llm);
                    addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                    newkey.swapAdapter(addMorekeysAdapter, false);
                    addMorekeysAdapter.notifyItemInserted(0);
                }else {
                    addnewkeyList.add(new AddNews(new EditText(v.getContext()), new Button(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    newkey.setLayoutManager(llm);
                    addMorekeysAdapter = new AddMorekeysAdapter(v.getContext(), addnewkeyList);
                    newkey.setAdapter(addMorekeysAdapter);
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
                    AllMoredetailesList.add(new AddNews(new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmoredet.setLayoutManager(llm);
                    addMoredetailsAdapter = new AddMoredetailesAdapter(v.getContext(), AllMoredetailesList);
                    addmoredet.swapAdapter(addMoredetailsAdapter, false);
                    addMoredetailsAdapter.notifyItemInserted(0);
                }else {
                    AllMoredetailesList.add(new AddNews(new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmoredet.setLayoutManager(llm);
                    addMoredetailsAdapter = new AddMoredetailesAdapter(v.getContext(), AllMoredetailesList);
                    addmoredet.setAdapter(addMoredetailsAdapter);
                }
            }
        });
////////////////////////////////////////add more
        addmorepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AllMorepayList.size() >0) {
                    AllMoredetailesList.add(new AddNews(new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmoredet.setLayoutManager(llm);
                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmoredet.swapAdapter(addMoredetailsAdapter, false);
                    addMoredetailsAdapter.notifyItemInserted(0);
                }else {
                    AllMoredetailesList.add(new AddNews(new EditText(v.getContext())));
                    LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    addmoredet.setLayoutManager(llm);
                    addMorePayoptions = new AddMorePayoptions(v.getContext(), AllMorepayList);
                    addmoredet.setAdapter(addMoredetailsAdapter);
                }
            }
        });
        ////////////////////////////////add pore /////////////////////////////////////////
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllKeywordsList = new ArrayList<>();

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
        newkey = findViewById(R.id.newkey);
        addmoredet = findViewById(R.id.addmoredet);
        showkeys = findViewById(R.id.showkeys);
        nxt = findViewById(R.id.nxt);
        addmorepay = findViewById(R.id.addmorepay);
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
        super.onDestroy();
    }

    //+++++++++++++++++++++++++++++++++++++++++end of on pause+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
