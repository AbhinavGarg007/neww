package com.example.emp354.linear.ThirdParty;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.emp354.linear.R;

public class ThirdPartyAdapter extends FragmentPagerAdapter{

    private Context mcontext;

    public ThirdPartyAdapter(Context context,FragmentManager fm) {
        super(fm);
        mcontext=context;
    }

    @Override
    public Fragment getItem(int position) {
        ThirdPartyFragment thirdPartyFragment=new ThirdPartyFragment();
        return thirdPartyFragment;

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mcontext.getString(R.string.monuments);

            case 1:
                return mcontext.getString(R.string.flowers);

            case 2:
                return mcontext.getString(R.string.landscapes);

            case 3:
                return mcontext.getString(R.string.waterfalls);

            case 4:
                return mcontext.getString(R.string.foods);

            default:
                return mcontext.getString(R.string.monuments);
        }
    }
}
