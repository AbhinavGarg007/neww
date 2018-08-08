package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.w3c.dom.Text;

public class CustomTextWithLine extends View {

    private Paint mOuterRect,mInnerRect,mLine,mText;
   /* private Path path;*/

    public CustomTextWithLine(Context context) {
        super(context);
        init();
    }

    public CustomTextWithLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextWithLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mOuterRect=new Paint();
        mOuterRect.setStyle(Paint.Style.STROKE);
        mOuterRect.setColor(Color.BLUE);
        mOuterRect.setStrokeWidth(10.0f);

        mInnerRect=new Paint();
        mInnerRect.setColor(Color.GREEN);
        mInnerRect.setStyle(Paint.Style.FILL);

        mLine=new Paint();
        mLine.setStyle(Paint.Style.STROKE);
        mLine.setStrokeWidth(10.0f);
        mLine.setColor(Color.BLUE);

        mText=new Paint();
        mText.setColor(Color.BLACK);
        mText.setTextSize(80.0f);

        /*path=new Path();
        path.addRect(220.0f,260.0f,550.0f,750.0f, Path.Direction.CW);*/


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(10.f,50.0f,760.0f,960.0f,mOuterRect);
        canvas.drawRect(210.f,250.0f,560.0f,760.0f,mOuterRect);
        canvas.drawRect(220.0f,260.0f,550.0f,750.0f,mInnerRect);
        canvas.drawLine(10.f,50.0f,210.0f,250.0f,mLine);
        canvas.drawLine(10.f,960.0f,210.0f,760.0f,mLine);
        canvas.drawLine(760.f,50.0f,560.0f,250.0f,mLine);
        canvas.drawLine(760.f,960.0f,560.0f,760.0f,mLine);

       /* canvas.drawTextOnPath("This is a custom view",path,10.0f,10.0f,mText);*/
        canvas.drawText("This is a",230.0f,370.0f,mText);
        canvas.drawText(" Custom ",230.0f,470.0f,mText);
        canvas.drawText("   View  ",230.0f,570.0f,mText);

    }
}
