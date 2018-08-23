package com.example.emp354.linear.DatabaseAssignmentMaccabi;



import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emp354.linear.R;


public class MaccabiHomeActivity extends AppCompatActivity {
    String mailId;
    Intent j;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView imageView;
    MaccabiMyProfileFragment maccabiMyProfileFragment;
    MaccabiEditProfileFragment maccabiEditProfileFragment;



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



        j=getIntent();
        mailId=j.getStringExtra("mailId");

        maccabiMyProfileFragment=new MaccabiMyProfileFragment();
        maccabiEditProfileFragment=new MaccabiEditProfileFragment();
        // set arguements for fragment
        Bundle c=new Bundle();
        c.putString("mailId",mailId);
        maccabiMyProfileFragment.setArguments(c);
        maccabiEditProfileFragment.setArguments(c);

        //loading myprofile fragment
        loadfragment(maccabiMyProfileFragment);


    }
    private void loadfragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
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
                return true;

            case R.id.home_icon:
                loadfragment(maccabiEditProfileFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_icon,menu);
        return true;
    }
}
