package com.s341872;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PreferencesActivity extends AppCompatActivity {

    private static int totalQuestions = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Button norwegianLangBtn = findViewById(R.id.btn_norwegian_language);
        Button germanLangBtn = findViewById(R.id.btn_german_language);

        norwegianLangBtn.setOnClickListener(view -> changeAppLanguage("no"));
        germanLangBtn.setOnClickListener(view -> changeAppLanguage("de"));

        final TextView totalQuestionsView = findViewById(R.id.view_total_questions);
        totalQuestionsView.setText(totalQuestions + "");

        Button totalQuestionsBtn = findViewById(R.id.btn_total_questions);
        totalQuestionsBtn.setOnClickListener(view -> updateTotalQuestions());
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

    private void updateTotalQuestions(){
        if (totalQuestions < 15){
            totalQuestions += 5;
        } else {
            totalQuestions = 5;
        }

        final TextView totalQuestionsView = findViewById(R.id.view_total_questions);
        totalQuestionsView.setText(totalQuestions + "");
    }

    public static int getTotalQuestions(){
        return totalQuestions;
    }
}