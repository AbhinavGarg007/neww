package com.example.emp354.vshop.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.fragment.OrderSummaryFragment;
import com.example.emp354.vshop.fragment.TrackingStatusFragment;

public class TrackOrderActivity extends AppCompatActivity implements View.OnClickListener {

    //declaring variables
    Button btnTrackingStatus,btnOrderSummary;
    HomeActivity homeActivity;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        //initialising variables
        btnTrackingStatus=findViewById(R.id.btn_tracking_status);
        btnOrderSummary=findViewById(R.id.btn_order_summary);

        homeActivity=new HomeActivity();



        //setting click listener
        btnTrackingStatus.setOnClickListener(this);
        btnOrderSummary.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_tracking_status:
                fragment=new TrackingStatusFragment();
                break;

            case R.id.btn_order_summary:
                fragment=new OrderSummaryFragment();
                break;
        }
        loadFragment(fragment);
    }

    //method to load fragment
    public void loadFragment(Fragment fragment)
    {
        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_frame_track_order, fragment,"addfragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


}
