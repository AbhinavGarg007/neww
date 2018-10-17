package com.example.emp354.vshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.CategoriesRecyclerAdapter;

import static com.example.emp354.vshop.constants.Constant.CATEGORY_DISCOUNT;
import static com.example.emp354.vshop.constants.Constant.CATEGORY_IMAGE;
import static com.example.emp354.vshop.constants.Constant.CATEGORY_TYPES;

public class CategoriesFragment extends Fragment {

    /**
     * initialising varibles
     */
    int requiredHeight=0;

    CategoriesRecyclerAdapter categoriesRecyclerAdapter;


    /**
     * method to inflate layout
     * @param inflater to inflate layout
     * @param container it is the parent of inflated layout
     * @param savedInstanceState if you want to save the instance
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_categories,container,false);
       return view;
    }


    /**
     * method to perform operation after layout in inflated
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).checkFragment();
        RecyclerView recyclerView=view.findViewById(R.id.recyclerview_categories);

        if(((HomeActivity)getActivity()).getHeight()!=0)
        {
            requiredHeight=((HomeActivity)getActivity()).getHeight()*14/100;
        }
        categoriesRecyclerAdapter=new CategoriesRecyclerAdapter(getActivity(),CATEGORY_TYPES,CATEGORY_DISCOUNT,CATEGORY_IMAGE,requiredHeight);

        recyclerView.setAdapter(categoriesRecyclerAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);



    }


}
