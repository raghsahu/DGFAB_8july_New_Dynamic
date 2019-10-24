package com.example.dgfab.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dgfab.R;

public class Product_Success extends AppCompatActivity {
    private BroadcastReceiver mReceiver;
    protected BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(intent.hasExtra("type")){
                        Toast.makeText(context, "fjsdfsd"+intent.getStringExtra("type"), Toast.LENGTH_SHORT).show();
                        // Do some action
                    }
                }
            });

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__success);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("data-loaded"));
    }
}
