package com.example.siva.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_SMS)){

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS},1);

            }
            else{

                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_SMS},1);

            }

        }

        Intent sms_intent=getIntent();
        Bundle b=sms_intent.getExtras();
        TextView tv=(TextView)findViewById(R.id.txtview);

        if(b!=null){
            tv.setText(b.getString("sms_str"));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {

        switch (requestCode){
            case 1:{

                   if(grantResults.length>0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){

                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,"No Permission Granted",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }


    }
}
