package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.adapter.FeedsRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

public class FeedsActivity extends AppCompatActivity implements ItemClickListener {
    //declaring member variables
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    FeedsRecyclerAdapter feedsRecyclerAdapter;
    GridLayoutManager gridLayoutManager;
    TextView tvTitle;
    Toolbar toolbar;
    boolean isSearchOpen=false;
    LinearLayout layoutSearch;

    //passing static data
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
        //calculating height
        int height=getHeight()*30/100;

        //initialing variables
        toolbar=findViewById(R.id.toolbar_feeds);
        tvTitle=findViewById(R.id.tv_title_feeds);
        layoutSearch=findViewById(R.id.layout_search_feeds);
        recyclerView=findViewById(R.id.recyclerview_feeds);
        linearLayout=findViewById(R.id.layout_recycler_feeds);


        feedsRecyclerAdapter=new FeedsRecyclerAdapter(this,feeds,title,price,this,height);
        gridLayoutManager=new GridLayoutManager(this,2,LinearLayout.VERTICAL,false);
        //setting layout manager
        recyclerView.setLayoutManager(gridLayoutManager);
        //setting adapter to the recycler view
        recyclerView.setAdapter(feedsRecyclerAdapter);
        setToolbar();

    }

    @Override
    public void onBackPressed() {
        searchStateCheck();
    }


    //method to perform operation after getting position from the adapter item click
    @Override
    public void onItemClick(View view, int position) {

        switch (view.getId())
        {
            case R.id.iv_feed:
                Intent selectProductIntent=new Intent(FeedsActivity.this,SelectProductActivity.class);
                selectProductIntent.putExtra("position",position);
                startActivity(selectProductIntent);
                break;
        }

    }

    //method to set toolbar
    private void setToolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);
        tvTitle.setText(getResources().getString(R.string.feeds));
        toolbar.inflateMenu(R.menu.menu_icon);
        toolbar.getMenu().findItem(R.id.edit).setVisible(false);
        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_bag:
                        Intent intent=new Intent(FeedsActivity.this,ShoppingBagActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.navigation_search:
                        isSearchOpen=true;
                        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(false);
                        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(false);
                        tvTitle.setVisibility(View.GONE);
                        layoutSearch.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });

    }

    //method to get height on runtime
    public int getHeight()
    {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;

    }

    //method to check whether search bar is open or not
    public void searchStateCheck()
    {
        if(isSearchOpen)
        {isSearchOpen=false;
            layoutSearch.setVisibility(View.GONE);
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            tvTitle.setVisibility(View.VISIBLE);

        }
        else { finish();
        }
    }

}
