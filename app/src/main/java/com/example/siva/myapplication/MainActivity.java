package com.example.siva.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private Button send;
    private EditText phno,msg;
    private boolean mLocationPermissionsGranted=false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send=(Button) findViewById(R.id.send);
        phno=(EditText) findViewById(R.id.phno);
        msg=(EditText) findViewById(R.id.msg);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] permissions = {Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_SMS};

                if(ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(),
                        Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(),
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        mLocationPermissionsGranted = true;

                    }else{
                        ActivityCompat.requestPermissions(MainActivity.this,
                                permissions,
                                LOCATION_PERMISSION_REQUEST_CODE);
                    }
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this,
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
                if (mLocationPermissionsGranted) {
                    String phono = phno.getText().toString();
                    String mesg = msg.getText().toString();
                    int flag = 0;
                    if (phono == null || phono.length() == 0) {
                        Toast.makeText(MainActivity.this, "Enter a number to send", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                    if (mesg == null || mesg.length() == 0) {
                        Toast.makeText(MainActivity.this, "Enter a message to send", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                    if (flag == 0) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phono, null, mesg, null, null);
                        Toast.makeText(MainActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this,"Permission is not granted!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent sms_intent=getIntent();
        Bundle b=sms_intent.getExtras();
        TextView tv=(TextView)findViewById(R.id.txtview);

        if(b!=null){
            tv.setText(b.getString("sms_str"));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;
        if(ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(),
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(),
                    Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                if(ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), Manifest.permission.RECEIVE_SMS)==PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity: ", " " + mLocationPermissionsGranted);
                    mLocationPermissionsGranted = true;
                }

            } else {
                Log.d("MainActielsepermisson: ", " " + mLocationPermissionsGranted);
            }
        }
        /*switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    Log.d("MainActivity","mLocationPermissionsGranted = true");

                }
            }
        }*/
    }
}
