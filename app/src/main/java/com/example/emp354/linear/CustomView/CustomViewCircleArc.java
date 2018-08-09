package com.example.emp354.linear.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewCircleArc extends View{

    private Paint mArc1,mArc2,mArc3,mArc4,mArc5,mArc6;

    public CustomViewCircleArc(Context context) {
        super(context);
        init();
    }

    public CustomViewCircleArc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewCircleArc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   private void init()
   {
       mArc1=new Paint();
       mArc1.setColor(Color.BLUE);
       mArc1.setStyle(Paint.Style.FILL);

       mArc2=new Paint();
       mArc2.setColor(Color.RED);
       mArc2.setStyle(Paint.Style.FILL);

       mArc3=new Paint();
       mArc3.setColor(Color.MAGENTA);
       mArc3.setStyle(Paint.Style.FILL);

       mArc4=new Paint();
       mArc4.setColor(Color.BLUE);
       mArc4.setStyle(Paint.Style.FILL);

       mArc5=new Paint();
       mArc5.setColor(Color.YELLOW);
       mArc5.setStyle(Paint.Style.FILL);

       mArc6=new Paint();
       mArc6.setColor(Color.GRAY);
       mArc6.setStyle(Paint.Style.FILL);

   }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getWidth();
        int height=getHeight();

        int margin=50;
        canvas.drawArc(margin,margin,width-margin,height-margin,0,60,true,mArc1);
        canvas.drawArc(margin,margin,width-margin,height-margin,60,60,true,mArc2);
        canvas.drawArc(margin,margin,width-margin,height-margin,120,60,true,mArc3);
        canvas.drawArc(margin,margin,width-margin,height-margin,180,60,true,mArc4);
        canvas.drawArc(margin,margin,width-margin,height-margin,240,60,true,mArc5);
        canvas.drawArc(margin,margin,width-margin,height-margin,300,60,true,mArc6);




    }
}
