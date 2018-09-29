package com.example.emp354.vshop.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.HomeActivity;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.SigninRegisterActivity;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;

public class SigninFragment extends Fragment implements View.OnClickListener {
    Button btnSubmit,btnSignin;
    TextView tvForgotPassword;
    EditText etEmail,etPassword;
    AppDatabase appDatabase;
    String DATABASE_NAME;
    VshopUserModel vshopUserModel;
    ProgressDialog dialog;
    VshopSharedPreference vshopSharedPreference;

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
        vshopSharedPreference=VshopSharedPreference.getInstance(getActivity());

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
                new SigninAsyncTask().execute();
                break;

            case R.id.tv_forgot_password:
                ((SigninRegisterActivity)getActivity()).loadFragment(new ForgotPasswordFragment());
                break;

        }
    }


    private class SigninAsyncTask extends AsyncTask<Void,Void,VshopUserModel>
    {

        @Override
        protected void onPreExecute() {
          dialog=ProgressDialog.show(getActivity(),"Logging In","Please wait..");

        }

        @Override
        protected void onPostExecute(VshopUserModel vshopUserModel) {
            dialog.dismiss();
            if(vshopUserModel!=null)
            {
                vshopSharedPreference.saveId(vshopUserModel.getUid());
                Intent intent=new Intent(((SigninRegisterActivity)getActivity()),HomeActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getActivity(),"Please check the entries",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected VshopUserModel doInBackground(Void... voids) {
            vshopUserModel=appDatabase.userDao().isAccountExist(etEmail.getText().toString(),etPassword.getText().toString());
            return vshopUserModel;
        }
    }
}
