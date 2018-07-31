package com.example.emp354.linear.ViewPager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.emp354.linear.R;

public class ViewPagerMain extends AppCompatActivity {


  /*  public static final String[] tabtitles={MYCOURSES,BOOKMARKED,NEARBY};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagermain);

        Toolbar mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ViewPager viewPager=findViewById(R.id.view_pager);
        ViewPagerAdapter view_pager_adapter=new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter);

        TabLayout tabLayout=findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);




    }
}
