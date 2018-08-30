package com.example.emp354.linear.DatabaseAssignmentMaccabi;



import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emp354.linear.Dialog.DatePickerFragment;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;
import com.example.emp354.linear.SaveSharedPreference;

import java.util.Calendar;


public class MaccabiHomeActivity extends AppCompatActivity {
    String mailId;
    Intent j;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    MaccabiMyProfileFragment maccabiMyProfileFragment;
    MaccabiEditProfileFragment maccabiEditProfileFragment;
    MaccabiAllMembersDetailsFragment maccabiAllMembersDetailsFragment;
    MaccabiLikeFragment maccabiLikeFragment;
    /*DatePickerFragment datePickerFragment;*/
    DialogFragment dialogFragment;
    FragmentManager fragmentManager;
    String tag;
    Menu menu;
    int currentYear,currentMonth,currentDay;
    MySharedPreferences mySharedPreferences;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_home);
        drawerLayout =findViewById(R.id.drawer_layout);
        frameLayout=findViewById(R.id.layout_fragment);
        navigationView=findViewById(R.id.nav_view);

        //configure toolbar
        toolbar=findViewById(R.id.toolbar);
        /*imageView=findViewById(R.id.toolbar_home);*/

        calendar=Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.maccabi_menu_resize_again);
*/
        fragmentManager=getSupportFragmentManager();

        toolbar.setNavigationIcon(R.drawable.maccabi_menu_resize_again);
        toolbar.inflateMenu(R.menu.home_icon);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                }


        });


        // to load fragment on menu item click
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {

                    case R.id.home_icon_edit:
               /* MenuItem item1=(MenuItem)findViewById(R.id.home_icon_edit);
                item1.setVisible(false);*/
                        loadfragment(maccabiEditProfileFragment);
                        break;


                    case R.id.home_icon_calendar:

               /* fragmentManager=getSupportFragmentManager();
                dialogFragment=new MaccabiDatePickerFragment();
                tag="datePicker";
                dialogFragment.show(fragmentManager,tag);
                break;*/
                        DatePickerDialog datePickerDialog=new DatePickerDialog(MaccabiHomeActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        mySharedPreferences=MySharedPreferences.getInstance(this);
        j=getIntent();
        mailId=j.getStringExtra("mailId");

        maccabiMyProfileFragment=new MaccabiMyProfileFragment();
        maccabiEditProfileFragment=new MaccabiEditProfileFragment();
        maccabiAllMembersDetailsFragment=new MaccabiAllMembersDetailsFragment();
        maccabiLikeFragment=new MaccabiLikeFragment();

        // set arguements for fragment
        Bundle c=new Bundle();
        c.putString("mailId",mailId);
        maccabiMyProfileFragment.setArguments(c);
        maccabiEditProfileFragment.setArguments(c);

        //loading myprofile fragment
        loadfragment(maccabiMyProfileFragment);




        //to load fragment on navigation drawer item click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getTitle().toString())
                { case "All Members Details":
                        loadfragment(maccabiAllMembersDetailsFragment);
                        break;

                    case "Like" :
                        loadfragment(maccabiLikeFragment);
                        break;
                    case "Log Out" :
                        logOut();
                        break;
                }
                return true;
            }
        });


    }



    // to load fragment
    public void loadfragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment,fragment,"addfragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.home_icon_edit:
               *//* MenuItem item1=(MenuItem)findViewById(R.id.home_icon_edit);
                item1.setVisible(false);*//*
               loadfragment(maccabiEditProfileFragment);
               break;


            case R.id.home_icon_calendar:

               *//* fragmentManager=getSupportFragmentManager();
                dialogFragment=new MaccabiDatePickerFragment();
                tag="datePicker";
                dialogFragment.show(fragmentManager,tag);
                break;*//*
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
        return super.onOptionsItemSelected(item);
    }*/


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.home_icon,menu);
        return true;
    }*/

    // to check whichfragment is going to load
    //according to that change toolbar icon
    public void checkFragment()
    {
        Fragment fragment;

        fragment=getSupportFragmentManager().findFragmentByTag("addfragment");
        toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
        toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);

        if (fragment instanceof MaccabiMyProfileFragment)
        {
            toolbar.setTitle("My Profile");
            toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(true);
            toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);

        }
        if(fragment instanceof MaccabiEditProfileFragment)
        {
            toolbar.setTitle("Edit Profile");
            toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
            toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(true);

        }
        if(fragment instanceof MaccabiAllMembersDetailsFragment)
        {
            toolbar.setTitle(R.string.all_members_details);


        }
        if(fragment instanceof MaccabiLikeFragment)
        {
            toolbar.setTitle(R.string.likes);


        }
    }

    //backpress functionality
    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount()>1)
        {
            fragmentManager.popBackStackImmediate();
            Fragment f = fragmentManager.findFragmentByTag("addfragment");
            if(f instanceof MaccabiMyProfileFragment)
            {
                toolbar.setTitle("My Profile");
                toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(true);
                toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(false);
            }
            else if(f instanceof MaccabiEditProfileFragment)
            {
                toolbar.setTitle("Edit Profile");
                toolbar.getMenu().findItem(R.id.home_icon_edit).setVisible(false);
                toolbar.getMenu().findItem(R.id.home_icon_calendar).setVisible(true);
            }
            else if(f instanceof MaccabiAllMembersDetailsFragment)
            {
                toolbar.setTitle(R.string.all_members_details);

            }
            else if(f instanceof MaccabiLikeFragment)
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

                                mySharedPreferences.clearAllData();
                        Intent i=new Intent(MaccabiHomeActivity.this,MailIdScreenMaccabi.class);
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

     maccabiEditProfileFragment.tv_dob.setText(dob);
     String age=calculateAge(year,month,day);
     maccabiEditProfileFragment.tv_age.setText(age);
    }


}
