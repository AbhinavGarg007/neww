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

public class UpdatePasswordFragment extends Fragment implements View.OnClickListener {
    Button btnCancel,btnUpdate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_update_password,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnCancel=view.findViewById(R.id.btn_cancel_update);
        btnUpdate=view.findViewById(R.id.btn_update_password);

        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

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
