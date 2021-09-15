package com.s341872;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Utils {

    public static String getSharedPrefString(Context context, String key, int id) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String defLang = context.getResources().getString(id);
        return sp.getString(key, defLang);
    }
}
