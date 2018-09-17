package com.example.emp354.linear.ThirdParty.Assignment_1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.Assignment_1.ThirdPartyFragment;
import com.example.emp354.linear.ThirdParty.POJO.Images;
import com.example.emp354.linear.ThirdParty.POJO.ObjectPojoThirdParty;

import java.util.ArrayList;
import java.util.List;

public class ThirdPartyViewPagerAdapter extends FragmentPagerAdapter{

    private Context mcontext;
    List<Images> imagesList;

    public ThirdPartyViewPagerAdapter(Context context, FragmentManager fm, ObjectPojoThirdParty objectPojoThirdParty) {
        super(fm);
        mcontext=context;
       if(objectPojoThirdParty!=null)
       {
           imagesList=objectPojoThirdParty.getImages();
       }
    }

    @Override
    public Fragment getItem(int position) {

        String text;
        List<String> image=new ArrayList<String>();
        Images images;
        int size;

        String[] image_array=new String[10];

        switch (position)
        {
            case 0:
                images=imagesList.get(position);
               /* text=images.getName();*/
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                  image_array[i]=image.get(i) ;
                }

                break;

            case 1:
                images=imagesList.get(position);
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                    image_array[i]=image.get(i) ;
                }
                break;

            case 2:
                images=imagesList.get(position);
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                    image_array[i]=image.get(i) ;
                }
                break;

            case 3:
                images=imagesList.get(position);
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                    image_array[i]=image.get(i) ;
                }
                break;

            case 4:
                images=imagesList.get(position);
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                    image_array[i]=image.get(i) ;
                }
                break;

            default:
                images=imagesList.get(position);
                image=images.getValues();
                size=image.size();
                for(int i=0;i<size;i++)
                {
                    image_array[i]=image.get(i) ;
                }
                break;

        }

        ThirdPartyFragment thirdPartyFragment=new ThirdPartyFragment();
        Bundle b=new Bundle();
        b.putStringArray("send_images",image_array);

        thirdPartyFragment.setArguments(b);

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
