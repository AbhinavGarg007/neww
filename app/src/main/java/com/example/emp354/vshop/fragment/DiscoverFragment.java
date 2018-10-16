package com.example.emp354.vshop.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.DiscoverRecyclerAdapter;
import com.example.emp354.vshop.decoration.CirclePagerIndicatorDecoration;
import com.example.emp354.vshop.decoration.LinePagerIndicatorDecoration;
import com.example.emp354.vshop.decoration.SeparatorDecoration;

public class DiscoverFragment extends Fragment {
    RecyclerView recyclerView;
    DiscoverRecyclerAdapter discoverRecyclerAdapter;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_discover,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((HomeActivity)getActivity()).checkFragment();

        recyclerView=view.findViewById(R.id.recyclerview_discover);
        discoverRecyclerAdapter=new DiscoverRecyclerAdapter(getActivity());
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(discoverRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        //add pager behaviour
        PagerSnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //pager indicator
        /*recyclerView.addItemDecoration(new SeparatorDecoration(getActivity(), Color.GRAY, 1.5f));*/
        recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
    }
}
