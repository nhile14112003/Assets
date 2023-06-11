package com.example.broadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvBateryPercent, tvChargeMode;
    LinearLayout layout;
    BroadcastReceiver bateryBroadcast;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBateryPercent = findViewById(R.id.bateryPercent);
        tvChargeMode = findViewById(R.id.chargeMode);
        layout = findViewById(R.id.background);
        intentFilterAndBroadcast();
    }
    private void intentFilterAndBroadcast(){
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        bateryBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().compareTo(intent.ACTION_BATTERY_CHANGED) == 0){
                    int batteryPercent = intent.getIntExtra("level", 0);
                    tvBateryPercent.setText(String.valueOf(batteryPercent) + "%");
                    if (batteryPercent > 20) {
                        layout.setBackgroundColor(Color.GREEN);
                    }
                    else{
                        layout.setBackgroundColor(Color.YELLOW);
                    }
                    setChargeState(intent);
                }
            }
        };
    }
    private void setChargeState(Intent intent) {
        int chargeStatus = intent.getIntExtra("status", -1);
        switch(chargeStatus){
            case BatteryManager.BATTERY_STATUS_CHARGING:
                tvChargeMode.setText("Charging");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                tvChargeMode.setText("Not charging");
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        registerReceiver(bateryBroadcast,intentFilter);
    }
    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(bateryBroadcast);
    }
}
