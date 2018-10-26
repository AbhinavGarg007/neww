package com.example.emp354.vshop.useless;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.SigninRegisterActivity;
import com.example.emp354.vshop.fragment.SigninFragment;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener{
    Button btnCancel,btnSubmit;

    //inflating layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_forgot_password,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initialising button
       /*btnCancel=view.findViewById(R.id.btn_cancel);
       btnSubmit=view.findViewById(R.id.btn_submit);

       //setting listener on button
       btnCancel.setOnClickListener(this);
       btnSubmit.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cancel:
                ((SigninRegisterActivity)getActivity()).loadFirstFragment(new SigninFragment());
                break;

            case R.id.btn_submit:
                ((SigninRegisterActivity)getActivity()).loadFirstFragment(new SigninFragment());
                break;
        }
    }
}
