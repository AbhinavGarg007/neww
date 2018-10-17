package com.example.emp354.vshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.activity.OrderHistoryActivity;
import com.example.emp354.vshop.adapter.NotificationRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

public class NotificationFragment extends Fragment implements ItemClickListener {

    //declaring variables
    RecyclerView recyclerView;
    NotificationRecyclerAdapter notificationRecyclerAdapter;
    LinearLayoutManager layoutManager;
    int requiredHeight;

    //inflating layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_notification,container,false);
        return view;
    }

    //perform operations after layout is inflated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).checkFragment();

        //getting height at runtime
        if(((HomeActivity)getActivity()).getHeight()!=0)
        {
            requiredHeight=((HomeActivity)getActivity()).getHeight()*30/100;
        }



        //initialising variables
        recyclerView=view.findViewById(R.id.recyclerview_notification);
        notificationRecyclerAdapter=new NotificationRecyclerAdapter(getActivity(),requiredHeight,this);
        layoutManager=new LinearLayoutManager(getActivity());

        //setting adapter and layout manager to recyclerview
        recyclerView.setAdapter(notificationRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.layout_item_notification:
                Intent notificationIntent=new Intent(getActivity(),OrderHistoryActivity.class);
                startActivity(notificationIntent);
                break;
        }
    }
}
