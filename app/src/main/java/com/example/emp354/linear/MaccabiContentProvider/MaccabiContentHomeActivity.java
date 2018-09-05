package com.example.emp354.linear.MaccabiContentProvider;

import android.app.DatePickerDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiAllMembersDetailsFragment;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiEditProfileFragment;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiHomeActivity;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiLikeFragment;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiMyProfileFragment;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MailIdScreenMaccabi;
import com.example.emp354.linear.MaccabiContentSharedPreference;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

import java.util.Calendar;

public class MaccabiContentHomeActivity extends AppCompatActivity {


    String mailId;
    Intent j;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    MaccabiContentMyProfileFragment maccabiContentMyProfileFragment;
    MaccabiContentEditProfileFragment maccabiContentEditProfileFragment;
    MaccabiContentAllMembersDetailsFragment maccabiContentAllMembersDetailsFragment;
    MaccabiContentLikeFragment maccabiContentLikeFragment;
    FragmentManager fragmentManager;
    int currentYear,currentMonth,currentDay;
    MaccabiContentSharedPreference maccabiContentSharedPreference;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_content_home);
        maccabiContentSharedPreference=MaccabiContentSharedPreference.getInstance(this);

        drawerLayout =findViewById(R.id.drawer_layout);
        frameLayout=findViewById(R.id.layout_fragment);
        navigationView=findViewById(R.id.nav_view);
        fragmentManager=getSupportFragmentManager();

        calendar=Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.maccabi_menu_resize_again);
        toolbar.inflateMenu(R.menu.home_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {

                    case R.id.home_icon_edit:
                        loadfragment(maccabiContentEditProfileFragment);
                        break;


                    case R.id.home_icon_calendar:
                        DatePickerDialog datePickerDialog=new DatePickerDialog(MaccabiContentHomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                populateSetDate(year, month+1, dayOfMonth);
                            }
                        },currentYear,currentMonth,currentDay);
                        Calendar newCalendar=Calendar.getInstance();
                        newCalendar.set(Calendar.YEAR,(currentYear-4));
                        newCalendar.set(Calendar.MONTH,currentMonth);
                        datePickerDialog.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
                        datePickerDialog.setCancelable(false);
                        datePickerDialog.show();
                        return true;


                }
                return true;
            }
        });

        j=getIntent();
        mailId=j.getStringExtra("mailId");

        maccabiContentMyProfileFragment=new MaccabiContentMyProfileFragment();
        maccabiContentEditProfileFragment=new MaccabiContentEditProfileFragment();
        maccabiContentAllMembersDetailsFragment=new MaccabiContentAllMembersDetailsFragment();
        maccabiContentLikeFragment=new MaccabiContentLikeFragment();

        Bundle c=new Bundle();
        c.putString("mailId",mailId);
        maccabiContentMyProfileFragment.setArguments(c);
        maccabiContentEditProfileFragment.setArguments(c);
        loadfragment(maccabiContentMyProfileFragment);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getTitle().toString())
                { case "All Members Details":
                    loadfragment(maccabiContentAllMembersDetailsFragment);
                    break;

                    case "Like" :
                        loadfragment(maccabiContentLikeFragment);
                        break;
                    case "Log Out" :
                        logOut();
                        break;
                }
                return true;
            }
        });



    }
    public void loadfragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment,fragment,"addfragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void checkFragment()
    {
        Fragment fragment;

        fragment=getSupportFragmentManager().findFragmentByTag("addfragment");
        toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
        toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);

        if (fragment instanceof MaccabiContentMyProfileFragment)
        {
            toolbar.setTitle("My Profile");
            toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(true);
            toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);

        }
        if(fragment instanceof MaccabiContentEditProfileFragment)
        {
            toolbar.setTitle("Edit Profile");
            toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
            toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(true);

        }
        if(fragment instanceof MaccabiContentAllMembersDetailsFragment)
        {
            toolbar.setTitle(R.string.all_members_details);


        }
        if(fragment instanceof MaccabiContentLikeFragment)
        {
            toolbar.setTitle(R.string.likes);


        }
    }
    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount()>1)
        {
            fragmentManager.popBackStackImmediate();
            Fragment f = fragmentManager.findFragmentByTag("addfragment");
            if(f instanceof MaccabiContentMyProfileFragment)
            {
                toolbar.setTitle("My Profile");
                toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(true);
                toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);
            }
            else if(f instanceof MaccabiContentEditProfileFragment)
            {
                toolbar.setTitle("Edit Profile");
                toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
                toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(true);
            }
            else if(f instanceof MaccabiContentAllMembersDetailsFragment)
            {
                toolbar.setTitle(R.string.all_members_details);

            }
            else if(f instanceof MaccabiContentLikeFragment)
            {
                toolbar.setTitle(R.string.likes);

            }
        }
        else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing Activity")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }
    }


    //logout functionality
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

                        maccabiContentSharedPreference.clearAllData();
                        Intent i=new Intent(MaccabiContentHomeActivity.this,MaccabiContentMailId.class);
                        startActivity(i);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    //to calculate age based on inserted dob
    private String calculateAge(int year,int month,int day)
    {
        Calendar dob=Calendar.getInstance();
        Calendar today=Calendar.getInstance();
        dob.set(year,month,day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    //to set text on hidden textview
    private void populateSetDate(int year,int month,int day)
    {String dob=day+"-"+month+"-"+year;

        /*maccabiEditProfileFragment.tv_dob.setText(dob);
        String age=calculateAge(year,month,day);
        maccabiEditProfileFragment.tv_age.setText(age);*/
    }

}
