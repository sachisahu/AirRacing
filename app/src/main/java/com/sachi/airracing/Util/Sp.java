package com.sachi.airracing.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Sp {
    public static void SaveShared(Activity activity,String name,String key,String value){
        SharedPreferences.Editor editor = activity.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getShared(Activity activity,String name,String key){
        SharedPreferences preferences = activity.getSharedPreferences(name,Context.MODE_PRIVATE);
        String value = preferences.getString(key,"");
        return value;
    }
    public static void clearSp(Activity activity,String name){
        SharedPreferences.Editor editor = activity.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}
