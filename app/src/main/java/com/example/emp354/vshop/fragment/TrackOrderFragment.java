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
import com.example.emp354.vshop.activity.TrackOrderActivity;
import com.example.emp354.vshop.adapter.TrackOrderRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

public class TrackOrderFragment extends Fragment implements ItemClickListener {
    RecyclerView recyclerView;
   TrackOrderRecyclerAdapter trackOrderRecyclerAdapter;
   LinearLayoutManager layoutManager;
   int requiredHeight;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_track_order,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((HomeActivity)getActivity()).checkFragment();

        if(((HomeActivity)getActivity()).getHeight()!=0)
        {
            requiredHeight=((HomeActivity)getActivity()).getHeight()*40/100;
        }

       recyclerView=view.findViewById(R.id.recyclerview_track_order);
       trackOrderRecyclerAdapter=new TrackOrderRecyclerAdapter(getActivity(),requiredHeight,this);
       layoutManager=new LinearLayoutManager(getActivity());
       recyclerView.setAdapter(trackOrderRecyclerAdapter);
       recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onItemClick(View view, int position) {
     switch (view.getId())
     {
         case R.id.ll_track_order_item:
             Intent trackOrderIntent=new Intent(getActivity(),TrackOrderActivity.class);
             trackOrderIntent.putExtra("position",position);
             startActivity(trackOrderIntent);
     }
    }
}
