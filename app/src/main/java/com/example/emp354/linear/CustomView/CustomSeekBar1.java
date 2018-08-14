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

import static java.lang.StrictMath.abs;

public class CustomSeekBar1 extends View{


    private Paint mLine,mCircle1,mCircle2,mProgress,mText1,mText2;
    private float start1X=50.0f;
    private float start2X=670.0f;

    float startMoveX;
    float startMoveY;
    float textsize=50.0f;
    float margin=50.0f;
    private int mActivePointerId;
    int progress1=0;
    int progress2=100;



    float height;
    float width;
    float line_width;
    float diff;

    public CustomSeekBar1(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mLine=new Paint();
        mLine.setColor(Color.GRAY);
        mLine.setStyle(Paint.Style.STROKE);
        mLine.setStrokeWidth(10.0f);

        mCircle1=new Paint();
        mCircle1.setColor(Color.RED);
        mCircle1.setStyle(Paint.Style.FILL);
        mCircle1.setAlpha(150);

        mCircle2=new Paint();
        mCircle2.setColor(Color.BLUE);
        mCircle2.setStyle(Paint.Style.FILL);
        mCircle2.setAlpha(150);

        mProgress=new Paint();
        mProgress.setColor(Color.GREEN);
        mProgress.setStyle(Paint.Style.FILL);
        mProgress.setStrokeWidth(15.0f);

        mText1=new Paint();
        mText1.setColor(Color.BLACK);
        mText1.setTextSize(textsize);

       /* mText2=new Paint();
        mText2.setColor(Color.BLACK);
        mText2.setTextSize(textsize);
*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        height=getHeight();
        margin=50.0f;
        width=getWidth();
        line_width=width-(margin+margin);
        diff=20*(line_width/100);
        float radius=30.0f;

        canvas.drawLine(margin,height/2,width-margin,height/2,mLine);
        canvas.drawLine(start1X,height/2,start2X,height/2,mProgress);
        canvas.drawCircle(start1X,height/2,radius,mCircle1);
        canvas.drawCircle(start2X,height/2,radius,mCircle2);
        canvas.drawText(String.valueOf(progress1),120.0f,400.0f,mText1);
        canvas.drawText(String.valueOf(progress2),550.0f,400.0f,mText1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*int action= MotionEventCompat.getActionMasked(event);*/
        int action=event.getAction();

        float x=event.getX();
        float y=event.getY();




        switch (action)
        {
            case(MotionEvent.ACTION_DOWN):

               /* && start2X-x>=(20*(line_width/100))*/

                if(x>margin && x<(width-margin)) {
                    if (abs(x - start1X) < abs(start2X - x) ) {

                        int r_progress1 = (int) (((x - margin) / line_width) * 100);
                        if(progress2-r_progress1>=20)
                        {start1X = x;
                       /* start2X=start1X+diff;*/
                            progress1=r_progress1;
                            /*progress2=progress1+20;*/
                        }else  if(r_progress1<=80){
                            start1X=x;
                            progress1=r_progress1;
                            start2X=start1X+(diff);
                            progress2=progress1+20;
                        }

                            /*progress1=r_progress1;*/
                            /*if(progress2-r_progress1>=20)
                                progress1=r_progress1;
                            else
                                progress1=progress2-20;*/


                    } else {

                        int r_progress2 = (int) (((x - margin) / line_width) * 100);
                        if(r_progress2-progress1>=20)
                        {start2X = x;
                            progress2=r_progress2;
                        }
                        else if(r_progress2>=20)
                        {start2X=x;
                            progress2=r_progress2;
                            start1X=start2X-diff;
                            progress1=progress2-20;
                        }
                        /*if(r_progress2>100)
                        {
                            progress2=100;
                        }
                        else
                        {
                            progress2=r_progress2;
                        }*/
                    }
                }else {

                    if (abs(x - start1X) < abs(start2X - x)) {
                        start1X = margin;
                        progress1 = 0;
                    }
                    else {
                        start2X = width - margin;
                        progress2 = 100;
                    }
                }

                /*if (x > 20.0f && x < 80.0f) {
                        start1X = 50.0f;
                    } else if (x > 640.0f && x < 700.0f) {
                        start2X = 670.0f;
                    }*/

                    /*if (progress1 <0) {
                        start1X = margin;
                        progress1 = 0;
                    } else if (progress2 >100) {
                        start2X = width - margin;
                        progress2 = 100;}*/
                    /* if (progress2 - progress1 >= 20) {
                        if (abs(x - start1X) < abs(start2X - x)) {
                            start1X = x;
                            progress1 = (int) (((x - margin) / line_width) * 100);

                        } else {
                            start2X = x;
                            progress2 = (int) (((x - margin) / line_width) * 100);
                        }
                    } else {
                        start2X = start1X + 20 * (line_width / 100);
                        progress1 = (int) (((start1X - margin) / line_width) * 100);
                        progress2 = (int) (((start2X - margin) / line_width) * 100);
                    }*/





                    invalidate();
                break;


                case(MotionEvent.ACTION_MOVE):

                    if(x>margin && x<(width-margin)) {
                        if (abs(x - start1X) < abs(start2X - x)) {

                            int r_progress1 = (int) (((x - margin) / line_width) * 100);
                            /*if(r_progress1<0)
                            {
                                progress1=0;
                            }else
                            {
                                if(progress2-r_progress1>=20)
                                progress1=r_progress1;
                                else
                                    progress1=progress2-20;
                            }*/

                            if(progress2-r_progress1>=20)
                            {start1X = x;
                                progress1=r_progress1;
                            }else if(r_progress1<=80) {
                                start1X = x;
                                progress1 = r_progress1;
                                start2X = start1X + diff;
                                progress2 = progress1 + 20;
                            }

                                /*progress1=r_progress1;*/
                            /*if(progress2-r_progress1>=20)
                                progress1=r_progress1;
                            else
                                progress1=progress2-20;*/



                        } else {

                            int r_progress2 = (int) (((x - margin) / line_width) * 100);
                            if(r_progress2-progress1>=20)
                            {start2X = x;
                                progress2=r_progress2;
                            }
                            else if(r_progress2>=20) {
                                start2X = x;
                                progress2 = r_progress2;
                                start1X = start2X - diff;
                                progress1 = progress2 - 20;

                            }
                        }
                    }else {
                        if (abs(x - start1X) < abs(start2X - x)) {
                            start1X = margin;
                            progress1 = 0;
                        }
                        else {
                            start2X = width - margin;
                            progress2 = 100;
                        }
                    }


                   /* if(progress1<0)
                    {
                        start1X=margin;
                        progress1=0;
                    }
                   else if (progress2>100)
                    {
                        start2X=width-margin;
                        progress2=100;
                    }
                     else if(progress2-progress1>=20) {
                        if (abs(x - start1X) < abs(start2X - x)) {
                            start1X = x;
                            progress1=(int)(((x-margin)/line_width)*100);
                        } else {
                            start2X = x;
                            progress2=(int)(((x-margin)/line_width)*100);
                        }
                    }
                    else
                    {
                        start2X=start1X+20*(line_width/100);
                        progress1=(int)(((start1X-margin)/line_width)*100);
                        progress2=(int)(((start2X-margin)/line_width)*100);
                    }*/





                    invalidate();
                break;


            case(MotionEvent.ACTION_UP):
                float x2=getX();


                break;
            case(MotionEvent.ACTION_CANCEL):
        }




        return true;
    }
}


