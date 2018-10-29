package com.example.emp354.linear.StartingActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.R;

public class IntroAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] screens={R.layout.layout_screen1,R.layout.layout_screen2,R.layout.layout_screen3,R.layout.layout_screen4,R.layout.layout_screen5};


    public IntroAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return screens.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    // Returns the layout at the given position of the view pager

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(screens[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view=(View)object ;
        container.removeView(view);
    }
}
