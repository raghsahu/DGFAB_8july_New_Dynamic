package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.Connections.SeenProfile;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDirectoryAdapter extends RecyclerView.Adapter<SearchDirectoryAdapter.MyViewHolder> {
    public static ArrayList<String> Servicenames = new ArrayList<>();
    public static ArrayList<String> Service_names = new ArrayList<>();
    public List<Searching_Manufacturers_Data> Doc;
    int pos_try;
    SessionManager sessionManager;
    String name, email, com_name, password, address, mobile;
    ImageView manu_img;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    int chky, chkn;
    private Context mContext;
    private ProgressDialog progressBar;

    public SearchDirectoryAdapter(Context context, List<Searching_Manufacturers_Data> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public SearchDirectoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_manufacurers, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doneaccepting, parent, false);
        return new SearchDirectoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchDirectoryAdapter.MyViewHolder holder, final int position) {
        final Searching_Manufacturers_Data searching_manufacturers_data;
        this.pos_try = position;

        searching_manufacturers_data = Doc.get(pos_try);
        Log.e("Position", "is " + pos_try);
        Log.e("Status ", "is " + searching_manufacturers_data.getStatus());
        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());
        Glide.with(mContext).applyDefaultRequestOptions(RequestOptions.errorOf(R.drawable.personn)).load("https://neareststore.in/uploads/" + searching_manufacturers_data.getImg_Name()).into(holder.manu_img);

        holder.manu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SeenProfile.class);
                intent.putExtra("theirid", searching_manufacturers_data.getId());
                intent.putExtra("whatsname", searching_manufacturers_data.getName());
                v.getContext().startActivity(intent);
            }
        });
        holder.addreqimgli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(v.getContext());
                Toast.makeText(mContext, "Searcher", Toast.LENGTH_SHORT).show();
                if (searching_manufacturers_data.getReqstatus().equals("Pending")) {
                    Toast.makeText(mContext, "Please Wait for approval", Toast.LENGTH_SHORT).show();
                } else if (searching_manufacturers_data.getReqstatus().equals("Accepted")) {
                    holder.conbtn.setBackgroundColor(Color.GRAY);
                    Toast.makeText(mContext, "You are already Connected", Toast.LENGTH_SHORT).show();
                } else {
                    Try_Sent_Reuqest(holder, v.getContext(), sessionManager.getUS(), searching_manufacturers_data.getId());
                }
            }
        });
//        holder.addreqimg.setOnClickListener(new View.OnClickListener() {
//                                             @Override
//                                             public void onClick(View v) {
//             sessionManager = new SessionManager(v.getContext());
//              Toast.makeText(mContext, "Searcher", Toast.LENGTH_SHORT).show();
//               if(searching_manufacturers_data.getReqstatus().equals("Pending")) {
//                       Toast.makeText(mContext, "Please Wait for approval", Toast.LENGTH_SHORT).show();
//            }
//            else
//                 if(searching_manufacturers_data.getReqstatus().equals("Accepted"))
//                   {
//                         holder.conbtn.setBackgroundColor(Color.GRAY);
//                          Toast.makeText(mContext, "You are already Connected", Toast.LENGTH_SHORT).show();
//                  }
//               else {
//                 Try_Sent_Reuqest(holder,v.getContext(), sessionManager.getUS(), searching_manufacturers_data.getId());
//              }
//
//                                             }
//                                         }
//        );
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
        holder.conbtn.setText(searching_manufacturers_data.getReqstatus());
        if (searching_manufacturers_data.getReqstatus().equals("Accepted")) {
            Toast.makeText(mContext, "You are already Connected", Toast.LENGTH_SHORT).show();
            holder.addreqimg.setBackgroundResource(R.drawable.linkedup);
        } else if (searching_manufacturers_data.getReqstatus().equals("Pending")) {
            Toast.makeText(mContext, "Some Requests are Pending", Toast.LENGTH_SHORT).show();
            holder.addreqimg.setBackgroundResource(R.drawable.sent);
        } else {
//            Toast.makeText(mContext, "You are already Connected", Toast.LENGTH_SHORT).show();
            holder.addreqimg.setBackgroundResource(R.drawable.addconicon);
        }
//        holder.bra_bus.setText(searching_manufacturers_data.getCity());

    }

    private void Try_Sent_Reuqest(SearchDirectoryAdapter.MyViewHolder holder, Context context, int us, String id) {
        Log.d("us is", "" + us);
        Log.d("their is", "" + id);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("sortname is", id);
        Call<Connection_Requests> Get_All_Country_New = AbloutApi.CONNECTION_REQUESTS_CALL(String.valueOf(us), id);
        Get_All_Country_New.enqueue(new Callback<Connection_Requests>() {
            @Override
            public void onResponse(Call<Connection_Requests> call, Response<Connection_Requests> response) {
                Log.e("getcity", "" + response.toString());
                if (response != null) {
                    Log.e("Get_City", "" + response.body().getResponce());
                    try {
                        if (response.body().getResponce() == true) {
                            Toast.makeText(context, "" + response.body().getData(), Toast.LENGTH_SHORT).show();
                            holder.conbtn.setText("Request Sent");
                            holder.addreqimg.setBackgroundResource(R.drawable.pentimeleft);
                        } else {
                            Toast.makeText(context, "Problem in sending request", Toast.LENGTH_SHORT).show();
                        }
                        // countyed.showDropDown();
                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Connection_Requests> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country", "" + t.getMessage());
                Toast.makeText(context, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname, brx_bus;
        ImageView manu_img;
        LinearLayout addreqimgli;
        CircleImageView addreqimg;
        Button conbtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.mname);
            manu_img = itemView.findViewById(R.id.manu_img);
            conbtn = itemView.findViewById(R.id.conbtn);
            addreqimgli = itemView.findViewById(R.id.addreqimgli);
            //    manu_img = itemView.findViewById(R.id.manu_img);
            addreqimg = itemView.findViewById(R.id.addreqimg);

        }
    }
}