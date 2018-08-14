package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static java.lang.StrictMath.abs;

public class CustomSeekBar2 extends View{

    private Paint mLine,mCircle1,mCircle2,mProgress,mText1,mText2;
    private float start1X=50.0f;
    private float start2X=174.0f;

    float startMoveX;
    float startMoveY;
    float textsize=50.0f;
    float margin=50.0f;
    private int mActivePointerId;
    int progress1=0;
    int progress2=20;



    float height;
    float width;
    float line_width;
    float diff;

    public CustomSeekBar2(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                    if (abs(x - start1X) < abs(start2X - x)) {

                        int r_progress1 = (int) (((x - margin) / line_width) * 100);


                        if(progress2-r_progress1<=20 && r_progress1<=80)
                        {start1X = x;
                            start2X=x+diff;
                            progress1=r_progress1;
                            progress2=r_progress1+20;
                        }else if(progress2-r_progress1>=40 && r_progress1>=0) {
                            start1X = x;
                            progress1 = r_progress1;
                            start2X = start1X + (2*diff);
                            progress2 = progress1 + 40;
                        }
                        else if(r_progress1>=0 && r_progress1<=80)
                        {
                            start1X=x;
                            progress1=r_progress1;
                        }



                    } else {

                        int r_progress2 = (int) (((x - margin) / line_width) * 100);
                        if(r_progress2-progress1<=20 && r_progress2>=20)
                        {start2X = x;
                            start1X=x-diff;
                            progress2=r_progress2;
                            progress1=r_progress2-20;
                        }
                        else if(r_progress2-progress1>=40 && r_progress2<=100) {
                            start2X = x;
                            progress2 = r_progress2;
                            start1X = start2X - (2*diff);
                            progress1 = progress2 - 40;

                        }
                        else if(r_progress2<=100 && r_progress2>=20)
                        {
                            start2X=x;
                            progress2=r_progress2;
                        }
                    }
                }else {
                    if (abs(x - start1X) < abs(start2X - x)) {
                        start1X = margin;
                        progress1 = 0;
                        start2X=start1X+(2*diff);
                        progress2=40;
                    }
                    else {
                        start2X = width-margin;
                        progress2 = 100;
                        start1X=start2X-(2*diff);
                        progress1=progress2-40;
                    }
                }






                invalidate();
                break;


            case(MotionEvent.ACTION_MOVE):

                if(x>margin && x<(width-margin)) {
                    if (abs(x - start1X) < abs(start2X - x)) {

                        int r_progress1 = (int) (((x - margin) / line_width) * 100);


                        if(progress2-r_progress1<=20 && r_progress1<=80)
                        {start1X = x;
                        start2X=x+diff;
                            progress1=r_progress1;
                            progress2=r_progress1+20;
                        }else if(progress2-r_progress1>=40 && r_progress1>=0) {
                            start1X = x;
                            progress1 = r_progress1;
                            start2X = start1X + (2*diff);
                            progress2 = progress1 + 40;
                        }else if(r_progress1>=0 && r_progress1<=80)
                        {
                            start1X=x;
                            progress1=r_progress1;
                        }



                    } else {

                        int r_progress2 = (int) (((x - margin) / line_width) * 100);
                        if(r_progress2-progress1<=20 && r_progress2>=20)
                        {start2X = x;
                        start1X=x-diff;
                            progress2=r_progress2;
                        progress1=r_progress2-20;
                        }
                        else if(r_progress2-progress1>=40 && r_progress2<=100) {
                            start2X = x;
                            progress2 = r_progress2;
                            start1X = start2X - (2*diff);
                            progress1 = progress2 - 40;

                        }
                        else if(r_progress2<=100 && r_progress2>=20)
                        {
                            start2X=x;
                            progress2=r_progress2;
                        }
                    }
                }else {
                    if (abs(x - start1X) < abs(start2X - x)) {
                        start1X = margin;
                        progress1 = 0;
                        start2X=start1X+(2*diff);
                        progress2=40;
                    }
                    else {
                        start2X = width-margin;
                        progress2 = 100;
                        start1X=start2X-(2*diff);
                        progress1=progress2-40;
                    }
                }







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

