package com.example.emp354.linear.DatabaseAssignmentMaccabi;



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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emp354.linear.Dialog.DatePickerFragment;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;
import com.example.emp354.linear.SaveSharedPreference;


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
    MySharedPreferences mySharedPreferences;


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

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.maccabi_menu_resize_again);



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


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                menu.findItem(R.id.home_icon_edit).setVisible(false);
                menu.findItem(R.id.home_icon_calendar).setVisible(false);

                switch (item.getTitle().toString())
                {


                    case "All Members Details":
                        getSupportActionBar().setTitle(R.string.all_members_details);
                        loadfragment(maccabiAllMembersDetailsFragment);
                        break;

                    case "Like" :
                        getSupportActionBar().setTitle(R.string.likes);

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
    private void loadfragment(Fragment fragment)
    {
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment,fragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.home_icon_edit:
               /* MenuItem item1=(MenuItem)findViewById(R.id.home_icon_edit);
                item1.setVisible(false);*/
               menu.findItem(R.id.home_icon_edit).setVisible(false);
                getSupportActionBar().setTitle("Edit Profile");
               menu.findItem(R.id.home_icon_calendar).setVisible(true);
               loadfragment(maccabiEditProfileFragment);
               break;


            case R.id.home_icon_calendar:

                fragmentManager=getSupportFragmentManager();
                dialogFragment=new MaccabiDatePickerFragment();
                tag="datePicker";
                dialogFragment.show(fragmentManager,tag);
                break;

            /*DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    populateSetDate(year, month+1, dayOfMonth);
                }
            },currentYear,currentMonth,currentDay);
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.YEAR,currentYear-4);
            calendar.add(Calendar.MONTH,currentMonth);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

            datePickerDialog.setCancelable(false);
            datePickerDialog.show();
            return true;*/


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.home_icon,menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
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

                                mySharedPreferences.clearAllData();
                        Intent i=new Intent(MaccabiHomeActivity.this,MailIdScreenMaccabi.class);
                        startActivity(i);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
