package com.example.emp354.linear.ThreadingAssignments;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.emp354.linear.ThreadingAssignments.Adapter.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NormalService extends Service{

    Looper mLooper;
    ServiceHandler serviceHandler;

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<ListItem> listItem;
    ProgressDialog dialog;
    Handler handler;


    private final class ServiceHandler extends Handler{

        public ServiceHandler(Looper looper)
        {

            super(looper);
            Log.d("ServiceHandlerConst","ServiceHandlerConst");
        }

        @Override
        public void handleMessage(Message msg) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try{
                        JSONObject jsonObject=new JSONObject(loadJSONFromAsset());
                        JSONArray array=jsonObject.getJSONArray("results");

                        for(int i=0;i<array.length();i++)
                        {
                            Log.d("loadRecyclerData", "Thread id: " + Thread.currentThread().getId());

                            JSONObject o=array.getJSONObject(i);
                            ListItem item=new ListItem(o.getString("name"),
                                    o.getString("vicinity"));
                            listItem.add(item);
                            Thread.sleep(1000);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("handler", "Thread id: " + Thread.currentThread().getId());
                                    dialog.dismiss();
                                   /* adapter=new MyAdapter(listItem,getApplicationContext());*/
                                    recyclerView.setAdapter(adapter);

                                }
                            });


                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    stopSelf();


                }
            }).start();
        }
    }

    @Override
    public void onCreate() {
        Log.d("onCreate","onCreate");

        //startup the thread running the services.
        //we also make it background priority.

        HandlerThread handlerThread=new HandlerThread("ServiceStart", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        //get the handlerthread's looper and use it for our handler
        mLooper=handlerThread.getLooper();
        serviceHandler=new ServiceHandler(mLooper);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand","onStartCommand");

        //for each request,send a message to start a job
        //and deliver the id so we know which request we are stopping
        //when we finish the job.

        Message msg=serviceHandler.obtainMessage();
        msg.arg1=startId;
        serviceHandler.sendMessage(msg);


        //if get killed will not restart.
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onBind","onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("onDestroy","destroy");
        super.onDestroy();
    }
    private String loadJSONFromAsset()
    {
        String json=null;
        try
        {
            InputStream is=getAssets().open("json_data.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
