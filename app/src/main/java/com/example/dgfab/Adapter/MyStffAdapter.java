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

import com.example.dgfab.Java_Adapter_Files.AddNews;
import com.example.dgfab.R;

import java.util.ArrayList;
import java.util.List;

public class MyStffAdapter extends RecyclerView.Adapter<MyStffAdapter.MyViewHolder> {
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    public List<AddNews> Doc;
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    private Context mContext;
    private AddNews addNews141;

    public MyStffAdapter(Context context, List<AddNews> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public MyStffAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newkeywords, parent, false);
        return new MyStffAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyStffAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;

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
        ImageView manu_img;
        Button removenew, addnewf;
        EditText addnewkey;

        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
            removenew = itemView.findViewById(R.id.removenew);
            addnewf = itemView.findViewById(R.id.addnewf);
            addnewkey = itemView.findViewById(R.id.addnewkey);
            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }
}
