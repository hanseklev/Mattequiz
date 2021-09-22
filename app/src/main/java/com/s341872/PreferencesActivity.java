package com.s341872;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class PreferencesActivity extends AppCompatActivity implements ConfirmationDialogFragment.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences_container, new PreferencesFragment())
                .commit();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        getApplicationContext().getSharedPreferences(Utils.Constants.STATS_KEY, MODE_PRIVATE).edit().clear().apply();
        Utils.getSharedPref(getApplicationContext(), Utils.Constants.LANG_KEY).edit().clear().apply();
        Utils.getSharedPref(getApplicationContext(), Utils.Constants.QUESTIONS_KEY).edit().clear().apply();
        Utils.setAppLanguage(getResources(), Utils.Constants.DEFAULT_LANG_CODE);

        dialog.dismiss();
        String resetStatsSuccessText = getResources().getString(R.string.reset_stats_success);
        Utils.showToast(getApplicationContext(), resetStatsSuccessText, 1000);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        System.out.println("LA VÆRE");
        dialog.dismiss();
    }
}