package com.example.emp354.linear.ThirdParty.Assignment_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.R;

public class ThirdPartyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gridview_thirdparty,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle c=this.getArguments();

        String[] images=c.getStringArray("send_images");

        RecyclerView recyclerView=getView().findViewById(R.id.recycler_view_third_party);
        ThirdPartyRecyclerViewAdapter thirdPartyRecyclerViewAdapter=new ThirdPartyRecyclerViewAdapter(images);
        recyclerView.setAdapter(thirdPartyRecyclerViewAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);


    }
}
