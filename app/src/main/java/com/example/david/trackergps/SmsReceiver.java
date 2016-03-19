package com.example.david.trackergps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSBroadcastReceiver";
    private ServiceLock mMonService;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent received:" + intent.getAction());

        if (intent.getAction() == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            Bundle bundle = intent.getExtras();
            String info = intent.getStringExtra("format");

            if (bundle != null) {

                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];

                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])
                            pdus[i], info);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "MESSAGE Received: " + messages[0].getMessageBody());
                    Toast.makeText(context, messages[0].getMessageBody(), Toast.LENGTH_LONG).show();
                    checkSMS(messages[0].getMessageBody(), context);
                }
            }
        }
    }


        void checkSMS(String SMS,Context context) {
        // Stoppe la reception des SMS : ! Attention le système n'est plus pilotable !

        if(SMS.equals("StopService")) {
            broadCastMessage(context,"StopService");

        }
    }
private void broadCastMessage (Context context,String msg) {
    Log.d(TAG,"Broadcasting message");
    Intent intent = new Intent("lockMyPhone");
    intent.putExtra("message", msg);
    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
}
}
