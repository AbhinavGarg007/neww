package com.example.emp354.vshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.useless.EditProfileFragment;

public class UpdatePasswordFragment extends Fragment implements View.OnClickListener {

    //declaring variables
    Button btnCancel,btnUpdate;
    @Nullable
    @Override

    //inflating layout
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_update_password,container,false);
        return view;
    }


    //performing operation after layout is inflated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initialising variables
        btnCancel=view.findViewById(R.id.btn_cancel_update);
        btnUpdate=view.findViewById(R.id.btn_update_password);

        //seting listener
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }


    //performing operation on click
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cancel_update:
                ((HomeActivity)getActivity()).loadFragment(new EditProfileFragment());
                break;

            case R.id.btn_update_password:
                ((HomeActivity)getActivity()).loadFragment(new EditProfileFragment());
                break;
        }
    }
}
