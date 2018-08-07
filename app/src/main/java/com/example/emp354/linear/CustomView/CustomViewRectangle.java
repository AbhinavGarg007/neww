package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewRectangle extends View{
    private Paint mpaint;

    public CustomViewRectangle(Context context) {
        super(context);
        init();
    }

    public CustomViewRectangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewRectangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    private void init()
    {
        mpaint=new Paint();
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(50.0f,50.0f,350.0f,450.0f,mpaint);

    }
}
