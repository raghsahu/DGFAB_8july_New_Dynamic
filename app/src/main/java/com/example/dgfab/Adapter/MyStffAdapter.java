package com.example.dgfab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.Java_Adapter_Files.MyStaffs;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class MyStffAdapter extends RecyclerView.Adapter<MyStffAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<MyStaffs> Doc;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private MyStaffs addNews141;

    public MyStffAdapter(Context context, List<MyStaffs> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public MyStffAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mystaff, parent, false);
        return new MyStffAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyStffAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;
        holder.namestff.setText(addNews141.getName());
        holder.emailstff.setText(addNews141.getEmail());
        holder.stffid.setText(addNews141.getDesignation());
        Glide.with(mContext).load("http://neareststore.in/uploads/staff/" + addNews141.getImage()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Toast.makeText(mContext, "Failed to load image", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Toast.makeText(mContext, "Image laod success", Toast.LENGTH_SHORT).show();
                return false;
            }
        }).into(holder.stffimg);

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
        ImageView stffimg;
        TextView namestff, emailstff, stffid;


        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            stffimg = itemView.findViewById(R.id.stffimg);
            namestff = itemView.findViewById(R.id.namestff);
            emailstff = itemView.findViewById(R.id.emailstff);
            stffid = itemView.findViewById(R.id.stffdes);
            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}
