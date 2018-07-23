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

public class Child_view extends AppCompatActivity {
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_view);

        TextView textview_4=findViewById(R.id.textview_4);
        TextView textview_5=findViewById(R.id.textview_5);
        TextView textview_6=findViewById(R.id.textview_6);
        TextView textview_7=findViewById(R.id.textview_7);
        TextView textview_8=findViewById(R.id.textview_8);
        mTopToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        SpannableString text=new SpannableString("Responsibilities Completed\n 0 out of 5");
        text.setSpan(new RelativeSizeSpan(1.5f),0,27, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        text.setSpan(new RelativeSizeSpan(2f),28,29, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        text.setSpan(new RelativeSizeSpan(2f),37,38, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blue)),28,29,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blue)),37,38,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textview_4.setText(text);

        SpannableString text_1=new SpannableString("1");
        SpannableString text_2=new SpannableString("30\nToday's\nAllowance");
        SpannableString text_3=new SpannableString("30\nTime\nLeft");
        text_1.setSpan(new RelativeSizeSpan(6f),0,1,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text_2.setSpan(new RelativeSizeSpan(2f),0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text_3.setSpan(new RelativeSizeSpan(2f),0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textview_5.setText(text_1);
        textview_6.setText(text_2);
        textview_7.setText(text_1);
        textview_8.setText(text_3);



    }
}
