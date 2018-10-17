package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.emp354.vshop.R;

/**
 * activity to display the orders placed in past
 */
public class OrderHistoryActivity extends AppCompatActivity {

    //declaring variables
    android.support.v7.widget.Toolbar toolbar;
    TextView tvTitle;
    LinearLayout layoutSearch;
    boolean isSearchOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        //assigning variables
        toolbar=findViewById(R.id.toolbar_order_history_activity);
        tvTitle=findViewById(R.id.tv_title_order_history_activity);
        layoutSearch=findViewById(R.id.layout_search_order_history);
        setToolbar();
    }

    @Override
    public void onBackPressed() {
        searchStateCheck();
    }


    //method to set toolbar ,inflate menu and setting click listener on menu items
    private void setToolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);
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
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    //if click on navigation bag icon
                    case R.id.navigation_bag:
                        Intent intent=new Intent(OrderHistoryActivity.this,EmptyShoppingBagActivity.class);
                        startActivity(intent);
                        break;

                        //if click on navigation search icon

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
