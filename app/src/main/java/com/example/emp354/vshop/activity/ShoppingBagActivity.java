package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.adapter.ShoppingBagAdapter;

public class ShoppingBagActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    Toolbar toolbar;
    TextView tvTitle;
    ShoppingBagAdapter shoppingBagAdapter;
    LinearLayoutManager layoutManager;
    Button btnPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);
        recyclerView=findViewById(R.id.recyclerview_add_to_cart);
        toolbar=findViewById(R.id.toolbar_shopping_bag_activity);
        tvTitle=findViewById(R.id.tv_title_shopping_bag_activity);
        btnPlaceOrder=findViewById(R.id.btn_place_order);

        //setting title to the textview of toolbar
        tvTitle.setText(R.string.shopping_bag);

        //setting listener on button
        btnPlaceOrder.setOnClickListener(this);

        //setting navigation icon on toolbar
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);

        //setting listener on navigation icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //initialising adapter
        shoppingBagAdapter=new ShoppingBagAdapter(this);

        //setting adapter to recyclerview
        recyclerView.setAdapter(shoppingBagAdapter);

        //initialing layout manager
        layoutManager=new LinearLayoutManager(this);

        //setting layout manager
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_place_order:
                Intent placeOrderintent=new Intent(this,PaymentActivity.class);
                startActivity(placeOrderintent);
                ShoppingBagActivity.this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        ShoppingBagActivity.this.overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
