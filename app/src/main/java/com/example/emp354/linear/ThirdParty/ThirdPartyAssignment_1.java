package com.example.emp354.linear.ThirdParty;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emp354.linear.R;

public class ThirdPartyAssignment_1 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ThirdPartyAdapter thirdPartyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_assignment_1);

        tabLayout=findViewById(R.id.tablayout_third_party);
        viewPager=findViewById(R.id.viewpager_third_party);
        thirdPartyAdapter=new ThirdPartyAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(thirdPartyAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
