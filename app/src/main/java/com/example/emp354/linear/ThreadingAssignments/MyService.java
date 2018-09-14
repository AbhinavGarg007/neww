package com.example.emp354.linear.ThreadingAssignments;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.IBinder;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    final static String MY_ACTION = "MY_ACTION";
    List<ServiceListItem> listItem = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        Log.d("loadRecyclerData", "Thread id: " + Thread.currentThread().getId());

                        JSONObject o = array.getJSONObject(i);
                        /*ServiceListItem item = new ServiceListItem(o.getString("name"),o.getString("vicinity"));*/
                        ServiceListItem item = new ServiceListItem(o);
                        listItem.add(item);
                        Thread.sleep(1000);

                       /* handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("handler", "Thread id: " + Thread.currentThread().getId());
                                dialog.dismiss();
                                adapter=new MyAdapter(listItem,getApplicationContext());
                                recyclerView.setAdapter(adapter);

                            }
                        });*/

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                intent.putParcelableArrayListExtra("listItem", listItem);

               /*intent.putStringArrayListExtra("intentList",listItem);*/
                sendBroadcast(intent);
                stopSelf();

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }


    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("json_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void sendData(ServiceListItem serviceListItem) {
        Intent intent = new Intent("data_action");
        intent.putExtra("data", serviceListItem);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
    }
}
