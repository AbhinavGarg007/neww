package com.example.emp354.linear;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.emp354.linear.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.emp354.linear.PreferencesUtility.MAIL_ID;

public class SaveSharedPreference {
    static SharedPreferences getPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    //set login status
    public static void setLoggedIn(Context context,boolean loggedIn,String mailId)
    {

        SharedPreferences.Editor editor=getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF,loggedIn);
        editor.putString(MAIL_ID,mailId);
        editor.apply();
    }

    //get login status
    public static boolean getLoggedStatus(Context context)
    {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF,false);
    }
}
