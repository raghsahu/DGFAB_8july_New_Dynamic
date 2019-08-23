package com.example.dgfab.Adapter;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class SelectandProsAdapter extends RecyclerView.Adapter<SelectandProsAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<AddNews> Doc;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private AddNews addNews141;

    public SelectandProsAdapter(Context context, List<AddNews> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public SelectandProsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showmainpros, parent, false);
        return new SelectandProsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectandProsAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;
        Log.e("Name", "" + addNews141.getNamesDetails());
        holder.seletits.setText(addNews141.getTiltes());
        holder.seldes.setText(addNews141.getValues());
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
        TextView seletits, seldes;
        ImageView remove;

        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            seletits = itemView.findViewById(R.id.seltitle);
            seldes = itemView.findViewById(R.id.seldes);
            remove = itemView.findViewById(R.id.remove);

            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}
