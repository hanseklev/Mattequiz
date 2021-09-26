package com.s341872.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;

import com.s341872.ui.game.GameActivity;
import com.s341872.ui.preferences.PreferencesActivity;
import com.s341872.R;
import com.s341872.ui.stats.StatsActivity;
import com.s341872.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton startGameBtn = findViewById(R.id.button_start_game);
        final ImageButton showStatsBtn = findViewById(R.id.button_stats);
        final ImageButton showPreferences = findViewById(R.id.button_preferences);

        //Loads the saved language on start up
        Utils.setAppLanguage(getResources(), Utils.getSharedPrefString(getApplicationContext(),
                Utils.Constants.LANG_KEY, R.string.language_default));

        startGameBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        showStatsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        });

        showPreferences.setOnClickListener(view -> {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        });

    }
}