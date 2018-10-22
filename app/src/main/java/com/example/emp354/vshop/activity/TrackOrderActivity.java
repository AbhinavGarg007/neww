package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.fragment.OrderSummaryFragment;
import com.example.emp354.vshop.fragment.TrackingStatusFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;
import static com.example.emp354.vshop.constants.Constant.TRACK_ORDER_IMAGES;
import static com.example.emp354.vshop.constants.Constant.TRACK_ORDER_TITLE;

public class TrackOrderActivity extends AppCompatActivity implements View.OnClickListener {

    //declaring variables
    Button btnTrackingStatus,btnOrderSummary;
    Fragment fragment;
    Toolbar toolbar;
    TextView tvTitle,tvTitleProduct;
    ImageView ivProduct;
    LinearLayout layoutSearch;
    boolean isSearchOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        //initialising variables
        btnTrackingStatus=findViewById(R.id.btn_tracking_status);
        btnOrderSummary=findViewById(R.id.btn_order_summary);
        toolbar=findViewById(R.id.toolbar_track_order_activity);
        tvTitle=findViewById(R.id.tv_title_track_order_activity);
        layoutSearch=findViewById(R.id.layout_search_track_order);
        tvTitleProduct=findViewById(R.id.tv_title_track_order_product_activity);
        ivProduct=findViewById(R.id.iv_track_order_product_activity);




        //method to set toolbar
        setToolbar();

        //calling method to load fragment
        loadFragment(new TrackingStatusFragment());

        //getting data from calling intent
        Intent intent=getIntent();
        int pos=intent.getIntExtra("position",0);


        tvTitleProduct.setText(TRACK_ORDER_TITLE[pos]);
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + TRACK_ORDER_IMAGES[pos],ivProduct);

        //setting click listener
        btnTrackingStatus.setOnClickListener(this);
        btnOrderSummary.setOnClickListener(this);

    }

    //method to perform operation based on id's
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_tracking_status:
                fragment=new TrackingStatusFragment();
                break;

            case R.id.btn_order_summary:
                fragment=new OrderSummaryFragment();
                break;
        }
        //calling method to set button background
        setButtonBackground(fragment);

        //calling method to load fragment
        loadFragment(fragment);
    }

    //method to load fragment
    public void loadFragment(Fragment fragment)
    {
        if(fragment!=null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            boolean fragmentPopped = fragmentManager.popBackStackImmediate (backStateName, 0);
            if(!fragmentPopped) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
                fragmentTransaction.replace(R.id.layout_frame_track_order, fragment, "addfragment");
                fragmentTransaction.addToBackStack(backStateName);
                fragmentTransaction.commit();
            }
        }
    }

    //method to set toolbar
    private void setToolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                        Intent intent=new Intent(TrackOrderActivity.this,EmptyShoppingBagActivity.class);
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

    @Override
    public void onBackPressed() {
        searchStateCheck();
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


    //method to set button background
    private void setButtonBackground(Fragment fragment)
    {
      if(fragment instanceof TrackingStatusFragment)
      {
          btnTrackingStatus.setBackgroundColor(getResources().getColor(R.color.color_grey_dark));
          btnTrackingStatus.setTextColor(getResources().getColor(android.R.color.white));
          btnOrderSummary.setBackgroundColor(getResources().getColor(android.R.color.transparent));
          btnOrderSummary.setTextColor(getResources().getColor(R.color.color_black));
      }
      else if(fragment instanceof OrderSummaryFragment)
      {
          btnTrackingStatus.setBackgroundColor(getResources().getColor(android.R.color.transparent));
          btnTrackingStatus.setTextColor(getResources().getColor(R.color.color_black));
          btnOrderSummary.setBackgroundColor(getResources().getColor(R.color.color_grey_dark));
          btnOrderSummary.setTextColor(getResources().getColor(android.R.color.white));
      }
    }


}
