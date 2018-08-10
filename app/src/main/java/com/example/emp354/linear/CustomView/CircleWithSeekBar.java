package com.example.emp354.linear.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class CircleWithSeekBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_with_seek_bar);

        final TextView seek_textview=findViewById(R.id.seek_textview);
        final FrameLayout layout_circle=findViewById(R.id.layout_circle);
        SeekBar seekbar=findViewById(R.id.seekbar);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                float width=layout_circle.getWidth();
                float height=layout_circle.getHeight();

                if(width<=height) {

                    float half_width = (width / 2);
                    float radius = (half_width / 100) * progress;
                    Circle circle_view = findViewById(R.id.circle_view);
                    circle_view.setRadius(radius);
                    seek_textview.setText(String.valueOf(progress));
                    circle_view.invalidate();
                }
                else
                {
                    float half_height= height/2;
                    float radius = (half_height / 100) * progress;
                    Circle circle_view = findViewById(R.id.circle_view);
                    circle_view.setRadius(radius);
                    seek_textview.setText(String.valueOf(progress));
                    circle_view.invalidate();
                }

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
