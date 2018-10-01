package com.example.emp354.vshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.FeedsRecyclerAdapter;

public class FeedsFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    FeedsRecyclerAdapter feedsRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_feeds,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview_feeds);
        linearLayout=view.findViewById(R.id.layout_recycler_feeds);
        feedsRecyclerAdapter=new FeedsRecyclerAdapter();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(feedsRecyclerAdapter);
    }
}
