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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.EditTextValidation;
import com.example.emp354.vshop.HomeActivity;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.SigninRegisterActivity;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    Button btnSubmit,btnSignin;
    EditText etFirstName,etLastName,etEmail,etPassword,etConfirmPassword,etUserName;
    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    String DATABASE_NAME;
    RadioGroup radioGroup;
    RadioButton rbMale,rbFemale;
    String selectGender;
    boolean isChecked=false;
    boolean isAccountExist=false;
    ProgressDialog dialog;
    long id;
    VshopSharedPreference vshopSharedPreference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_register,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        appDatabase=AppDatabase.getAppDatabase(getContext());
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();
        vshopSharedPreference=VshopSharedPreference.getInstance(getActivity());


        btnSubmit=view.findViewById(R.id.btn_register);
        btnSignin=view.findViewById(R.id.btn_register_signin);
        etFirstName=view.findViewById(R.id.et_first_name);
        etLastName=view.findViewById(R.id.et_last_name);
        etEmail=view.findViewById(R.id.et_register_email);
        etPassword=view.findViewById(R.id.et_register_password);
        etConfirmPassword=view.findViewById(R.id.et_confirm_password);
        etUserName=view.findViewById(R.id.et_username);
        radioGroup=view.findViewById(R.id.radiogroup);
        rbMale=view.findViewById(R.id.rb_male);
        rbFemale=view.findViewById(R.id.rb_female);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton checkedRadioButton=radioGroup.findViewById(i);
                if(checkedRadioButton.getText().toString()!=null) {
                    isChecked=true;
                    selectGender =checkedRadioButton.getText().toString();
                }
            }
        });

        btnSubmit.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId())
        {

            case R.id.btn_register_signin:
                ((SigninRegisterActivity)getActivity()).loadFragment(new SigninFragment());
                break;

            case R.id.btn_register:
                if(isChecked) {
                    { if (validateFormData(etFirstName.getText().toString(),
                                etLastName.getText().toString(),
                                etEmail.getText().toString(),
                                etPassword.getText().toString(),
                                etConfirmPassword.getText().toString(),
                                etUserName.getText().toString(),
                                selectGender))
                        {
                            new RegisterAsyncTask().execute();
                        }
                        else {
                        printToast(getString(R.string.toast_check_entries)); }
                    }
                }else {
                  printToast(getString(R.string.toast_gender)); }
                break;

            default:
                break;
        }
    }


    //aync task method

    private class RegisterAsyncTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
          dialog=ProgressDialog.show(((SigninRegisterActivity)getActivity()),getString(R.string.registering),"Please Wait..");
        }

        @Override
        protected void onPostExecute(String string) {
            dialog.dismiss();
            printToast(string);
            if(!isAccountExist) {
                Intent intent = new Intent((SigninRegisterActivity) getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(Void... voids) {

            vshopUserModel=appDatabase.userDao().isMailExist(etEmail.getText().toString());
            if(vshopUserModel!=null)
            {
                isAccountExist=true;
                return getString(R.string.toast_account_exist);
            }
            else {
                vshopUserModel=new VshopUserModel();
                vshopUserModel.setFirstName(etFirstName.getText().toString());
                vshopUserModel.setLastName(etLastName.getText().toString());
                vshopUserModel.setEmail(etEmail.getText().toString());
                vshopUserModel.setPassword(etPassword.getText().toString());
                vshopUserModel.setUserName(etUserName.getText().toString());
                vshopUserModel.setGender(selectGender);

                id=appDatabase.userDao().insert(vshopUserModel);
                vshopSharedPreference.saveId(id);

                return getResources().getString(R.string.toast_registered);
            }
        }
    }




    //method to validate data
    private boolean validateFormData(String firstName,String lastName,String email,  String password,String confirmPassword,String userName,String gender) {
        if (!EditTextValidation.isValidName(firstName)) {
            etFirstName.setError("First letter must be capital.");
            return false; }
        if (!EditTextValidation.isValidName(lastName)) {
            etLastName.setError("First letter must be capital.");
            return false; }
        if (!EditTextValidation.isValidEmail(email)) {
            etEmail.setError("Invalid Email");
            return false; }
        if (!EditTextValidation.isValidPassword(password)) {
            etPassword.setError("Must contain atleast 6 or atmost 8 characters or digits.");
            return false; }

        if (!EditTextValidation.isValidPassword(confirmPassword)) {
            etConfirmPassword.setError("Rewrite Again");
            return false;
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Rewrite Again");
            return false; }

        if (!EditTextValidation.isValidUserName(userName)) {
            etUserName.setError("Must contain atleast 4 characters followed by atleast 2 digits.");
            return false; }

        if(!EditTextValidation.isValidGender(gender))
        { return false; }

        return true;
    }

    //method to print toast
    private void printToast(String string)
    {
        Toast.makeText((SigninRegisterActivity)getActivity(),
                string, Toast.LENGTH_SHORT).show();
    }
}
