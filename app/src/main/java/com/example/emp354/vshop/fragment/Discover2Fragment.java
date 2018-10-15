package com.example.emp354.vshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.ViewPagerAdapter;

public class Discover2Fragment extends Fragment {

    //declaring variables
    ViewPager viewPager;
    TabLayout tabLayout;
    int px;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_discover2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //method to load layout according to the fragment
        ((HomeActivity)getActivity()).checkFragment();

        viewPager=view.findViewById(R.id.viewpager);
        tabLayout =view.findViewById(R.id.tab_layout);

        //initialing adapter
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity());

        //setting adapter
        viewPager.setAdapter(viewPagerAdapter);

        //converting dp to pixel
        px = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));

        viewPager.setPageMargin(px);
        viewPager.setPageMarginDrawable(R.color.color_list);
        tabLayout.setupWithViewPager(viewPager);

    }
}
