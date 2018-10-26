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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.fragment.BrowseBrandsFragment;
import com.example.emp354.vshop.fragment.CategoryFragment;
import com.example.emp354.vshop.fragment.Discover2Fragment;
import com.example.emp354.vshop.fragment.NotificationFragment;
import com.example.emp354.vshop.fragment.ProfileFragment;
import com.example.emp354.vshop.fragment.TrackOrderFragment;

import static com.example.emp354.vshop.constants.Constant.BROWSE_BRANDS;
import static com.example.emp354.vshop.constants.Constant.CATEGORIES;
import static com.example.emp354.vshop.constants.Constant.DISCOVER;
import static com.example.emp354.vshop.constants.Constant.FEEDS;
import static com.example.emp354.vshop.constants.Constant.NOTIFICATION;
import static com.example.emp354.vshop.constants.Constant.PROFILE;
import static com.example.emp354.vshop.constants.Constant.SIGN_OUT;
import static com.example.emp354.vshop.constants.Constant.TRACK;

public class HomeActivity extends AppCompatActivity {

    //declaring member variables
    NavigationView navigationView;
    VshopSharedPreference vshopSharedPreference;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout layoutSearch;
    SearchView searchView;
    TextView tvTitle;
    Fragment fragment;
    boolean isSearchOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HomeActivity", "onCreate");

        //initialising variables
        navigationView = findViewById(R.id.navigation_view);
        vshopSharedPreference = VshopSharedPreference.getInstance(this);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.layout_drawer);
        searchView=findViewById(R.id.searchview);
        /*layoutSearch = findViewById(R.id.layout_search_home);*/
        tvTitle = findViewById(R.id.tv_toolbar_home);
        checkFragment();
        setToolbar();
        setNavigationView();
        getHeight();
        getWidth();
        loadFirstFragment(new ProfileFragment());
    }

    //method to set navigation view
    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getTitle().toString()) {
                    case PROFILE:
                        fragment = new ProfileFragment();
                        break;

                    case FEEDS:
                        Intent feedIntent = new Intent(HomeActivity.this, FeedsActivity.class);
                        startActivity(feedIntent);
                        break;

                    case BROWSE_BRANDS:
                        fragment = new BrowseBrandsFragment();
                        break;

                    case CATEGORIES:
                        fragment = new CategoryFragment();
                        break;

                    case DISCOVER:
                        fragment = new Discover2Fragment();
                        break;

                    case TRACK:
                        fragment = new TrackOrderFragment();
                        break;

                    case NOTIFICATION:
                        fragment = new NotificationFragment();
                        break;

                    case SIGN_OUT:
                        logOut();
                        break;

                }

                HomeActivity.this.overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
                //method call to load fragment
                loadFragment(fragment);
                //closing drawerlayout
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    //method to set toolbar
    private void setToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.inflateMenu(R.menu.menu_icon);
        //setting click listener on navigation icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //setting click listener on menu item
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.edit:
                        Intent editIntent = new Intent(HomeActivity.this, EditProfileActivityCropWithLibrary.class);
                        startActivity(editIntent);
                        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
                        break;


                    case R.id.navigation_bag:
                        Intent searchBagIntent = new Intent(HomeActivity.this, EmptyShoppingBagActivity.class);
                        startActivity(searchBagIntent);
                        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
                        break;

                    case R.id.navigation_search:
                        isSearchOpen = true;
                        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(false);
                        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(false);
                        tvTitle.setVisibility(View.GONE);
                       /* layoutSearch.setVisibility(View.VISIBLE);*/
                        searchView.setVisibility(View.VISIBLE);
                        break;

                }
                return true;
            }
        });
    }

    //method to perform logout operation
    public void logOut() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("LogOut")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vshopSharedPreference.clearAllData();
                        Intent i = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    //method to load fragment
    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment currentFragment=fragmentManager.findFragmentById(R.id.layout_frame_home);
            String currentFragmentName=currentFragment.getClass().getName();
            if (!backStateName.equals(currentFragmentName))
            {
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

                if (!fragmentPopped) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.replace(R.id.layout_frame_home, fragment, "addfragment");
                    fragmentTransaction.addToBackStack(backStateName);
                    fragmentTransaction.commit();
                }

            }
        }
    }

    public void loadFirstFragment(Fragment firstFragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame_home, firstFragment, "addfragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    //method to check fragment instance and set the toolbar layout accordingly that will be called from fragment
    // onCreate method
    public void checkFragment() {
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentByTag("addfragment");
        whichFragmentInstance(fragment);
    }

    //getting height of display to make independent layout
    public int getHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    //method to get width of display on runtime
    public int getWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    //method to check which fragment instance is loading and inflate the toolbar layout accordingly
    private void whichFragmentInstance(Fragment fragment) {
        if (fragment instanceof ProfileFragment) {
            tvTitle.setText(getResources().getString(R.string.profile));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(false);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(false);
            toolbar.getMenu().findItem(R.id.edit).setVisible(true);
            return;
        }

        if (fragment instanceof BrowseBrandsFragment) {
            tvTitle.setText(getResources().getString(R.string.browse_brands));
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);

            return;
        }
        if (fragment instanceof CategoryFragment) {
            tvTitle.setText(getResources().getString(R.string.category));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            return;
        }
        if (fragment instanceof Discover2Fragment) {
            tvTitle.setText(getResources().getString(R.string.discover));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            return;
        }
        if (fragment instanceof TrackOrderFragment) {
            tvTitle.setText(getResources().getString(R.string.track_order));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            return;
        }
        if (fragment instanceof NotificationFragment) {
            tvTitle.setText(getResources().getString(R.string.notification));
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            toolbar.getMenu().findItem(R.id.edit).setVisible(false);
            return;
        }
    }



    //method to perform back pressed operation
    @Override
    public void onBackPressed() {
        if (isSearchOpen) {
            isSearchOpen = false;
           /* layoutSearch.setVisibility(View.GONE);*/
            searchView.setVisibility(View.GONE);
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            tvTitle.setVisibility(View.VISIBLE);
        }
        else
            {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate();
                Fragment f = getSupportFragmentManager().findFragmentByTag("addfragment");
               /* f.setEnterTransition(new Slide(Gravity.RIGHT));
                f.setExitTransition(new Slide(Gravity.LEFT));*/
                whichFragmentInstance(f);
            }
            else {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Exit from Vshop")
                        .setMessage("Are you sure you want to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        }
    }


    @Override
    protected void onResume() {
        Log.d("HomeActivity", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("HomeActivity", "onPause");
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Log.d("HomeActivity", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("HomeActivity", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("HomeActivity", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("HomeActivity", "onDestroy");
        super.onStop();
    }



}
