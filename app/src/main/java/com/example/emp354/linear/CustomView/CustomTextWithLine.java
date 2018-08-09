package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    float stroke_width=10.0f;
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
        mLine.setStrokeWidth(stroke_width);
        mLine.setColor(Color.BLUE);

        mText=new Paint();
        mText.setColor(Color.BLACK);
        mText.setTextSize(80.0f);

        /*path=new Path();
        path.addRect(220.0f,260.0f,550.0f,750.0f, Path.Direction.CW);*/
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }



    @Override
    protected void onDraw(Canvas canvas) {

        int height=getHeight();
        int width=getWidth();

        float outer_rect_left_topX=stroke_width,outer_rect_left_topY=stroke_width;
        float outer_rect_right_topX=width-stroke_width,outer_rect_right_topY=stroke_width;
        float outer_rect_left_bottomX=stroke_width,outer_rect_left_bottomY=height-stroke_width;
        float outer_rect_right_bottomX=width-stroke_width,outer_rect_right_bottomY=height-stroke_width;

        float height_inner_rect=(float)0.4*height;
        float width_inner_rect=(float)0.4*width;

        float inner_rect_left_topX=(width-width_inner_rect)/2;
        float inner_rect_left_topY=(height-height_inner_rect)/2;
        float inner_rect_right_topX=inner_rect_left_topX + width_inner_rect;
        float inner_rect_right_topY=(height-height_inner_rect)/2;
        float inner_rect_left_bottomX=(width-width_inner_rect)/2;
        float inner_rect_left_bottomY=inner_rect_left_topY+height_inner_rect;
        float inner_rect_right_bottomX=inner_rect_left_bottomX+width_inner_rect;
        float inner_rect_right_bottomY=inner_rect_right_topY+height_inner_rect;



        canvas.drawRect(outer_rect_left_topX,outer_rect_left_topY,outer_rect_right_bottomX,outer_rect_right_bottomY,mOuterRect);
        canvas.drawRect(inner_rect_left_topX,inner_rect_left_topY,inner_rect_right_bottomX,inner_rect_right_bottomY,mOuterRect);
        canvas.drawRect(inner_rect_left_topX+stroke_width,inner_rect_left_topY+stroke_width,inner_rect_right_bottomX-stroke_width,inner_rect_right_bottomY-stroke_width,mInnerRect);
        canvas.drawLine(outer_rect_left_topX,outer_rect_left_topY,inner_rect_left_topX,inner_rect_left_topY,mLine);
        canvas.drawLine(outer_rect_right_topX,outer_rect_right_topY,inner_rect_right_topX,inner_rect_right_topY,mLine);
        canvas.drawLine(outer_rect_left_bottomX,outer_rect_left_bottomY,inner_rect_left_bottomX,inner_rect_left_bottomY,mLine);
        canvas.drawLine(outer_rect_right_bottomX,outer_rect_right_bottomY,inner_rect_right_bottomX,inner_rect_right_bottomY,mLine);

        /*canvas.drawRect(10.f,50.0f,760.0f,960.0f,mOuterRect);
        canvas.drawRect(210.f,250.0f,560.0f,760.0f,mOuterRect);
        canvas.drawRect(220.0f,260.0f,550.0f,750.0f,mInnerRect);
        canvas.drawLine(10.f,50.0f,210.0f,250.0f,mLine);
        canvas.drawLine(10.f,960.0f,210.0f,760.0f,mLine);
        canvas.drawLine(760.f,50.0f,560.0f,250.0f,mLine);
        canvas.drawLine(760.f,960.0f,560.0f,760.0f,mLine);

       *//* canvas.drawTextOnPath("This is a custom view",path,10.0f,10.0f,mText);*//*
        canvas.drawText("This is a",230.0f,370.0f,mText);
        canvas.drawText(" Custom ",230.0f,470.0f,mText);
        canvas.drawText("   View  ",230.0f,570.0f,mText);
*/
    }
}
