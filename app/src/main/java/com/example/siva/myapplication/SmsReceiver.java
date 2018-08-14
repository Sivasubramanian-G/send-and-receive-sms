package com.example.siva.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by root on 7/17/18.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();
        SmsMessage[] smsm=null;
        String sms_str="";

        if(bundle!=null){

            Object[] pdus=(Object[]) bundle.get("pdus");
            smsm=new SmsMessage[pdus.length];
            for(int i=0;i<smsm.length;i++){
                smsm[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                sms_str+="Sent From:"+smsm[i].getDisplayOriginatingAddress();
                sms_str+="\r\nMessage: ";
                sms_str+=smsm[i].getDisplayMessageBody().toString();
                sms_str+="\r\n";
            }

            Intent smsIntent=new Intent(context,MainActivity.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra("sms_str",sms_str);
            context.startActivity(smsIntent);

        }

    }
}
