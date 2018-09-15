package com.example.emp354.linear.ThreadingAssignments.AssignmentActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.emp354.linear.R;
import com.example.emp354.linear.ThreadingAssignments.Services.MyIntentService;
import com.example.emp354.linear.ThreadingAssignments.Adapter.MyServiceAdapter;
import com.example.emp354.linear.ThreadingAssignments.POJO_Service.MyObject_1;

public class UsingIntentService extends AppCompatActivity {

    RecyclerView recyclerView;
    BroadcastReceiver broadcastReceiver;
    MyServiceAdapter myAdapter;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_intent_service);

        dialog=ProgressDialog.show(this,"Loading","Please wait..");

        recyclerView=findViewById(R.id.recyclerview_intent_service);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                MyObject_1 object_1=intent.getParcelableExtra("data");
                myAdapter=new MyServiceAdapter(object_1,getBaseContext());
                dialog.dismiss();
                recyclerView.setAdapter(myAdapter);


            }
        };

        IntentFilter intentFilter=new IntentFilter("intent_service_data");


        //registering broadcast reciever
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);


        //to start service
        Intent intent=new Intent(this,MyIntentService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

        super.onDestroy();
    }
}
