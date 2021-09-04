package com.s341872;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        final ImageButton mainMenuBtn = (ImageButton) findViewById(R.id.button_main_menu);
        final ImageButton showStatsBtn = (ImageButton) findViewById(R.id.button_stats_2);

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        showStatsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        });

        final TextView resultText = findViewById(R.id.txt_final_score);
        resultText.setText(GameActivity.getFinalScore());
    }
}