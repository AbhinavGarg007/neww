package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Candle extends View {

    Paint mPaint;
    private int topHeight;
    /*CandleViewActivity candleView=new CandleViewActivity();*/


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
    {
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

    }



    @Override
    protected void onDraw(Canvas canvas) {

        int top=getTopHeight();
        int height=getHeight();
        int width=getWidth();
        int left=(int)(0.3*width);
        int right=(width-left);



        canvas.drawRect(left,top,right,height,mPaint);
       /*canvas.drawRect(candleView.myRect,mPaint);*/
    }
    public int getTopHeight() {
        return topHeight;
    }

    public void setTopHeight(int topHeight) {
        this.topHeight = topHeight;
    }


}
