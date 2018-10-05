package com.example.emp354.vshop.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.fragment.EditProfileFragment;
import com.example.emp354.vshop.fragment.FeedsFragment;
import com.example.emp354.vshop.fragment.ProfileFragment;

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
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HomeActivity","onCreate");

        loadFragment(new ProfileFragment());
        navigationView=findViewById(R.id.navigation_view);
        vshopSharedPreference=VshopSharedPreference.getInstance(this);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.layout_drawer);


        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.inflateMenu(R.menu.menu_icon);
        checkFragment();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.edit:
                        Intent intent=new Intent(HomeActivity.this,EditProfileActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getTitle().toString())
                {
                    case PROFILE:
                        fragment=new ProfileFragment();
                        break;

                    case FEEDS:
                        fragment=new FeedsFragment();
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
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);

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
                        vshopSharedPreference.clearAllData();
                        Intent i=new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void loadFragment(Fragment fragment)
    {
        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_frame_home, fragment,"addfragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onResume() {
        Log.d("HomeActivity","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("HomeActivity","onPause");
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Log.d("HomeActivity","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("HomeActivity","onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("HomeActivity","onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("HomeActivity","onDestroy");
        super.onStop();
    }

    public void checkFragment()
    {
        Fragment fragment;

        fragment=getSupportFragmentManager().findFragmentByTag("addfragment");

        if(fragment instanceof ProfileFragment)
        {
            toolbar.setTitle(getResources().getString(R.string.profile));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(false);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(false);
            toolbar.getMenu().findItem(R.id.edit).setVisible(true);
        }

        if(fragment instanceof FeedsFragment)
        {
            toolbar.setTitle(getResources().getString(R.string.feeds));
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);

        }
    }

}
