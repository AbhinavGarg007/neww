package com.example.emp354.vshop.fragment;

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
import com.example.emp354.vshop.adapter.BrowseBrandsRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

import java.util.HashSet;

import static com.example.emp354.vshop.constants.Constant.BRANDS;
import static com.example.emp354.vshop.constants.Constant.BRANDS_TITLE;

public class BrowseBrandsFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    int requiredHeight=0;
    int requiredWidth=0;
    BrowseBrandsRecyclerAdapter browseBrandsRecyclerAdapter;
    HashSet<Integer> isFollowed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_browse_brands,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).checkFragment();
        isFollowed=new HashSet<>();
        recyclerView=view.findViewById(R.id.recyclerview_browse_brands);
        if(((HomeActivity)getActivity()).getHeight()!=0)
        {
            requiredHeight=((HomeActivity)getActivity()).getHeight()*7/100;
        }
        if(((HomeActivity)getActivity()).getWidth()!=0)
        {
            requiredWidth=((HomeActivity)getActivity()).getHeight()*10/100;
        }
        browseBrandsRecyclerAdapter=new BrowseBrandsRecyclerAdapter(getActivity(),BRANDS,BRANDS_TITLE,requiredHeight,requiredWidth,isFollowed,this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(browseBrandsRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.tv_follow_browse_brands:

                if(isFollowed.contains(position))
                {
                    isFollowed.remove(position);
                }
                else
                {
                    isFollowed.add(position);
                }

                browseBrandsRecyclerAdapter.notifyItemChanged(position);
        }
    }
}
