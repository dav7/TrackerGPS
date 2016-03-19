package com.example.david.trackergps;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class ServiceLock extends Service {
//SMS
    SmsReceiver myReceiver;
    BroadcastReceiver msgcom;
    @Override
    public void onCreate() {
        initSMS();
        initBroadcast();
        Toast.makeText(this, "Service démarré", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this.getApplicationContext()).unregisterReceiver(msgcom);
        unregisterReceiver(myReceiver);
        Log.d("ServiceLock", "Service done");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
        private void initBroadcast() {
        msgcom = new MsgCom();

//Recepteur local pour les intents
        LocalBroadcastManager.getInstance(this).registerReceiver(msgcom, new IntentFilter("LockMyPhone"));
    }
    private void initSMS() {
        //Réception des sms
        myReceiver = new SmsReceiver();
        this.registerReceiver(myReceiver,new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED"));
    }
        private class MsgCom extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent){
                if (TextUtils.equals(intent.getAction(),"lockMyPhone")) {
                    String message = intent.getStringExtra("message");
                Log.d("receiver", "Got message : " + message);

                if(message.equals("StopService"))
                {
                    ServiceLock.this.stopSelf();
                    Log.d("receiver", "Service stopped");
                }
            }
        }
}
}
