package com.example.dgfab.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class InfoBroadcast extends BroadcastReceiver {
    public InfoBroadcast() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        Log.d("API123",""+intent.getAction());

        if(intent.getAction().equals("my.action.string")) {
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
           setResultData("Hiiiiiiiiiiii");
        }else {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                try {
                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
            }
        }
    }
}
