package com.example.dgfab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dgfab.BusinessDashboard.AddProductWay;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;


public class AddMorekeysAdapter extends RecyclerView.Adapter<AddMorekeysAdapter.MyViewHolder> {
    private Context mContext;
   // public static ArrayList<String> Myproducttiles = new ArrayList<>();
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    public List<AddNews> Doc;
    private AddNews addNews141;

    public AddMorekeysAdapter(Context context, List<AddNews> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView manu_img;
        Button removenew , addnewf;
        EditText addnewkey;

        public MyViewHolder(View itemView) {
            super(itemView);
         //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
           // manu_img =  itemView.findViewById(R.id.manu_img);
            removenew =  itemView.findViewById(R.id.removenew);
            addnewf =  itemView.findViewById(R.id.addnewf);
            addnewkey =  itemView.findViewById(R.id.addnewkey);
       //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }


    @Override
    public AddMorekeysAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newkeywords, parent, false);
        return new AddMorekeysAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final AddMorekeysAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;

        holder.removenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    //Myproducttiles.remove(position);
//           //         Myproductsdescrip.remove(position);
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
                Doc.remove(position);
                notifyDataSetChanged();
                if (position == 0) {
                    //  Toast.makeText(mContext, "xyz "+holder.addnewkey.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("filter_string");
                    //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                    intent.putExtra("removekey", "2");
                    // put your all data using put extra

                    LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
                }

            }
        });
        holder.addnewf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addnewkey.setText(holder.addnewkey.getText().toString());
                addNews141.setNames(holder.addnewkey.getText().toString());

              //  Myproducttiles.add(holder.addnewkey.getText().toString());
            //    Myproducttiles.add(holder.addnewkey.getText().toString());
                //Toast.makeText(mContext, "xyz "+holder.addnewkey.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("filter_string");
             //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                intent.putExtra("key", holder.addnewkey.getText().toString());
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
        return  position;
    }
}
