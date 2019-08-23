package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.Accept_Decline;
import com.example.dgfab.AllParsings.CommingRequestData;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.Connections.SeenProfile;
import com.example.dgfab.Java_Adapter_Files.AddNews;
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

public class AddNewsAdapter extends RecyclerView.Adapter<AddNewsAdapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public static ArrayList<String> Myproductsdescrip = new ArrayList<>();
    int pos_try;
    SessionManager sessionManager;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    int chky,chkn;
    public List<AddNews> Doc;
    private ProgressDialog progressBar;

    public AddNewsAdapter(Context context, List<AddNews> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddNews addNews = Doc.get(position);
        this.pos_try = position;

        holder.removenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doc.remove(position);
                notifyDataSetChanged();
                if (position == 0) {
                    //  Toast.makeText(mContext, "xyz "+holder.addnewkey.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("filter_string");
                    //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                    intent.putExtra("removekey", "1");
                    // put your all data using put extra

                    LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
                }
//                try {
//                    Myproducttiles.remove(position);
//                    Myproductsdescrip.remove(position);
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }

            }
        });
        holder.addnewf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addtitle.setText(holder.addtitle.getText().toString());
                holder.adddes.setText(holder.adddes.getText().toString());
                addNews.setTiltes(holder.addtitle.getText().toString());
                addNews.setValues(holder.adddes.getText().toString());

                //  Myproducttiles.add(holder.addnewkey.getText().toString());
                //    Myproducttiles.add(holder.addnewkey.getText().toString());
                Toast.makeText(mContext, "xyz " + holder.adddes.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("filter_string");
                //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                intent.putExtra("maintits", holder.addtitle.getText().toString());
                intent.putExtra("mainvalue", holder.adddes.getText().toString());
                // put your all data using put extra

                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
//                holder.mname.getText().toString();
//                holder.mname.setText();
                // Myproductsdescrip.add(holder.adddes.getText().toString());
                try {
                    //   Toast.makeText(mContext, "" + Myproductsdescrip.get(position), Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(mContext, "" + Myproducttiles.get(position), Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "Working on api", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
//                Myproducttiles.add(holder.addtitle.getText().toString());
//                Myproductsdescrip.add(holder.adddes.getText().toString());
//                try {
//                    Toast.makeText(mContext, "" + Myproductsdescrip.get(position), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "" + Myproducttiles.get(position), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "Working on api", Toast.LENGTH_SHORT).show();
//                }catch (Exception e)
//                {
//
//                }
            }

        });
//        searching_manufacturers_data = Doc.get(pos_try);
//        Log.e("Position","is "+pos_try);
//        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_news, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname;
        ImageView manu_img;
        Button removenew, addnewf;
        EditText addtitle, adddes;

        public MyViewHolder(View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.mname);

            manu_img = itemView.findViewById(R.id.manu_img);
            removenew = itemView.findViewById(R.id.removenew);
            addnewf = itemView.findViewById(R.id.addnewf);
            addtitle = itemView.findViewById(R.id.addtitle);
            adddes = itemView.findViewById(R.id.adddes);


        }
    }





    private void Try_Sent_Reuqest(CommingConnAdapter.MyViewHolder holder, Context context, int us, String id) {
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