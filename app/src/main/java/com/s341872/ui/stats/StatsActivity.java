package com.s341872.ui.stats;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.s341872.R;
import com.s341872.utils.Utils;

import java.util.Set;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        final TextView textView = findViewById(R.id.textView);

        SharedPreferences statsSharedPrefs = getSharedPreferences(Utils.Constants.STATS_KEY, MODE_PRIVATE);

        Set<String> statistics = statsSharedPrefs.getStringSet("stats", null);
        if (statistics != null) {
            StringBuilder stats = new StringBuilder();
            for (String stat : statistics) {
                stats.append(stat).append("\n\n");
            }
            textView.setText(stats.toString());
        }
    }
}