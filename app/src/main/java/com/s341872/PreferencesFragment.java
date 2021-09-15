package com.s341872;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Locale;


public class PreferencesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    public static final String LANG_KEY = "language";
    private static final String QUESTIONS_KEY = "questions";
    //private static final String RESET_STATS_KEY = "reset_stats";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        DropDownPreference languagePreference = findPreference(LANG_KEY);
        DropDownPreference questions = findPreference(QUESTIONS_KEY);

        String langValue = Utils.getSharedPrefString(requireContext(), LANG_KEY, R.string.language_default);
        String questionValue = Utils.getSharedPrefString(requireContext(), QUESTIONS_KEY, R.string.questions_default);

        if (languagePreference != null) {
            String langEntry = getEntryFromValue(languagePreference, langValue);
            languagePreference.setSummary(langEntry);
        }

        if (questions != null)
            questions.setSummary(String.valueOf(questionValue));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);

        if (preference instanceof DropDownPreference) {
            DropDownPreference dropPref = (DropDownPreference) preference;
            String newValue = dropPref.getValue();
            preference.setSummary(dropPref.getEntry());

            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (key.equals(LANG_KEY)) {
                editor.putString(key, newValue);
                changeAppLanguage(newValue);
            }
            if (key.equals(QUESTIONS_KEY)) {
                editor.putString(key, newValue);
            }

            editor.apply();

        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    private String getEntryFromValue(DropDownPreference pref, String value) {
        CharSequence[] langValues = pref.getEntryValues();
        CharSequence[] langEntries = pref.getEntries();

        for (int i = 0; i < langValues.length; i++) {
            if (langValues[i].equals(value))
                return langEntries[i].toString();
        }
        return "-1";
    }

    //TODO: maybe restart activity onchange to get instant change
    private void changeAppLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.locale = locale;
        res.updateConfiguration(cf, dm);
        /*
            Intent refresh = new Intent(this, PreferencesActivity.class);
            finish();
            startActivity(refresh);
        */
    }
}