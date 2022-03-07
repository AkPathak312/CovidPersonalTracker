package com.example.covidpersonaltracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    public static void saveSharedPreference(Activity activity,String name, String key, String value){
        SharedPreferences.Editor editor=activity.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static  String getSharedPreferences(Activity activity,String name, String key){
        SharedPreferences preferences=activity.getSharedPreferences(name,Context.MODE_PRIVATE);
        String value = preferences.getString(key,"");
        return value;
    }

}
