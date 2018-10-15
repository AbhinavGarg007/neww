package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;

import static com.example.emp354.vshop.constants.Constant.BRANDS;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] images=BRANDS;
    View v;

    public ViewPagerAdapter(Context context)
    {
        mContext=context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        /*ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(images[position]);
        container.addView(imageView,0);
        return imageView;*/

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.layout_discover_item, null);
        container.addView(v,0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.5f;
    }
}
