package com.example.emp354.linear.Assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.AdapterView.RecyclerViewAssignment3;
import com.example.emp354.linear.R;

public class Signup_page extends AppCompatActivity {
private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        ImageView back=findViewById(R.id.back);
        Button signup_btn=findViewById(R.id.signup_btn);

        TextView signup_policy_textview=findViewById(R.id.signup_policy_textview);
        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        final SpannableString text = new SpannableString("By signing up you agree to our\nPrivacy Policy & Terms of Service");
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 31, 44, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 50, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup_policy_textview.setText(text);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup_page.this,RecyclerViewAssignment3.class);
                startActivity(i);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(Signup_page.this,Child_view.class);
                startActivity(j);
            }
        });

    }


}
