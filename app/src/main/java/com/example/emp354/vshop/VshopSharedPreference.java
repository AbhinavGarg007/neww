package com.example.emp354.vshop;

import android.content.Context;
import android.content.SharedPreferences;

public class VshopSharedPreference {
    private static final String SHAREDPREF_NAME="User_Login_Details";
    private static final String USER_ID="UserId";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static VshopSharedPreference mInstance;

    private VshopSharedPreference(Context context)
    {
      mSharedPreferences=context.getSharedPreferences(SHAREDPREF_NAME,Context.MODE_PRIVATE);
      mEditor=mSharedPreferences.edit();
    }

    public static VshopSharedPreference getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new VshopSharedPreference(context);
        }
        return mInstance;
    }

    public void saveId(long id)
    {
        mEditor.putLong(USER_ID,id);
        mEditor.apply();
    }

    public long fetchid()
    {
        return mSharedPreferences.getLong(USER_ID,-1);
    }
    public void clearAllData()
    {
        mEditor.clear();
        mEditor.apply();
    }

}
