package com.s341872;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startGameBtn = (Button) findViewById(R.id.button_start_game);
        final Button showStatsBtn = (Button) findViewById(R.id.button_stats);
        final Button showPreferences = (Button) findViewById(R.id.button_preferences);

        startGameBtn.setOnClickListener(view -> {
            System.out.println("Game is starting");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        showStatsBtn.setOnClickListener(view -> {
            System.out.println("Showing stats..");
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        });

        showPreferences.setOnClickListener(view -> {
            System.out.println("This is the preferences");
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        });

    }
}