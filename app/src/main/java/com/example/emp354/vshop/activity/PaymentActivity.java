package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.example.emp354.vshop.R;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    //declaring member variables
    Toolbar toolbar;
    TextView tvTitle,tvMonth,tvYear;
    Button btnPayNow;
    LinearLayout llYear,llMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //assigning variables
        toolbar=findViewById(R.id.toolbar_payment);
        tvTitle=findViewById(R.id.tv_title_payment_activity);
        llMonth=findViewById(R.id.ll_month);
        llYear=findViewById(R.id.ll_year);

        btnPayNow=findViewById(R.id.btn_pay_now);

        //setting click listener on pay now button
        btnPayNow.setOnClickListener(this);
        tvMonth=findViewById(R.id.tv_month);
        tvYear=findViewById(R.id.tv_year);
        llMonth.setOnClickListener(this);
        llYear.setOnClickListener(this);

        //setting navigation icon
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);

        //setting click listener on navigation icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    //operation to perform on click based on their id's.
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ll_month:
                PopupMenu popupMonth = new PopupMenu(PaymentActivity.this, llMonth);

                //Inflating the Popup using xml file
                popupMonth.getMenuInflater()
                        .inflate(R.menu.menu_month, popupMonth.getMenu());

                //registering popup with OnMenuItemClickListener
                popupMonth.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        tvMonth.setText(item.getTitle());

                        return true;
                    }
                });

                popupMonth.show(); //showing popup menu
                break;


            case R.id.ll_year:
                PopupMenu popupYear = new PopupMenu(PaymentActivity.this, llYear);

                //Inflating the Popup using xml file
                popupYear.getMenuInflater()
                        .inflate(R.menu.menu_year, popupYear.getMenu());

                //registering popup with OnMenuItemClickListener
                popupYear.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        tvYear.setText(item.getTitle());

                        return true;
                    }
                });

                popupYear.show(); //showing popup menu
                break;



            case R.id.btn_pay_now:
                Intent intent=new Intent(this,PaymentSuccessfulActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;


        }
    }

    @Override
    public void onBackPressed() {
        finish();
        PaymentActivity.this.overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
