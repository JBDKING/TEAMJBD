package com.example.team_jbd;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class activity_Data
{
    //데이터들 저장해놓는 DB 함수
    private final static String PREF_NAME = "pref_sharedpreferences_data";
    private static final String NAME_KEY = "NAME_KEY";

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    private static Context mContext;

    private static activity_Data mInstance;
    public synchronized static activity_Data getInstance(Context ctx)
    {
        mContext = ctx;
        if (mInstance == null)
        {
            mInstance = new activity_Data();
            mSharedPreferences = ctx.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
        }
        return mInstance;
    }

    public void setName(String flag)
    {
        mEditor.putString(NAME_KEY, flag);
        mEditor.commit();
    }

    public String getName()
    {
        return mSharedPreferences.getString(NAME_KEY, "");
    }
}