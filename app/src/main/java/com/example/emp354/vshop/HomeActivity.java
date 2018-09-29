package com.example.emp354.vshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import static com.example.emp354.vshop.constants.Constant.BROWSE_BRANDS;
import static com.example.emp354.vshop.constants.Constant.CATEGORY;
import static com.example.emp354.vshop.constants.Constant.DISCOVER;
import static com.example.emp354.vshop.constants.Constant.FEEDS;
import static com.example.emp354.vshop.constants.Constant.NOTIFICATION;
import static com.example.emp354.vshop.constants.Constant.PROFILE;
import static com.example.emp354.vshop.constants.Constant.SIGN_OUT;
import static com.example.emp354.vshop.constants.Constant.TRACK;

public class HomeActivity extends AppCompatActivity {

    NavigationView navigationView;
    VshopSharedPreference vshopSharedPreference;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView=findViewById(R.id.navigation_view);
        vshopSharedPreference=VshopSharedPreference.getInstance(this);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.layout_drawer);


        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.inflateMenu(R.menu.menu_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getTitle().toString())
                {
                    case PROFILE:
                        break;

                    case FEEDS:
                        break;

                    case BROWSE_BRANDS:
                        break;

                    case CATEGORY:
                        break;

                    case DISCOVER:
                        break;

                    case TRACK:
                        break;

                    case NOTIFICATION:
                        break;

                    case SIGN_OUT:
                        logOut();
                        break;


                }
                return true;
            }
        });
    }

    public void logOut()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("LogOut")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* SaveSharedPreference.setLoggedIn(getApplicationContext(), false,null);*/
                        vshopSharedPreference.clearAllData();
                        Intent i=new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
