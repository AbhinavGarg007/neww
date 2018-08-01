package com.example.emp354.linear.ViewPager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
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
        View view;
        String[] data=mcontext.getResources().getStringArray(R.array.viewpager_title);
        String[] date=mcontext.getResources().getStringArray(R.array.date);
        switch (position)
        {
            case 0: return ViewPagerFragment.newInstance(data[position],"Finish",R.drawable.ic_delete_black_24dp,date);

            case 1:return ViewPagerFragment.newInstance(data[position],"Remove",R.drawable.ic_bookmark_black_24dp,date);

            case 2:return ViewPagerFragment.newInstance(data[position],"Bookmark",R.drawable.ic_bookmark_border_black_24dp,date);

            default:return ViewPagerFragment.newInstance(data[position],"Finish",R.drawable.ic_delete_black_24dp,date);

            /*view= new ViewPagerFragment().getView();
                   view.findViewById(R.id.textview_bookmark).setVisibility(View.GONE);
                   return ViewPagerFragment.*/
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
