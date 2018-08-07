package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewRectangleStroke extends View {

    private Paint mRectangle;

    public CustomViewRectangleStroke(Context context) {
        super(context);
        init();
    }

    public CustomViewRectangleStroke(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewRectangleStroke(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mRectangle=new Paint();
        mRectangle.setColor(Color.BLACK);
        mRectangle.setStyle(Paint.Style.STROKE);
        mRectangle.setStrokeWidth(10.0f);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(50.0f,50.0f,350.0f,450.0f,mRectangle);
        canvas.drawLine(50.0f,50.0f,350.0f,450.0f,mRectangle);
        canvas.drawLine(350.0f,50.0f,50.0f,450.0f,mRectangle);
    }
}
