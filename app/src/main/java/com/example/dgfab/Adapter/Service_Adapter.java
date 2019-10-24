package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.StrictMode;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.AllParsings.GET_Services_Data;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;


public class Service_Adapter  extends RecyclerView.Adapter<Service_Adapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Servicenames = new ArrayList<>();
    public static ArrayList<String> Service_names = new ArrayList<>();
    int pos_try;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    int chky,chkn;
    public List<GET_Services_Data> Doc;
    private ProgressDialog progressBar;

    public Service_Adapter(Context context, List<GET_Services_Data> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_of_doc ;
        ImageView ser_image;
        public TextView  down_btn, email_to;
        CheckBox namechk;

        public MyViewHolder(View itemView) {
            super(itemView);
            name_of_doc = (TextView) itemView.findViewById(R.id.name);
            ser_image =  itemView.findViewById(R.id.ser_image);
            namechk =  itemView.findViewById(R.id.namechk);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder( final MyViewHolder holder,  final   int position) {
        final GET_Services_Data get_services_data ;
        this.pos_try = position;

        get_services_data = Doc.get(pos_try);
        Log.e("Position","is "+pos_try);
        document = get_services_data.getService();
        StrictMode.setVmPolicy(builder.build());
//        holder.namechk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                {
//
//                }
//            }
//        });
        holder.name_of_doc.setText(get_services_data.getService());
//        Glide.with(mContext)
//                .load("https://neareststore.in/uploads/"+get_services_data.getImage())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        // log exception
//                        Log.e("TAG", "Error loading image", e);
//                        return false; // important to return false so the error placeholder can be placed
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(holder.ser_image);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.namechk.isChecked() == false) {
//                    Servicenames.add(get_services_data.getId());
//                    Service_names.add(get_services_data.getService());
//                    holder.namechk.setChecked(true);
//
//                    Toast.makeText(mContext, "services "+Servicenames, Toast.LENGTH_SHORT).show();
//                    Log.e("Services_add",""+Servicenames);
//                    Log.e("Services_add_name",""+Service_names);
//                }
//                else {
//                    Servicenames.remove(get_services_data.getId());
//                    Service_names.remove(get_services_data.getService());
//                    holder.namechk.setChecked(false);
//
//                    Log.e("Services_update",""+Servicenames);
//                }
//            }
//        });
//        holder.name_of_doc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.namechk.isChecked() == false) {
//                    Servicenames.add(get_services_data.getId());
//                    Service_names.add(get_services_data.getService());
//                    holder.namechk.setChecked(true);
//
//                    Toast.makeText(mContext, "services "+Servicenames, Toast.LENGTH_SHORT).show();
//                    Log.e("Services_add",""+Servicenames);
//                    Log.e("Services_add_name",""+Service_names);
//                }else {
//                    Servicenames.remove(get_services_data.getId());
//                    Service_names.remove(get_services_data.getService());
//                    holder.namechk.setChecked(false);
//
//                    Log.e("Services_update",""+Servicenames);
//                }
//
//            }
//        });


        holder.namechk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.namechk.isChecked() == true) {
                    Servicenames.add(get_services_data.getId());
                    Service_names.add(get_services_data.getService());
                    holder.namechk.setChecked(true);

                    Toast.makeText(mContext, "services "+Servicenames, Toast.LENGTH_SHORT).show();
                    Log.e("Services_add",""+Servicenames);
                    Log.e("Services_add_name",""+Service_names);
                }else {
                    Servicenames.remove(get_services_data.getId());
                    Service_names.remove(get_services_data.getService());
                    holder.namechk.setChecked(false);

                    Log.e("Services_update",""+Servicenames);
                }

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
