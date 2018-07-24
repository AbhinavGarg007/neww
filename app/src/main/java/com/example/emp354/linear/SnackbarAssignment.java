package com.example.emp354.linear;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.emp354.linear.AdapterView.ListViewAssignment1;
import com.example.emp354.linear.Toast.CustomToastAssignment;

public class SnackbarAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snackbarassignment);

        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i=new Intent(SnackbarAssignment.this, CustomToastAssignment.class);
                                        startActivity(i);
                                    }
                                }
        );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(SnackbarAssignment.this, ListViewAssignment1.class);
                startActivity(j);
            }
        });

        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearlayout);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar= Snackbar.make(linearLayout,"Simple Snack_bar", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });findViewById(R.id.btn_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(linearLayout, "Snack_bar with Callback", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(linearLayout, "Snack_bar with Callback called.", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                snackbar.show();
            }
        });

    }
}
