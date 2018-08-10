package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Speedometer extends View {

    Paint mLine,mArc,mInnerArc,mPointer;

    private int progress;
    /*private float topHeight;*/

    public Speedometer(Context context) {
        super(context);
        init();
    }

    public Speedometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Speedometer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        mLine=new Paint();
        mLine.setStyle(Paint.Style.STROKE);
        mLine.setStrokeWidth(10.0f);
        mLine.setColor(Color.BLACK);

        mArc=new Paint();
        mArc.setStyle(Paint.Style.FILL);
        mArc.setColor(Color.GRAY);

        mInnerArc=new Paint();
        mInnerArc.setStyle(Paint.Style.FILL);
        mInnerArc.setColor(Color.BLUE);

        mPointer=new Paint();
        mPointer.setStyle(Paint.Style.STROKE);

        mPointer.setStrokeWidth(5.0f);
        mPointer.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float height=getHeight();
        float width=getWidth();
        float top=height/2-width/2;
       /*float top=getTopHeight();*/
        float startAngle=180;
        float sweepAngle=180;
        float radius=width/2;

        float half_width=width/2;
        float half_height=height/2;

        int progress=getProgress();
        if(progress>175)
        {
            mPointer.setColor(Color.RED);
        }
        else
        {
            mPointer.setColor(Color.GREEN);
        }


        /*float angle = (float) ((startAngle + sweepAngle / 2) * Math.PI / 180);*/
        float angle=(float)((startAngle +(progress * (sweepAngle/220)))*Math.PI/180);
        float stopX = (float) (half_width + radius * Math.cos( angle));
        float stopY = (float) (half_height + radius * Math.sin(angle));



        canvas.drawLine(0.0f,height/2,width,height/2,mLine);
        canvas.drawArc(0.0f,top,width,height-top,startAngle,sweepAngle,true,mLine);
        canvas.drawArc(0.0f,top,width,height-top,startAngle,sweepAngle,true,mArc);
        /*mArc.setColor(Color.BLUE);*/

        canvas.drawLine(width/2,height/2,stopX,stopY,mPointer);
        canvas.drawArc((width/2)-50,(height/2)-50,(width/2)+50,(height/2)+50,180,180,true,mInnerArc);


    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


   /* public float getTopHeight() {
        return topHeight;
    }

    public void setTopHeight(float topHeight) {
        this.topHeight = topHeight;
    }*/
}
