package com.s341872;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import java.util.Set;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        final TextView textView = findViewById(R.id.textView);

        SharedPreferences statsSharedPrefs = getSharedPreferences("statistics", MODE_PRIVATE);

        Set<String> statistics = statsSharedPrefs.getStringSet("stats", null);
        if (statistics != null) {
            String stats = "";
            for (String stat : statistics) {
                stats += stat + "\n\n";
            }
            textView.setText(stats);
        }
        else {

        }

    }
}