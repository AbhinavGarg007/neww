package com.example.emp354.vshop.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCancel,btnUpdate;
    EditText etCurrentPassword,etNewPassword,etRetypePassword;
    ProgressDialog dialog;
    long id;

    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    String DATABASE_NAME;
    VshopSharedPreference vshopSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        btnCancel=findViewById(R.id.btn_cancel_update);
        btnUpdate=findViewById(R.id.btn_update);
        etCurrentPassword=findViewById(R.id.et_current_password);
        etNewPassword=findViewById(R.id.et_new_password);
        etRetypePassword=findViewById(R.id.et_retype_new_password);

        appDatabase=AppDatabase.getAppDatabase(this);
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();
        vshopSharedPreference=VshopSharedPreference.getInstance(this);

        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cancel_update:
                Intent cancelIntent=new Intent(this,EditProfileActivity.class);
                startActivity(cancelIntent);
                break;

            case R.id.btn_update:
                Intent updateIntent=new Intent(this,EditProfileActivity.class);

                id=vshopSharedPreference.fetchid();
                vshopUserModel=appDatabase.userDao().getUserInfo(id);

                if(etCurrentPassword.getText().toString()!=null && etNewPassword.getText().toString()!=null && etRetypePassword.getText().toString()!=null) {

                    if (etNewPassword.getText().toString().equals(etRetypePassword.getText().toString())) {
                        if (etNewPassword.getText().toString().equals(etCurrentPassword.getText().toString())) {
                            break;
                        } else {
                            new UpdatePasswordAsyncTask().execute();
                        }
                    } else {
                        Toast.makeText(this, "Please retype password.", Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(updateIntent);
                break;
        }

    }

    private class UpdatePasswordAsyncTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
          dialog=ProgressDialog.show(ResetPasswordActivity.this,"Updating Password","Please wait..");

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Toast.makeText(ResetPasswordActivity.this,"Password updated.",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            appDatabase.userDao().updatePassword(etNewPassword.getText().toString(),id);
            return null;
        }
    }
}
