package com.s341872;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static class Constants {
        public static final String LANG_KEY = "language";
        public static final String QUESTIONS_KEY = "questions";
        public static final String RESET_STATS_KEY = "reset_stats";
        public static final String STATS_KEY = "statistics";
        public static final String DEFAULT_LANG_CODE = "no";
    }

    /**
     * @param context getApplicationContext()
     * @param key     the sharedPref key
     * @param id      pointer to default resource string
     * @return String from the specified key
     */
    public static String getSharedPrefString(Context context, String key, int id) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String defLang = context.getResources().getString(id);
        return sp.getString(key, defLang);
    }

    public static SharedPreferences getSharedPref(Context context, String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("dd/MMM/yyyy 'at' HH:mm", Locale.ITALY).format(new Date());
    }

    public static void setAppLanguage(Resources res, String langCode) {
        Locale locale = new Locale(langCode);
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.locale = locale;
        res.updateConfiguration(cf, dm);
    }


    public static void showToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 15);
        toast.show();

        //Customizes the duration of the toast
        Handler handler = new Handler();
        handler.postDelayed(toast::cancel, duration);
    }
}
