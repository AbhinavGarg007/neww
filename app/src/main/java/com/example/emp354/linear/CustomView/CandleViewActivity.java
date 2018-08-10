package com.example.emp354.linear.CustomView;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class CandleViewActivity extends AppCompatActivity {

    EditText et_even_no;
    Button submit_btn;
    TextView mTextField;
   /* Rect myRect;*/
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candle_view);

        et_even_no = findViewById(R.id.et_even_no);
        submit_btn = findViewById(R.id.submit_btn);
        mTextField = findViewById(R.id.textview);
        frameLayout = findViewById(R.id.candle_layout);
        /* View myView=findViewById(R.id.myview);*/
        /* myRect=new Rect();
         myRect.left=300;
        myRect.right=500;
        myRect.bottom=500;
        myRect.top=400;*/
        /*myView.draw(canvas);*/


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int totalTime = Integer.valueOf(et_even_no.getText().toString());

                new CountDownTimer(totalTime * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainprogress = (int) (millisUntilFinished / 1000);

                        Candle myView = findViewById(R.id.myview);
                        int height=frameLayout.getHeight();
                      /*  int totalTime2=totalTime/2;*/

                        if((millisUntilFinished/1000)>=(totalTime/2)) {

                            /* int top = (int) (height-(50*(millisUntilFinished/1000)));*/
                            int top = (int) ((height / totalTime) * (millisUntilFinished / 1000));
                            myView.setTopHeight(top);
                            myView.setVisibility(View.VISIBLE);
                            myView.invalidate();

                            mTextField.setText(String.valueOf(millisUntilFinished/1000));



                        }
                        else {
                            int top = (int) ((height / totalTime) *(totalTime- (millisUntilFinished / 1000)));
                            myView.setTopHeight(top);
                            myView.setVisibility(View.VISIBLE);
                            myView.invalidate();

                            mTextField.setText(String.valueOf(millisUntilFinished/1000));

                        }
                        }
                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });

    }
}




