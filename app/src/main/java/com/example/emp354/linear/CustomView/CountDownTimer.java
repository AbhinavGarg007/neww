package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class CountDownTimer extends AppCompatActivity {
TextView mTextField;
EditText mEditText;
Button submit_btn;
MyCountDownTimer myCountDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down_timer);

         mTextField=findViewById(R.id.textview);
         submit_btn=findViewById(R.id.submit_btn);
         mEditText=findViewById(R.id.et_even_no);

         submit_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String time=mEditText.getText().toString();
                 myCountDownTimer = new MyCountDownTimer(Integer.valueOf(time)*1000, 1000) ;
                 myCountDownTimer.start();
                 }
         });
         }




    public class MyCountDownTimer extends android.os.CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int remainprogress = (int) (millisUntilFinished / 1000);
            String progress = mEditText.getText().toString();
            final int intprogress = Integer.valueOf(progress);

            if (intprogress % 2 != 0) {
                Toast.makeText(getApplicationContext(), "Please enter the number divisible by 2", Toast.LENGTH_SHORT).show();
            } else {

                FrameLayout candle_layout = findViewById(R.id.candle_layout);
                mTextField.setText(String.valueOf(intprogress - remainprogress));
            /*Canvas canvas=new Canvas();
            canvas.getHeight();
            canvas.getHeight();
            Candle candle = new Candle(getApplicationContext());
            candle.draw(canvas);*/
            }
        }
        @Override
        public void onFinish() {
                finish();
            }
            }
/*

    public class Candle extends View
    {Paint mPaint;

        public Candle(Context context) {
            super(context);
            init();
        }

        public Candle(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Candle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }
        private void init()
        {mPaint=new Paint();
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL);
        }
        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawRect(50.0f,(getHeight()/10),250.0f,getHeight(),mPaint);
        }
    }
*/

}
