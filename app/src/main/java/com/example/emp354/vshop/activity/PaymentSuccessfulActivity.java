package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.emp354.vshop.R;

public class PaymentSuccessfulActivity extends AppCompatActivity implements View.OnClickListener {

    //declaring variables
    Button btnOkay;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        //initialising variables
        btnOkay=findViewById(R.id.btn_okay);
        toolbar=findViewById(R.id.toolbar_payment_successful);

        //applying click listener on okay button
        btnOkay.setOnClickListener(this);

        //settin navigation icon on toolbar
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);

        //setting click listener on navigation icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //performing operations on certain clicks based on id's
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_okay:
                Intent paymentSuccessfullIntent=new Intent(this,HomeActivity.class);
                startActivity(paymentSuccessfullIntent);
                PaymentSuccessfulActivity.this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        PaymentSuccessfulActivity.this.overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