/*
 switch (action)
         {
         case(MotionEvent.ACTION_DOWN):



              */
/*  if(action==0.0f)
                {
                    start1X=50.0f;
                }
                else
                {
                    start1X=x;
                }*//*

             */
/* if(x<50.0f && x>670.0f)

              start1X=x;*//*



         if(x<50.0f)
        {
        start1X=50.0f;
        start2X=250.0f;
        }
        else if(x>670.0f)
        {
        start2X=670.0f;
        start1X=start2X-200.0f;
        }
        else
        {
        start1X=x;
        start2X=x+200.0f;
        }
        Toast.makeText(CustomSeekBar.super.getContext(),String.valueOf( x),Toast.LENGTH_SHORT).show();

        */
/* start2X=start1X+200;*//*


        invalidate();
        break;

        case(MotionEvent.ACTION_MOVE):
                */
/*if(action==0.0f)
                {
                    start1X=50.0f;
                }
                else
                {
                    start1X=x ;
                }*//*


        if (x <= 50.0f) {
        start1X = 50.0f;
        */
/* start2X = 250.0f;*//*

        } else if (x >= 470.0f) {
        start2X = 670.0f;
        start1X=470.0f;
        */
/* start1X = start2X - 200.0f;*//*

        } else {
        start1X = x;
        */
/*start2X = x + 200.0f;*//*

        }
        */
/*   start1X=x;*//*

        Toast.makeText(CustomSeekBar.super.getContext(), String.valueOf(x),Toast.LENGTH_SHORT).show();
        */
/* start2X=start1X+200;*//*

        invalidate();
        break;

        case(MotionEvent.ACTION_UP):

        if (start1X == 50.0f) {
        start2X = 250.0f;
        }  else {
        start2X = start1X + 200.0f;
        }

        invalidate();
        break;
        case(MotionEvent.ACTION_CANCEL):
        invalidate();
        break;
        }*/










   /* float part=(width-100.0f)/100.0f;
    int progress=(int)(x/part);
// Get the pointer ID
        mActivePointerId = event.getPointerId(0);

                // ... Many touch events later...

                // Use the pointer ID to find the index of the active pointer
                // and fetch its position
                int pointerIndex = event.findPointerIndex(mActivePointerId);
                // Get the pointer's current position
                float x2 = event.getX(pointerIndex);
                float y2 = event.getY(pointerIndex);
*/