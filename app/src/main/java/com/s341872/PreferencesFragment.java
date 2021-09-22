package com.s341872;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.s341872.Utils.Constants;


public class PreferencesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        DropDownPreference languagePreference = findPreference(Constants.LANG_KEY);
        DropDownPreference questions = findPreference(Constants.QUESTIONS_KEY);
        Preference resetStatsBtn = findPreference(Constants.RESET_STATS_KEY);

        String langValue = Utils.getSharedPrefString(requireContext(), Constants.LANG_KEY, R.string.language_default);
        String questionValue = Utils.getSharedPrefString(requireContext(), Constants.QUESTIONS_KEY, R.string.questions_default);

        if (languagePreference != null) {
            String langEntry = getEntryFromValue(languagePreference, langValue);
            languagePreference.setSummary(langEntry);
        }

        if (questions != null)
            questions.setSummary(String.valueOf(questionValue));

        if (resetStatsBtn != null) {
            resetStatsBtn.setOnPreferenceClickListener(preference -> removeAllStats());
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);

        if (preference instanceof DropDownPreference) {
            DropDownPreference dropPref = (DropDownPreference) preference;
            String newValue = dropPref.getValue();
            preference.setSummary(dropPref.getEntry());

            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (key.equals(Constants.LANG_KEY)) {
                editor.putString(key, newValue);
                Utils.setAppLanguage(getResources(), newValue);
            }
            if (key.equals(Constants.QUESTIONS_KEY)) {
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

    public boolean removeAllStats() {
        getPreferenceManager().getSharedPreferences().edit().clear().apply();
        String removeStatsText = getResources().getString(R.string.reset_stats_summary) + "?";
        ConfirmationDialogFragment dialog = new ConfirmationDialogFragment(removeStatsText);
        dialog.show(getParentFragmentManager(), "deleteStats");

        return true;
    }
}
