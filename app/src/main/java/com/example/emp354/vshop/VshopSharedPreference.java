package com.example.emp354.vshop;

import android.content.Context;
import android.content.SharedPreferences;

public class VshopSharedPreference {
    private static final String SHAREDPREF_NAME="User_Login_Details";
    private static final String USER_ID="UserId";
    private static final String NAME="name";
    private static final String GENDER="gender";
    private static final String DOB="dob";
    public static final String IMAGE="image";

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

    public void saveName(String name)
    {
        mEditor.putString(NAME,name);
        mEditor.apply();
    }

    public String fetchName()
    {
        return mSharedPreferences.getString(NAME,"");
    }

    public void saveGender(String gender)
    {
        mEditor.putString(GENDER,gender);
        mEditor.apply();
    }
    public String fetchGender()
    {
        return mSharedPreferences.getString(GENDER,"");
    }

    public void saveDOB(String dob)
    {
        mEditor.putString(DOB,dob);
        mEditor.apply();
    }
    public String fetchDOB(String dob)
    {
        return mSharedPreferences.getString(DOB,"");
    }
    public void saveImage(String image)
    {
        mEditor.putString(IMAGE,image);
        mEditor.apply();
    }
    public String fetchImage()
    {
        return mSharedPreferences.getString(IMAGE,"");
    }

    public void clearAllData()
    {
        mEditor.clear();
        mEditor.apply();
    }


    public void setPreferencesChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }
    public void unRegisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

}
