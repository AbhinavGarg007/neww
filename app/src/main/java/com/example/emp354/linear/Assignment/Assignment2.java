package com.example.emp354.linear.Assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class Assignment2 extends AppCompatActivity {
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);

        TextView textview_4=findViewById(R.id.textview_4);
        mTopToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        SpannableString text=new SpannableString("Responsibilities Completed\n 0 out of 5");
        text.setSpan(new RelativeSizeSpan(1.5f),0,28, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        text.setSpan(new RelativeSizeSpan(1.5f),36,37, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blue)),27,28,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blue)),36,37,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textview_4.setText(text);



    }
}
