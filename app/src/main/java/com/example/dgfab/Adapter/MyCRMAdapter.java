package com.example.dgfab.Adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.AllParsings.Accept_Decline;
import com.example.dgfab.AllParsings.CommingRequestData;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.Friendsdata;
import com.example.dgfab.BusinessDashboard.Business_CRM.CalenderActivity;
import com.example.dgfab.Connections.SeenProfile;
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

public class MyCRMAdapter extends RecyclerView.Adapter<MyCRMAdapter.MyViewHolder> {
    public List<Friendsdata> Doc;
    int pos_try;
    SessionManager sessionManager;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;
    int chky, chkn;
    private Context mContext;
    private ProgressDialog progressBar;

    public MyCRMAdapter(Context context, List<Friendsdata> doc) {
        mContext = context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    @Override
    public MyCRMAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycrm, parent, false);
        return new MyCRMAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyCRMAdapter.MyViewHolder holder, final int position) {
        final Friendsdata searching_manufacturers_data;
        this.pos_try = position;

        searching_manufacturers_data = Doc.get(pos_try);
        Log.e("Position", "is " + pos_try);
        holder.callid.setText(searching_manufacturers_data.getRemainder_time());
        holder.conscause.setText(searching_manufacturers_data.getRemainder_cause());
        Log.e("searching_ gettimr", "is " + searching_manufacturers_data.getRemainder_time());
        document = searching_manufacturers_data.getName();
        holder.comname.setText(searching_manufacturers_data.getName());
        holder.aidimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CalenderActivity.class);
                intent.putExtra("Friname", searching_manufacturers_data.getName());
                intent.putExtra("Friid", searching_manufacturers_data.getReceiverid());

                v.getContext().startActivity(intent);
            }
        });
        holder.phoneimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.e("Calling a Phone Number", "Call failed" + searching_manufacturers_data.getMobile().toString());
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + searching_manufacturers_data.getMobile().toString()));
                    v.getContext().startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Toast.makeText(mContext, "Calling a Phone Number", Toast.LENGTH_SHORT).show();
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                    Toast.makeText(mContext, "Calling Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        TextView comname, callid, conscause, meetId, chatId, messageId;
        ImageView aidimg, phoneimg;

        public MyViewHolder(View itemView) {
            super(itemView);
            comname = (TextView) itemView.findViewById(R.id.comnsname);
            messageId = (TextView) itemView.findViewById(R.id.messageId);
            chatId = (TextView) itemView.findViewById(R.id.chatId);
            meetId = (TextView) itemView.findViewById(R.id.meetId);
            aidimg = itemView.findViewById(R.id.aidimg);
            phoneimg = itemView.findViewById(R.id.phoneimg);
            callid = itemView.findViewById(R.id.callid);
            conscause = itemView.findViewById(R.id.conscause);


        }
    }
}
