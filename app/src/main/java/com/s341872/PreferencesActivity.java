package com.s341872;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;

import java.util.Locale;

public class PreferencesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Button norwegianLangBtn = findViewById(R.id.btn_norwegian_language);
        Button germanLangBtn = findViewById(R.id.btn_german_language);

        norwegianLangBtn.setOnClickListener(view -> changeAppLanguage("no"));
        germanLangBtn.setOnClickListener(view -> changeAppLanguage("de"));
    }


    private void changeAppLanguage(String lang) {
        Locale locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.locale = locale;
        res.updateConfiguration(cf, dm);
        Intent refresh = new Intent(this, PreferencesActivity.class);
        finish();
        startActivity(refresh);
    }
}