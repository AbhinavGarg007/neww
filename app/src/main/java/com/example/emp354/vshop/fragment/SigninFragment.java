package com.example.emp354.vshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.ForgotPasswordFragment;
import com.example.emp354.vshop.HomeActivity;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.SigninRegisterActivity;
import com.example.emp354.vshop.VshopUserModel;
import com.example.emp354.vshop.fragment.RegisterFragment;

public class SigninFragment extends Fragment implements View.OnClickListener {
    Button btnSubmit,btnSignin;
    TextView tvForgotPassword;
    EditText etEmail,etPassword;
    AppDatabase appDatabase;
    String DATABASE_NAME;
    VshopUserModel vshopUserModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_signin,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        appDatabase=AppDatabase.getAppDatabase(getContext());
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();

        btnSubmit=view.findViewById(R.id.btn_signin);
        btnSignin=view.findViewById(R.id.btn_signin_register);
        tvForgotPassword=view.findViewById(R.id.tv_forgot_password);
        etEmail=view.findViewById(R.id.et_signin_email);
        etPassword=view.findViewById(R.id.et_signin_password);

        btnSubmit.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_signin_register:
                ((SigninRegisterActivity)getActivity()).loadFragment(new RegisterFragment());
                break;

            case R.id.btn_signin:
                vshopUserModel=appDatabase.userDao().findByEmail(etEmail.getText().toString(),etPassword.getText().toString());
                if(vshopUserModel!=null)
                {

                    Intent intent=new Intent(((SigninRegisterActivity)getActivity()),HomeActivity.class);
                    startActivity(intent);
                }


                break;

            case R.id.tv_forgot_password:
                ((SigninRegisterActivity)getActivity()).loadFragment(new ForgotPasswordFragment());
                break;

        }
    }
}
