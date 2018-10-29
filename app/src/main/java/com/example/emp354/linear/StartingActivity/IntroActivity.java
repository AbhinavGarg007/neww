package com.example.emp354.linear.StartingActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {


    private PrefManager prefManager;
    IntroAdapter introAdapter;
    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button btnNext,btnSkip;
    TextView[] mDots;
    int currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the app is launched first time before setting layout
        prefManager=new PrefManager(this);
        if(!prefManager.isFirstLaunch())
        {
            launchMainScreen();//go to main activity
        }
        setContentView(R.layout.activity_intro);
        transparentStatusBar(); //make status bar transparent
        findViews(); //find xml views in java
        setClickListener(); //set on click listeners
        setUpViewPager(); //setup viewpager
        addDotsIndicator(0); //called for the first launch
    }


    private void launchMainScreen()
    {
        prefManager.setIsFirstLaunch(false);
        startActivity(new Intent(IntroActivity.this,AfterIntro.class));
        finish();
    }

    private void transparentStatusBar()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }


    private void setUpViewPager()
    {
       introAdapter=new IntroAdapter(this);
       viewPager.setAdapter(introAdapter);
       viewPager.addOnPageChangeListener(pageChangeListener);
    }

    private void findViews()
    {
        viewPager=findViewById(R.id.viewpager_intro);
        dotsLayout=findViewById(R.id.layoutDots);
        btnNext=findViewById(R.id.btn_next);
        btnSkip=findViewById(R.id.btn_skip);

    }

    private void setClickListener()
    {
        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void addDotsIndicator(int position)
    {
        //adding textview dynamically
      mDots=new TextView[introAdapter.getCount()];

      //remove view when called next time if not called then view will keep on adding
        dotsLayout.removeAllViews();

        //see bullet in each dot textview
        for(int i=0;i<mDots.length;i++)
        {
         mDots[i]=new TextView(this);
         mDots[i].setText(Html.fromHtml("•")); //html code for bullet
         mDots[i].setTextSize(35);
         mDots[i].setTextColor(getResources().getColor(R.color.dot_inactive_color));
         dotsLayout.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            //change color of current selected dot
            mDots[position].setTextColor(getResources().getColor(R.color.dot_active_color));

        }

    }

    ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            currentItem=position; //currentItem used to get current position
            addDotsIndicator(position);

            //change the next button to "next"

            if(position==introAdapter.getCount()-1)
            {
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_next:
                if(currentItem<introAdapter.getCount()-1)
                {
                    ++currentItem;
                    viewPager.setCurrentItem(currentItem);
                }
                else {
                    launchMainScreen();
                }
                break;

            case R.id.btn_skip:
                launchMainScreen();
                break;

        }
    }
}
