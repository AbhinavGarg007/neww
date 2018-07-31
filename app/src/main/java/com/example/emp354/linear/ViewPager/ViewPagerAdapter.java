package com.example.emp354.linear.ViewPager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.emp354.linear.R;

import java.util.concurrent.Callable;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context mcontext;

    public ViewPagerAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        mcontext=context;
    }
    @Override
    public Fragment getItem(int position) {
        View v;
        switch (position)
        {
            case 0:v= new ViewPagerFragment().getView();
                   v.findViewById(R.id.textview_bookmark).setVisibility(View.GONE);
                   return v;

            case 1:return new ViewPagerFragment();

            case 2:return new ViewPagerFragment();

            default:return new ViewPagerFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position)
        {
            case 0: return mcontext.getString(R.string.my_course);

            case 1: return mcontext.getString(R.string.bookmarked);

            case 2: return mcontext.getString(R.string.nearby);

            default: return mcontext.getString(R.string.my_course);
        }
    }
}
