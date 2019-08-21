package com.example.dgfab.Business_fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.TheirProductsAdapter;
import com.example.dgfab.AllParsings.GetProducts;
import com.example.dgfab.AllParsings.GetProductsData;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dgfab.Connections.SeenProfile.Theirid;

public class ProductFrag extends Fragment {
   // ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView add_product;
    TextView chatnow,inquries;
    TheirProductsAdapter theirProducts;
    List<GetProductsData> getProductsData = new ArrayList<>();
    RecyclerView seenprorec;

    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product ,container,false);
        seenprorec = view.findViewById(R.id.seenprorec);
        sessionManager = new SessionManager(getActivity());
        int id = sessionManager.getUS();
        Log.e("product for" , ""+Theirid);
        MyProducts();
        return view;

    }

    private void MyProducts() {


        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("sortname is" , ""+sessionManager.getUS());
        Call<GetProducts> Get_All_Country_New = AbloutApi.GET_PRODUCTS_CALL(String.valueOf(sessionManager.getUS()));
        Get_All_Country_New.enqueue(new Callback<GetProducts>() {
            @Override
            public void onResponse(Call<GetProducts> call, Response<GetProducts> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        for(int i=0;i<response.body().getData().size();i++) {
                            getProductsData.add(new GetProductsData(response.body().getData().get(i).getProductId(),response.body().getData().get(i).getUserId()
                                    ,response.body().getData().get(i).getCategory(),response.body().getData().get(i).getSubcategory(),response.body().getData().get(i).getProductName(),
                                    response.body().getData().get(i).getDescription(),response.body().getData().get(i).getMrp(),response.body().getData().get(i).getPrice(),
                                    response.body().getData().get(i).getQty(),response.body().getData().get(i).getImage(),response.body().getData().get(i).getImage(),
                                    response.body().getData().get(i).getStatus(),response.body().getData().get(i).getId(),response.body().getData().get(i).getTitle(),
                                    response.body().getData().get(i).getDiscription(),response.body().getData().get(i).getDiscount()));
                        }
                        theirProducts = new TheirProductsAdapter(getActivity(),getProductsData);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        seenprorec.setLayoutManager(llm);
                        seenprorec.setAdapter(theirProducts);
//                        for(int i=0;i<response.body().getData().size(); i++)
//                        {
//                            if(response.body().getData().get(i).getName().length() !=0) {
//                                Log.d("sortname is" , response.body().getData().get(i).getName());
//                                if(!response.body().getData().get(i).getUstatus().equals("0")) {
//                                   // commingRequestData.add(new CommingRequestData(response.body().getData().get(i).getId(), response.body().getData().get(i).getBrandName(), response.body().getData().get(i).getName(), response.body().getData().get(i).getImage(), response.body().getData().get(i).getReceiverid(), response.body().getData().get(i).getSenderid(), response.body().getData().get(i).getUstatus()));
//                                }
//                            }
//                        }
//                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                        llm.setOrientation(LinearLayoutManager.VERTICAL);
//                        connectionsOnlyAdapter = new ConnectionsOnlyAdapter(getActivity() , commingRequestData);
//                        myconsreq.setLayoutManager(llm);
//                        myconsreq.setAdapter(connectionsOnlyAdapter);
                        // countyed.showDropDown();
                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetProducts> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//        // get the listview
////        expListView = (ExpandableListView) view.findViewById(R.id.ex_lv);
////        add_product=view.findViewById(R.id.add_product);
////        chatnow=view.findViewById(R.id.chatnow);
////        inquries=view.findViewById(R.id.inquries);
////
////        add_product.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent=new Intent(getContext(), AddProduct_Activity.class);
////                startActivity(intent);
////            }
////        });
////
////        // preparing list data
////        prepareListData();
////
////        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//        //listAdapter = new com.ics.dgfabapp.adapter.ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
//
//        // setting list adapter
//        //expListView.setAdapter(listAdapter);
//
//        // Listview Group click listener
//
//
//        // Listview Group expanded listener
//
//
//
//
//
//
//
//
//
//        super.onViewCreated(view, savedInstanceState);
//
//    }


}
