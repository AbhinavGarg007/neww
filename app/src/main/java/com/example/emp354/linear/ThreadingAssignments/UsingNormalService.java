package com.example.emp354.linear.ThreadingAssignments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.emp354.linear.R;
import com.example.emp354.linear.ThreadingAssignments.Adapter.MyAdapter;

public class UsingNormalService extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ProgressDialog dialog;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_normal_service);

        recyclerView=findViewById(R.id.recyclerview_service);
LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,new IntentFilter("data_action"));
        dialog= ProgressDialog.show(UsingNormalService.this,"Loading data","Please Wait..");

    }

    @Override
    protected void onStart() {

        //register broadcast receiver
        //to recieve event from our service

        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(MyService.MY_ACTION);
        registerReceiver(myReceiver,intentFilter);

        //start service
        Intent intent=new Intent(this,MyService.class);
        startService(intent);

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(myReceiver);
        super.onStop();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ServiceListItem listItem = intent.getParcelableExtra("data");
            Log.d("tag", "onReceive: ");
        }
    };
}
