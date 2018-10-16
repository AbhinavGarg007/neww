package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.R;

import static com.example.emp354.vshop.constants.Constant.BRANDS;
import static com.example.emp354.vshop.constants.Constant.PRICE;
import static com.example.emp354.vshop.constants.Constant.PRODUCTS;
import static com.example.emp354.vshop.constants.Constant.TITLE;

public class ViewPagerAdapter extends PagerAdapter {

    //declaring variables
    private Context mContext;
    private int[] images=BRANDS;
    View v;


    //constructor for the adapter
    public ViewPagerAdapter(Context context)
    {
        mContext=context;
    }

    //getting the number of items
    @Override
    public int getCount() {
        return PRODUCTS.length;
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

        //setting values to imageview and textview
        ImageView imageView=v.findViewById(R.id.iv_discover);
        imageView.setImageResource(PRODUCTS[position]);
        TextView tvTitle=v.findViewById(R.id.tv_title_discover_product);
        tvTitle.setText(TITLE[position]);
        TextView tvPrice=v.findViewById(R.id.tv_price_discover_product);
        tvPrice.setText(PRICE[position]);


        return v;
    }

    //removing the layout from container
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    //setting pagewidth to half of the screen
    @Override
    public float getPageWidth(int position) {
        return 0.5f;
    }
}
