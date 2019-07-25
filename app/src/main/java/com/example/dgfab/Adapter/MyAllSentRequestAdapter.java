package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.All_Sent_Request_Data;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.DestroyingConnections;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.Connections.SeenProfile;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAllSentRequestAdapter extends RecyclerView.Adapter<MyAllSentRequestAdapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Servicenames = new ArrayList<>();
    public static ArrayList<String> Service_names = new ArrayList<>();
    int pos_try;
    SessionManager sessionManager;
   Boolean deleornot;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    boolean bx;
    public List<All_Sent_Request_Data> Doc;
    private ProgressDialog progressBar;

    public MyAllSentRequestAdapter(Context context, List<All_Sent_Request_Data> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname , bra_bus;
        ImageView manu_img;
        Button conbtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.mname);
            bra_bus =  itemView.findViewById(R.id.bra_bus);
            manu_img =  itemView.findViewById(R.id.manu_img);
            conbtn =  itemView.findViewById(R.id.conbtn);

        }
    }


    @Override
    public MyAllSentRequestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newdesign, parent, false);
        return new MyAllSentRequestAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyAllSentRequestAdapter.MyViewHolder holder, final int position) {
        final All_Sent_Request_Data searching_manufacturers_data ;
        this.pos_try = position;

        searching_manufacturers_data = Doc.get(pos_try);
        Log.e("Position","is "+pos_try);
        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , SeenProfile.class);
                intent.putExtra("whatsname" , searching_manufacturers_data.getName());
                v.getContext().startActivity(intent);
            }
        });

        holder.manu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , SeenProfile.class);
                intent.putExtra("whatsname" , searching_manufacturers_data.getName());
                intent.putExtra("Connected" , "Connect Now");
                v.getContext().startActivity(intent);
            }
        });
//        holder.namechk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                {
//
//                }
//            }
//        });
        holder.mname.setText(searching_manufacturers_data.getName());
//        holder.bra_bus.setText(searching_manufacturers_data.getCity());
        holder.conbtn.setText("Request Sent");
        holder.conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(v.getContext());
                if(holder.conbtn.getText().equals("Connect")) {
            //        Try_Sent_Reuqest(holder,v.getContext(), sessionManager.getUS(), searching_manufacturers_data.getId());
                }else if(holder.conbtn.getText().equals("Request Sent")){
                    Toast.makeText(mContext, "Destroying Connection", Toast.LENGTH_SHORT).show();
                     TRYTODESTROY(v.getContext(),holder,sessionManager.getUS() , searching_manufacturers_data.getId(),position);
//                    if(deleornot)
//                    {
//                        Doc.remove(position);
//                        notifyDataSetChanged();
//                    }else {
//                        Toast.makeText(mContext, "something went wrong", Toast.LENGTH_SHORT).show();
//                    }
                }

            }
        });
    }

    private Boolean TRYTODESTROY(Context context, MyViewHolder holder, int us, String id, int position) {
        Log.d("us is" ,""+ us);
        Log.d("their is" ,""+ id);

        ProgressDialog progressDialog = new ProgressDialog(context);
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
        Log.d("sortname is" , id);
        Call<DestroyingConnections> Get_All_Country_New = AbloutApi.DESTROYING_CONNECTIONS_CALL( id);
        Get_All_Country_New.enqueue(new Callback<DestroyingConnections>() {
            @Override
            public void onResponse(Call<DestroyingConnections> call, Response<DestroyingConnections> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        if(response.body().getResponce() == true)
                        {
                            Toast.makeText(context, "request is "+response.body().getResponce(), Toast.LENGTH_SHORT).show();
                            holder.conbtn.setText("Request Retrieved");
                            bx = true;
                            Doc.remove(position);
                            notifyDataSetChanged();

                        }else {
                            Toast.makeText(context, "Problem in sending request", Toast.LENGTH_SHORT).show();
                            bx = false;
                        }
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
            public void onFailure(Call<DestroyingConnections> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

      return bx;
    }

    private void Try_Sent_Reuqest(MyAllSentRequestAdapter.MyViewHolder holder, Context context, int us, String id) {
        Log.d("us is" ,""+ us);
        Log.d("their is" ,""+ id);
        ProgressDialog progressDialog = new ProgressDialog(context);
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
        Log.d("sortname is" , id);
        Call<Connection_Requests> Get_All_Country_New = AbloutApi.CONNECTION_REQUESTS_CALL(String.valueOf(us) ,id);
        Get_All_Country_New.enqueue(new Callback<Connection_Requests>() {
            @Override
            public void onResponse(Call<Connection_Requests> call, Response<Connection_Requests> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        if(response.body().getResponce() == true)
                        {
                            Toast.makeText(context, ""+response.body().getData(), Toast.LENGTH_SHORT).show();
                            holder.conbtn.setText("Request Sent");

                        }else {
                            Toast.makeText(context, "Problem in sending request", Toast.LENGTH_SHORT).show();
                        }
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
            public void onFailure(Call<Connection_Requests> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


    @Override
    public int getItemCount() {
        return Doc.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return  position;
    }
}
