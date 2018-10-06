package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.adapter.FeedsRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

public class FeedsActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    FeedsRecyclerAdapter feedsRecyclerAdapter;
    Toolbar toolbar;
    int[] feeds={R.drawable.cloth_1,R.drawable.cloth_2,
            R.drawable.cloth_3,R.drawable.cloth_4,
            R.drawable.cloth_5,R.drawable.cloth_6,
            R.drawable.cloth_7,R.drawable.cloth_8,
            R.drawable.cloth_9,R.drawable.cloth_10,};
    String title[]={"Product1","Product2","Product3","Product4","Product5","Product6","Product7","Product8","Product9","Product10"};
    String price[]={"$1.00","$2.00","$3.00","$4.00","$5.00","$6.00","$7.00","$8.00","$9.00","$10.00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        toolbar=findViewById(R.id.toolbar_feeds);
        setToolbar();
        recyclerView=findViewById(R.id.recyclerview_feeds);
        linearLayout=findViewById(R.id.layout_recycler_feeds);
        feedsRecyclerAdapter=new FeedsRecyclerAdapter(this,feeds,title,price,this);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(feedsRecyclerAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });


    }

    @Override
    public void onItemClick(View view, int position) {

        switch (view.getId())
        {
            case R.id.iv_feed:
                Intent selectProductIntent=new Intent(FeedsActivity.this,SelectProductActivity.class);
                selectProductIntent.putExtra("position",position);
                startActivity(selectProductIntent);
                break;

            case R.id.iv_action_bar:
                Toast.makeText(this, "Please wait , work in progress", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void setToolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);
        toolbar.setTitle(getResources().getString(R.string.feeds));
        toolbar.inflateMenu(R.menu.menu_icon);
        toolbar.getMenu().findItem(R.id.edit).setVisible(false);
        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
    }
}
