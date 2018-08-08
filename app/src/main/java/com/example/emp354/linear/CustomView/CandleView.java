package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class CandleView extends AppCompatActivity {

    EditText et_even_no;
    Button submit_btn;
    TextView mTextField;
    Rect myRect;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candle_view);

        et_even_no=findViewById(R.id.et_even_no);
        submit_btn=findViewById(R.id.submit_btn);
        mTextField=findViewById(R.id.textview);
        frameLayout=findViewById(R.id.candle_layout);
       /* View myView=findViewById(R.id.myview);*/

        final MyView myView=new MyView(getApplicationContext());
        myView.setTopHeight(100);

        Canvas canvas=new Canvas();
        /* myRect=new Rect();
         myRect.left=300;
        myRect.right=500;
        myRect.bottom=500;
        myRect.top=400;*/
        myView.draw(canvas);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int totalTime=Integer.valueOf(et_even_no.getText().toString());


                new CountDownTimer(totalTime*1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainprogress = (int) (millisUntilFinished / 1000);
                        mTextField.setText(String.valueOf(totalTime - remainprogress));

                        /*for(int i=0;i<10;i++){
                            myView.setTopHeight(i*100);
                        }
*/
                        }
                    @Override
                    public void onFinish() {
                        finish();
                        }
                }.start();
                }
        });

        }

/*
    public class MyView extends View {

        Paint mPaint;

        public MyView(Context context) {
            super(context);
            init();
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();

        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }
        private void init()
        {
            mPaint=new Paint();
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRect(myRect,mPaint);
           *//* canvas.drawRect(300.0f,getHeight()-(getHeight()/10),500.0f,getHeight(),mPaint);*//*
        }


    }*/


}
