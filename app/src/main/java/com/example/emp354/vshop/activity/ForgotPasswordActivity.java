package com.example.emp354.vshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.vshop.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnCancel,btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //initialising button
        btnCancel=findViewById(R.id.btn_cancel);
        btnSubmit=findViewById(R.id.btn_submit);

        //setting listener on button
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cancel:
                onBackPressed();
                break;

            case R.id.btn_submit:
               onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
