package com.example.emp354.linear.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.emp354.linear.R;

import java.util.concurrent.Callable;

public class SeekBarRangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seek_bar_range_activity);

        CustomSeekBar custom_seek_bar1=findViewById(R.id.custom_seek_bar1);
       CustomSeekBar custom_seek_bar2=findViewById(R.id.custom_seek_bar2);


    }
}
