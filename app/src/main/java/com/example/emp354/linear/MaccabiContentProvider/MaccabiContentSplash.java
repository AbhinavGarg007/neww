package com.example.emp354.linear.MaccabiContentProvider;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiHomeActivity;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MailIdScreenMaccabi;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.SplashMaccabi;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

public class MaccabiContentSplash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    MySharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_content_splash);

        mySharedPreferences=MySharedPreferences.getInstance(this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(mySharedPreferences.fetchId()==-1)
                {
                    Intent mainIntent = new Intent(MaccabiContentSplash.this,MaccabiContentMailId.class);
                    MaccabiContentSplash.this.startActivity(mainIntent);
                    MaccabiContentSplash.this.finish();}
                else
                {
                    Intent mainIntent = new Intent(MaccabiContentSplash.this,MaccabiHomeActivity.class);
                    MaccabiContentSplash.this.startActivity(mainIntent);
                    MaccabiContentSplash.this.finish();}
                /*Intent mainIntent = new Intent(MaccabiContentSplash.this,MaccabiContentMailId.class);
                MaccabiContentSplash.this.startActivity(mainIntent);
                MaccabiContentSplash.this.finish();*/
            }
        },SPLASH_DISPLAY_LENGTH);
    }
    }

