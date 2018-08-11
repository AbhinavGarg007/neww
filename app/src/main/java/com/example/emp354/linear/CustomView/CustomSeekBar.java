package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomSeekBar extends View{


    private Paint mLine,mCircle;
    private float start1X=50.0f;
    private float start2X;
    float height;
    float width;



    public CustomSeekBar(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mLine=new Paint();
        mLine.setColor(Color.GRAY);
        mLine.setStyle(Paint.Style.STROKE);
        mLine.setStrokeWidth(10.0f);

        mCircle=new Paint();
        mCircle.setColor(Color.BLUE);
        mCircle.setStyle(Paint.Style.FILL);
        mCircle.setAlpha(150);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        height=getHeight();
        float margin=50.0f;
        width=getWidth();
        float radius=30.0f;


        canvas.drawLine(margin,height/2,width-margin,height/2,mLine);
        canvas.drawCircle(start1X,height/2,radius,mCircle);
        canvas.drawCircle(start2X,height/2,radius,mCircle);
        /*canvas.drawT*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*int action= MotionEventCompat.getActionMasked(event);*/
        int action=event.getAction();

        float x=event.getX();
        float y=event.getY();

        float part=(width-100.0f)/100.0f;
        int progress=(int)(x/part);



        switch (action)
        {
            case(MotionEvent.ACTION_DOWN):

              /*  if(action==0.0f)
                {
                    start1X=50.0f;
                }
                else
                {
                    start1X=x;
                }*/

              start1X=x;
                Toast.makeText(CustomSeekBar.super.getContext(),String.valueOf( progress),Toast.LENGTH_SHORT).show();
               start2X=start1X+200;
               invalidate();
                break;

            case(MotionEvent.ACTION_MOVE):
                if(action==0.0f)
                {
                    start1X=50.0f;
                }
                else
                {
                    start1X=x ;
                }

                Toast.makeText(CustomSeekBar.super.getContext(), String.valueOf(progress),Toast.LENGTH_SHORT).show();
                start2X=start1X+200;
                invalidate();
                break;

            case(MotionEvent.ACTION_UP):
                invalidate();
                break;
            case(MotionEvent.ACTION_CANCEL):
                invalidate();
                break;





        }
        return true;
    }
}
