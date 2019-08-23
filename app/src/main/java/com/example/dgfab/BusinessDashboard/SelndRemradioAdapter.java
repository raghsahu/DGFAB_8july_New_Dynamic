package com.example.dgfab.BusinessDashboard;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Adapter.SelectandRemoveAdapter;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

class SelndRemradioAdapter extends RecyclerView.Adapter<SelndRemradioAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<AddNews> Doc;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private AddNews addNews141;

    public SelndRemradioAdapter(Context context, List<AddNews> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public SelndRemradioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paysops, parent, false);
        return new SelndRemradioAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelndRemradioAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;
        addNews141.setPayRadioString(addNews141.getPayRadioString());
        holder.radbtn.setText(addNews141.getPayRadioString());
        //Log.e("Name is", "" + addNews141.getNames());
        //  holder.sletxt.setText(addNews141.getNames());
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

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton radbtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            radbtn = itemView.findViewById(R.id.radbtn);

            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}
