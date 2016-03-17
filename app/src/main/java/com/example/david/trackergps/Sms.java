package com.example.david.trackergps;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by david on 17/02/2016.
 */
public class Sms {
    public void sendSMS(String phoneNumber, String message, final Context myContext)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";


        PendingIntent sentPI = PendingIntent.getBroadcast(myContext,0,new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(myContext,0,new Intent(DELIVERED), 0);

        //Envoi du SMS

        myContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(myContext.getApplicationContext(),"SMS SENT", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(myContext.getApplicationContext(),"GENERIC FAILURE",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(myContext.getApplicationContext(),"NO SERVICE",Toast.LENGTH_SHORT).show();
                        break;
                    case  SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(myContext.getApplicationContext(),"NULL PDU",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(myContext.getApplicationContext(),"RADIO OFF",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        },new IntentFilter(SENT));


        //SMS RECU PAR LE DESTINATAIRE

        myContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(myContext.getApplicationContext(),"SMS DELIVERED", Toast.LENGTH_SHORT).show();
                        break;
                    case  Activity.RESULT_CANCELED:
                        Toast.makeText(myContext.getApplicationContext(),"SMS NOT DELIVERED",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        },new IntentFilter(DELIVERED));

        //DEMANDE D'ENVOI SMS

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null,message,sentPI,deliveredPI);
    }
}
