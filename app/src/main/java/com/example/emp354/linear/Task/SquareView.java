package com.example.emp354.linear.Task;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.regex.Pattern;

public class SquareView extends View {

    Paint mOuterPaint,mInnerPaint;

    public SquareView(Context context) {
        super(context);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
     mOuterPaint=new Paint();
     mOuterPaint.setColor(Color.BLACK);
     mOuterPaint.setStyle(Paint.Style.FILL);

        mInnerPaint=new Paint();
        mInnerPaint.setColor(Color.YELLOW);
        mInnerPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float width=getWidth();
        float height=getHeight();
        float center_vertical=height/2;
        float required_width=width/3;
        float half_required_width=required_width/2;


        //either you can draw a rectangle or you can set the background to black color.
        canvas.drawRect(0.0f,0.0f,width,height,mOuterPaint);
        //Inner yellow color rectangle
        canvas.drawRect(required_width,center_vertical-half_required_width,width-required_width,center_vertical+half_required_width,mInnerPaint);
    }
}
