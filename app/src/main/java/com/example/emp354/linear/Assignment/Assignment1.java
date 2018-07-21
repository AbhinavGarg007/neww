package com.example.emp354.linear.Assignment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class Assignment1 extends AppCompatActivity {
private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment1);

        TextView signup_policy_textview=findViewById(R.id.signup_policy_textview);
        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        final SpannableString text = new SpannableString("By signing up you agree to our\nPrivacy Policy & Terms of Service");
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 31, 44, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 50, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup_policy_textview.setText(text);

    }


}
