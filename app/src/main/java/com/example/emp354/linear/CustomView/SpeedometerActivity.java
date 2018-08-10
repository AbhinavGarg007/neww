package com.example.emp354.linear.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class SpeedometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speedometer);

        final TextView seek_textview=findViewById(R.id.seek_textview);
        SeekBar seekbar=findViewById(R.id.seekbar);
        final FrameLayout layout_speedometer=findViewById(R.id.layout_speedometer);
        final Speedometer speedometer=findViewById(R.id.speedometer_view);

        final float width=layout_speedometer.getWidth();
        final float height=layout_speedometer.getHeight();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               /* float startAngle=180;
                float sweepAngle=180;
                float angle=(float) (((startAngle + sweepAngle / 2) * Math.PI / 180)/220);*/
             /*if(width>=height) {
                 speedometer.setTopHeight(width / 2 - height / 2);
             }
                 else
                 {
                  speedometer.setTopHeight(height/2-width/2);
                 }*/


               speedometer.setProgress(progress);

               seek_textview.setText(String.valueOf(progress));
               speedometer.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
