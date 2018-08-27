package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

public class SplashMaccabi extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    MySharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_maccabi);
        mySharedPreferences=MySharedPreferences.getInstance(this);

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    if(mySharedPreferences.fetchId()==-1)
                    {
                    Intent mainIntent = new Intent(SplashMaccabi.this,MailIdScreenMaccabi.class);
                    SplashMaccabi.this.startActivity(mainIntent);
                    SplashMaccabi.this.finish();}
                    else
                    {
                        Intent mainIntent = new Intent(SplashMaccabi.this,MaccabiHomeActivity.class);
                        SplashMaccabi.this.startActivity(mainIntent);
                        SplashMaccabi.this.finish();}
                    }
                },SPLASH_DISPLAY_LENGTH);
        }
    }

