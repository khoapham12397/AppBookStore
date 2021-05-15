package com.example.reviewreadurl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ExampleService extends Service {
    HandlerService handler;
    HandlerThread thread;
    LocalBinder myBinder=new LocalBinder();
    Random random=new Random();
    int xcounter=0;
    private class HandlerService extends Handler{
        public HandlerService(Looper looper){
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            try {
                Log.d("BBB","In thread: "+ Thread.currentThread().getName()+" and intent startID = "+String.valueOf(msg.arg1));

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public class LocalBinder extends Binder {
        ExampleService getService(){
            return ExampleService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("CREATE","Creating service");
        thread=new HandlerThread("Worker Thread of Service");
        thread.start();
        handler=new HandlerService(thread.getLooper());
        //pass lien tuc cai nhiem vu vao cho no dung :

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg= handler.obtainMessage();
        msg.arg1=startId;
        handler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //tiep theo neu ta co the thuc hien va kiem soat all things in android->
        //there are so many things -> never see =>
        //so
        Log.d("SERVICE_DESTROY","Service is destroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ON_BIND","onBind is called");
        handler.post(new Runnable() {
            @Override
            public void run() {
                xcounter++;
                handler.postDelayed(this,1000);
            }
        });
        return myBinder;
    }
    public int getRandom(){
        return xcounter;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("ON_REBIND","onRebind is called");
    }
}
