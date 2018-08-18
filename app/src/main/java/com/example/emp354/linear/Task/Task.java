package com.example.emp354.linear.Task;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emp354.linear.R;

public class Task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

       /* LinearLayout inner_rectangle=findViewById(R.id.inner_rectangle);
        LinearLayout outer_rectangle=findViewById(R.id.outer_rectangle);

        ViewGroup.LayoutParams params=inner_rectangle.getLayoutParams();

        Display display=getWindowManager().getDefaultDisplay();




        int width=display.getWidth();
        int height=display.getHeight();

        params.height=(int)width/3;
        params.width=(int)width/3;

        inner_rectangle.setLayoutParams(params);
*/
    }

}
