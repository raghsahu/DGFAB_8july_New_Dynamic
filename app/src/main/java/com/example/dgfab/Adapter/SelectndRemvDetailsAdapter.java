package com.example.dgfab.Adapter;

import android.content.Context;
import android.os.StrictMode;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class SelectndRemvDetailsAdapter extends RecyclerView.Adapter<SelectndRemvDetailsAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<AddNews> Doc;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private AddNews addNews141;

    public SelectndRemvDetailsAdapter(Context context, List<AddNews> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.removedetails, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;
        Log.e("Name", "" + addNews141.getNamesDetails());
        holder.sletxt.setText(addNews141.getNamesDetails());
//        try{
//            if(!addNews141.getSletxt().isEmpty()){
//
//            }
//        }catch (Exception e)
//        {
//            holder.sletxt.setText(addNews141.getSletxt());
//            e.printStackTrace();
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doc.remove(position);
                notifyDataSetChanged();
                Toast.makeText(mContext, "Removing", Toast.LENGTH_SHORT).show();
            }
        });
//        searching_manufacturers_data = Doc.get(pos_try);
//        Log.e("Position","is "+pos_try);
//        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());

    }

    @Override
    public int getItemCount() {
        return Doc.size();
    }


//    private void Try_Sent_Reuqest(AddNewsAdapter.MyViewHolder holder, Context context, int us, String id) {
//        Log.d("us is" ,""+ us);
//        Log.d("their is" ,""+ id);
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Getting Country");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//        Log.d("sortname is" , id);
//        Call<Connection_Requests> Get_All_Country_New = AbloutApi.CONNECTION_REQUESTS_CALL( String.valueOf(us),id);
//        Get_All_Country_New.enqueue(new Callback<Connection_Requests>() {
//            @Override
//            public void onResponse(Call<Connection_Requests> call, Response<Connection_Requests> response) {
//                Log.e("getcity" , ""+response.toString());
//                if (response!=null){
//                    Log.e("Get_City",""+response.body().getResponce());
//                    try {
//                        if(response.body().getResponce() == true)
//                        {
//                            Toast.makeText(context, ""+response.body().getData(), Toast.LENGTH_SHORT).show();
//
//                            holder.conbtn.setText("Request Sent");
//                        }else {
//                            Toast.makeText(context, "Problem in sending request", Toast.LENGTH_SHORT).show();
//                        }
//                        // countyed.showDropDown();
//                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Connection_Requests> call, Throwable t) {
//                progressDialog.dismiss();
//                Log.e("error_country",""+t.getMessage());
//                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sletxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            sletxt = itemView.findViewById(R.id.sletxt);

            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}
