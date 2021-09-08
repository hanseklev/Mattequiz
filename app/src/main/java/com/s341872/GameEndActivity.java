package com.s341872;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        final ImageButton mainMenuBtn = findViewById(R.id.button_main_menu);
        final ImageButton showStatsBtn = (ImageButton) findViewById(R.id.button_stats_2);

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        showStatsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        });

        // Retrieving final score from GameActivity.java
        String fs = getIntent().getStringExtra("finalScore");

        // Displaying final score
        final TextView resultText = findViewById(R.id.txt_final_score);
        resultText.setText("Score: " + fs);

        // Storing string set in SharedPreferences
        SharedPreferences statsSharedPrefs = getSharedPreferences("statistics", MODE_PRIVATE);
        //statsSharedPrefs.edit().clear().commit();

        Set<String> statistics = statsSharedPrefs.getStringSet("stats", null);

        if (statistics != null) {
            HashSet<String> statistics2 = new HashSet<String>(statistics);
            statistics2.add(fs + " "
                    + new SimpleDateFormat("dd/MMM/yyyy 'at' HH:mm").format(new Date()));
            statsSharedPrefs.edit().putStringSet("stats", statistics2).apply();
        }
        else {
            statistics = new HashSet<String>();
            statistics.add(fs + " "
                    + new SimpleDateFormat("dd/MMM/yyyy 'at' HH:mm").format(new Date()));
            statsSharedPrefs.edit().putStringSet("stats", statistics).apply();
        }

    }
}