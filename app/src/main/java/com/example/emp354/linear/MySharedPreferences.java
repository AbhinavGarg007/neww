package com.example.emp354.linear;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static final String SHAREDPREF_NAME="User_Login_Details";
    private static final String USER_ID="UserId";
    private static final String MAIL_ID="MailId";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static MySharedPreferences sInstance;

    private MySharedPreferences(Context context) {
        mPreferences = context.getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static MySharedPreferences getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MySharedPreferences(context);
        }
        return sInstance;
    }



    public void saveId(long id)
    {
        mEditor.putLong(USER_ID,id);
        mEditor.apply();
    }



    public long fetchId()
    {

        return mPreferences.getLong(USER_ID,-1);

    }

    /*public void saveMailId(String mailId)
    {
        mEditor.putString(MAIL_ID,mailId);
        mEditor.apply();
    }

    public String fetchMailId()
    {
        return mPreferences.getString(MAIL_ID,"");
    }

    public void clearAllData()
    {
        mEditor.clear();
        mEditor.apply();
    }*/

}

