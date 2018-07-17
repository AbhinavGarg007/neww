package com.example.emp354.linear.Button;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.emp354.linear.ImageViewAssignment;
import com.example.emp354.linear.R;

public class CompoundBtnAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compoundbtnassignment);

        final Switch switch1 = findViewById(R.id.switch1);
        final Switch switch2 = findViewById(R.id.switch2);
        final CheckBox checkbox_1 = findViewById(R.id.checkbox_1);
        final CheckBox checkbox_2 = findViewById(R.id.checkbox_2);
        final RadioButton radiobutton_1 = findViewById(R.id.radiobutton_1);
        final RadioButton radiobutton_2 = findViewById(R.id.radiobutton_2);
        Button back_btn=findViewById(R.id.back_btn);
        Button next_btn=findViewById(R.id.next_btn);

        final TextView textview_1 = findViewById(R.id.textview_1);
        final TextView textview_2 = findViewById(R.id.textview_2);
        final TextView textView_3 = findViewById(R.id.textview_3);
        final TextView textView_4 = findViewById(R.id.textview_4);
        final TextView textView_5 = findViewById(R.id.textview_5);
        final TextView textView_6 = findViewById(R.id.textview_6);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked()) {
                    textView_5.setText("Switch is on");
                } else {
                    textView_5.setText("Switch is off");
                }
            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch2.isChecked()) {
                    textView_6.setText("Switch is on");
                } else {
                    textView_6.setText("Switch is off");
                }
            }
        });


        checkbox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox_1.isChecked()) {
                    textview_1.setText("Checkbox is checked");
                } else {
                    textview_1.setText("Checkbox is unchecked");
                }
            }
        });
        checkbox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox_2.isChecked()) {
                    textview_2.setText("Checkbox is checked");
                } else {
                    textview_2.setText("Checkbox is unchecked");
                }
            }
        });
        radiobutton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radiobutton_1.setChecked(!radiobutton_1.isChecked());


                if (((RadioButton) view).isChecked()) {
                    textView_3.setText("Radiobutton is checked");
                } else {
                    textView_3.setText("Radiobutton is unchecked");
                }
            }
        });
        radiobutton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radiobutton_2.isChecked()) {
                    textView_4.setText("Radiobutton is checked");
                } else {
                    textView_4.setText("Radiobutton is unchecked");
                }
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CompoundBtnAssignment.this, ImageViewAssignment.class);
                startActivity(i);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(CompoundBtnAssignment.this,DrawableButtonAssignment.class);
                startActivity(j);
            }
        });
    }
}
