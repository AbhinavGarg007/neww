package com.example.emp354.vshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.emp354.vshop.R;

public class EmptyShoppingBagActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_shopping_bag);

        //declaring variables
        toolbar=findViewById(R.id.toolbar_shopping_bag_activity);
        toolbar.setTitle("Shopping Bag");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);

        //setting onClickListener on navigation item
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
