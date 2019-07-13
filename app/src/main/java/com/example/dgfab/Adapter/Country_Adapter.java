package com.example.dgfab.Adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dgfab.Java_Adapter_Files.Country_files;
import com.example.dgfab.R;
import com.example.dgfab.LoginandReg.RegistrationActivityTwo;
import com.example.dgfab.Utils.SVG_Utils;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.dgfab.LoginandReg.RegistrationActivityTwo.Mycountry;
import static com.example.dgfab.LoginandReg.RegistrationActivityTwo.dataregistration;

public class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Servicenames = new ArrayList<>();
    int pos_try;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;

    public List<Country_files> Doc;
    private ProgressDialog progressBar;


    public Country_Adapter(Context context, List<Country_files> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_of_doc ;
        ImageView ser_image;
        public TextView  down_btn, email_to;
        RadioButton namechk;
        CardView country_card;
        LinearLayout ll_country;

        public MyViewHolder(View itemView) {
            super(itemView);
            name_of_doc = (TextView) itemView.findViewById(R.id.name);
            ser_image =  itemView.findViewById(R.id.flag_image);
            namechk =  itemView.findViewById(R.id.namechk);
            ll_country =  itemView.findViewById(R.id.ll_country);

        }
    }

    @Override
    public Country_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contry_list, parent, false);
        return new Country_Adapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final Country_Adapter.MyViewHolder holder, final   int position) {
        final Country_files get_services_data ;
        this.pos_try = position;

        get_services_data = Doc.get(pos_try);
        Log.e("Position","is "+pos_try);
        document = get_services_data.getNumber();
        StrictMode.setVmPolicy(builder.build());
        holder.name_of_doc.setText(get_services_data.getName().toString());

        Log.e("Flag_adapter", ""+get_services_data.getFlag());

       // GlideToVectorYou.justLoadImage((Activity) mContext, Uri.parse(get_services_data.getFlag()), holder.ser_image);


        //*********************************************************************************************

        // SVG_Utils.fetchSvg(mContext, get_services_data.getFlag(), holder.ser_image);
        //*******************
//        SvgLoader.pluck()
//                .with((Activity) mContext)
//                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//                .load(get_services_data.getFlag(), holder.ser_image);

        //******************************************************
//        Picasso.get()
//                .load(get_services_data.getFlag())
//                //.placeholder(R.drawable.user_placeholder)
//                .into(holder.ser_image);

//        Glide.with(mContext)
//                .load(get_services_data.getFlag())
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
//**************************************************************************************************









        //*********************************************************************************************

        holder.ll_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycountry = get_services_data.getName();
                Toast.makeText(mContext, ""+Doc.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext , RegistrationActivityTwo.class);
                intent.putExtra("mycountry" , get_services_data.getName());
                v.getContext().startActivity(intent);
                dataregistration.setCountry(Mycountry);
            }
        });

        holder.name_of_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycountry = get_services_data.getName();
                Toast.makeText(mContext, ""+Doc.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext , RegistrationActivityTwo.class);
                intent.putExtra("mycountry" , get_services_data.getName());
                v.getContext().startActivity(intent);
                dataregistration.setCountry(Mycountry);

            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, ""+Doc.get(position).getNumber(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext , RegistrationActivityTwo.class);
//                intent.putExtra("mycont" , Doc.get(position).getNumber());
//                v.getContext().startActivity(intent);
//            }
//        });
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


