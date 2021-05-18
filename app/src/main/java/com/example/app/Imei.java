package com.example.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Imei extends AppCompatActivity {

    TelephonyManager tm;
    TextView imeitxt;
    Button imeibtn;
    String imei;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei);

        imeitxt = (TextView)findViewById(R.id.imei);
        imeibtn = (Button)findViewById(R.id.getimeibtn);

        int permisI = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE);

        if(permisI == PackageManager.PERMISSION_GRANTED){
            tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId().toString();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},123);
        }

        imeibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imeitxt.setText(imei);
            }
        });

    }
}
