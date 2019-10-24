package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;

import androidx.recyclerview.widget.RecyclerView;
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
import com.example.dgfab.AllParsings.Accept_Decline;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.Friendsdata;
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

public class FriendsAdapter   extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Servicenames = new ArrayList<>();
    public static ArrayList<String> Service_names = new ArrayList<>();
    int pos_try;
    SessionManager sessionManager;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    int chky,chkn;
    public List<Friendsdata> Doc;
    private ProgressDialog progressBar;

    public FriendsAdapter(Context context, List<Friendsdata> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname , bra_bus ;
        ImageView manu_img;
        Button conbtn , discon;

        public MyViewHolder(View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            manu_img =  itemView.findViewById(R.id.manu_img);
            conbtn =  itemView.findViewById(R.id.conbtn);
            discon =  itemView.findViewById(R.id.disscon);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doneaccepting, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Friendsdata friendsdata ;
        this.pos_try = position;

        friendsdata = Doc.get(pos_try);
        Log.e("Position","is "+pos_try);
        document = friendsdata.getName();
        StrictMode.setVmPolicy(builder.build());


        holder.manu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , SeenProfile.class);
                intent.putExtra("whatsname" , friendsdata.getName());
                intent.putExtra("theirid" , friendsdata.getReceiverid());
                Log.e("reciverec data is ", ""+Doc.get(position).getSenderid());
                if(holder.conbtn.getText().equals("Connected")) {
                    intent.putExtra("Connected", "Connected" );
                }else {
                    intent.putExtra("Connected" , "Connect Now");
                }
                v.getContext().startActivity(intent);
            }
        });
        holder.discon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(v.getContext());
                TryDisconnect(holder,v.getContext() ,sessionManager.getUS() ,Doc.get(position).getSenderid() ,position ,2);
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
        holder.mname.setText(friendsdata.getName());
//        holder.bra_bus.setText(searching_manufacturers_data.getCity());
        holder.conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(v.getContext());
                if(holder.conbtn.getText().equals("Connect")) {
                    TryDisconnect(holder,v.getContext() ,sessionManager.getUS() ,Doc.get(position).getSenderid() ,position ,1);
                }else {
                    Toast.makeText(mContext, "Connection request already sent", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void TryDisconnect(MyViewHolder holder, Context context, int u_type, String receiverid, int position, int i) {
        Log.d("us is" ,""+ u_type);
        Log.d("their is" ,""+ u_type);
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
        Log.d("sortname is disvc" , ""+ u_type);
        Log.d("receiverid is conx" , ""+ receiverid);
        Call<Accept_Decline> Get_All_Country_New = AbloutApi.ACCEPT_DECLINE_CALL( receiverid,String.valueOf(u_type),String.valueOf(i));
        Get_All_Country_New.enqueue(new Callback<Accept_Decline>() {
            @Override
            public void onResponse(Call<Accept_Decline> call, Response<Accept_Decline> response) {
                Log.e("getcity" , ""+response.toString());
                if (response!=null){
                    Log.e("Get_City",""+response.body().getResponce());
                    try {
                        if(response.body().getResponce() == true)
                        {
                            //    if(response.body().getData().)
                            Toast.makeText(context, ""+response.body().getData(), Toast.LENGTH_SHORT).show();
                            if(i==1) {
                                holder.conbtn.setText("Connected");
                            }else {
                                Doc.remove(position);
                                notifyDataSetChanged();
                            }
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
            public void onFailure(Call<Accept_Decline> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country",""+t.getMessage());
                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void Try_Sent_Reuqest(ConnectionsOnlyAdapter.MyViewHolder holder, Context context, int us, String id) {
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
        Call<Connection_Requests> Get_All_Country_New = AbloutApi.CONNECTION_REQUESTS_CALL( String.valueOf(us),id);
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