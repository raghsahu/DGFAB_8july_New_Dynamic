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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.GetProductsData;
import com.example.dgfab.BusinessDashboard.Inquiry_Activity;
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

import static com.example.dgfab.Connections.SeenProfile.Theirid;

public class TheirProductsAdapter extends RecyclerView.Adapter<TheirProductsAdapter.MyViewHolder> {
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
    public List<GetProductsData> Doc;
    private ProgressDialog progressBar;

    public TheirProductsAdapter(Context context, List<GetProductsData> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView protit , promrp,prodes ;
        //ImageView inqur;
        Button inqur , inqui;
        EditText addtitle,adddes;

        public MyViewHolder(View itemView) {
            super(itemView);
            protit = (TextView) itemView.findViewById(R.id.protit);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            promrp =  itemView.findViewById(R.id.promrp);
//            chats =  itemView.findViewById(R.id.chats);
//            inqui =  itemView.findViewById(R.id.inqui);
            prodes =  itemView.findViewById(R.id.prodes);
            inqur =  itemView.findViewById(R.id.inqur);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theirproducts, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final GetProductsData addNews = Doc.get(position) ;
        this.pos_try = position;
        holder.protit.setText(addNews.getProductName());
        holder.promrp.setText(addNews.getPrice());
        holder.prodes.setText(addNews.getDescription());
        try {
            if(Theirid.length() ==0)
            {
                holder.inqur.setVisibility(View.GONE);
            }
        }catch (Exception e)
        {
            holder.inqur.setVisibility(View.GONE);
        }

        holder.inqur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , Inquiry_Activity.class);
                v.getContext().startActivity(intent);

            }
        });
//        searching_manufacturers_data = Doc.get(pos_try);
//        Log.e("Position","is "+pos_try);
//        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());

    }





    private void Try_Sent_Reuqest(MyViewHolder holder, Context context, int us, String id) {
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

                  //          holder.conbtn.setText("Request Sent");
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