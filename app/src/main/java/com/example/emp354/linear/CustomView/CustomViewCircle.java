package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewCircle extends View{

    private Paint mPaint ;

    public CustomViewCircle(Context context) {
        super(context);
        init();

    }

    public CustomViewCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CustomViewCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {
        mPaint= new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2.0f,getHeight()/2.0f,150.0f,mPaint);
    }
}
