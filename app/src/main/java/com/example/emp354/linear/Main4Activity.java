package com.example.emp354.linear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.VisualVoicemailService;
import android.telephony.VisualVoicemailSms;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button b1=findViewById(R.id.b1);
        Button b3=findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main4Activity.this,Main5Activity.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Main4Activity.this,Main3Activity.class);
                startActivity(i);
            }
        });
    }
}
