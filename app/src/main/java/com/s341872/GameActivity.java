package com.s341872;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class GameActivity extends AppCompatActivity implements CancelGameDialogFragment.CancelGameDialogListener, GameEndDialogFragment.GameEndDialogListener {

    private int totalQuestions;
    private QuestionArray questionArray = new QuestionArray();
    private int currentQuestion = 0;
    private int score;
    private TextView gameProgressText;
    private TextView questionText;
    private TextView answerText;
    private static String finalScore;

    @Override
    public void onBackPressed() {
        CancelGameDialogFragment endGameDialog = new CancelGameDialogFragment();
        endGameDialog.show(getSupportFragmentManager(), "endGame");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        totalQuestions = Integer.parseInt(Utils.getSharedPrefString(getApplicationContext(), "questions", R.string.questions_default));
        Log.d("questions", String.valueOf(totalQuestions));
        questionText = findViewById(R.id.txt_question);
        answerText = findViewById(R.id.txt_answer);
        gameProgressText = findViewById(R.id.txt_game_progress);

        final Button numPadBtn1 = findViewById(R.id.button_game_one);
        final Button numPadBtn2 = findViewById(R.id.button_game_two);
        final Button numPadBtn3 = findViewById(R.id.button_game_three);
        final Button numPadBtn4 = findViewById(R.id.button_game_four);
        final Button numPadBtn5 = findViewById(R.id.button_game_five);
        final Button numPadBtn6 = findViewById(R.id.button_game_six);
        final Button numPadBtn7 = findViewById(R.id.button_game_seven);
        final Button numPadBtn8 = findViewById(R.id.button_game_eight);
        final Button numPadBtn9 = findViewById(R.id.button_game_nine);
        final Button numPadBtn0 = findViewById(R.id.button_game_zero);

        final ImageButton numPadBtnDelete = findViewById(R.id.button_game_delete);
        final ImageButton submitAnswerBtn = findViewById(R.id.button_submit_answer);

        if (savedInstanceState == null) {
            questionArray.seedArray(getResources(), totalQuestions);
            Log.d("GAME", questionArray.toString());
            questionText.setText(questionArray.getQuestion(currentQuestion));
            gameProgressText.setText(getGameProgressString());
        }

        //Event handlers for buttons
        numPadBtn0.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "0")));

        numPadBtn1.setOnClickListener(view ->
                answerText.setText(updateAnswerText(answerText, "1")));

        numPadBtn2.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "2")));

        numPadBtn3.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "3")));

        numPadBtn4.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "4")));

        numPadBtn5.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "5")));

        numPadBtn6.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "6")));

        numPadBtn7.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "7")));

        numPadBtn8.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "8")));

        numPadBtn9.setOnClickListener(view -> answerText.setText(updateAnswerText(answerText, "9")));


        numPadBtnDelete.setOnClickListener(view -> {
            String oldText = String.valueOf(answerText.getText());
            if (oldText.length() > 0)
                answerText.setText(oldText.substring(0, oldText.length() - 1));
        });

        submitAnswerBtn.setOnClickListener(this::evaluateAnswer);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        questionArray = new QuestionArray();
        questionArray.addAll((QuestionArray) savedInstanceState.getSerializable("questions"));

        currentQuestion = savedInstanceState.getInt("currentQuestion");
        score = savedInstanceState.getInt("score");

        gameProgressText.setText(getGameProgressString());
        questionText.setText(questionArray.getQuestion(currentQuestion));
        answerText.setText(savedInstanceState.getCharSequence("currentAnswer"));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("questions", questionArray);
        outState.putInt("score", score);
        outState.putInt("currentQuestion", currentQuestion);
        outState.putCharSequence("currentAnswer", answerText.getText());
    }

    //TODO: make a better answer-feedback functioniality
    private void evaluateAnswer(View view) {
        if (answerText.getText().length() > 0) {
            if (answerText.getText().equals(questionArray.getAnswer(currentQuestion))) {
                score++;
                Toast.makeText(getApplicationContext(), ":)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), ":(", Toast.LENGTH_SHORT).show();
            }
            answerText.setText("");
            gameProgressText.setText(getGameProgressString());
            nextQuestion(questionText);
        } else {
            Log.d("Answer", "Answer not submitted");
        }
    }

    // Updates screen when numpad-buttons is pressed.
    // Limits maximum input to 4 digits
    private String updateAnswerText(TextView answer, String newInput) {
        String oldText = String.valueOf(answer.getText());
        if (oldText.length() < 4) {
            return oldText + newInput;
        }
        return oldText;
    }

    private String getGameProgressString() {
        int actualCount = currentQuestion + 1;
        return actualCount + "/" + totalQuestions;
    }

    private void nextQuestion(TextView questionTxt) {
        if (currentQuestion < questionArray.size() - 1) {
            currentQuestion++;

            questionTxt.setText(questionArray.getQuestion(currentQuestion));

        } else {
            finalScore = String.format("%s/%s", score, totalQuestions);
            Toast.makeText(getApplicationContext(), "Din score: "+finalScore, Toast.LENGTH_LONG).show();
            //questionTxt.setText(String.format("Score:%s/%s", score, this.totalQuestions));

            // Storing string set in SharedPreferences
            SharedPreferences statsSharedPrefs = getSharedPreferences("statistics", MODE_PRIVATE);
            //statsSharedPrefs.edit().clear().commit();

            Set<String> statistics = statsSharedPrefs.getStringSet("stats", null);

            if (statistics != null) {
                HashSet<String> statistics2 = new HashSet<String>(statistics);
                statistics2.add(finalScore + "   "
                        + new SimpleDateFormat("dd/MMM/yyyy 'at' HH:mm").format(new Date()));
                statsSharedPrefs.edit().putStringSet("stats", statistics2).apply();
            }
            else {
                statistics = new HashSet<String>();
                statistics.add(finalScore + "   "
                        + new SimpleDateFormat("dd/MMM/yyyy 'at' HH:mm").format(new Date()));
                statsSharedPrefs.edit().putStringSet("stats", statistics).apply();
            }
            GameEndDialogFragment gameEndDialog = new GameEndDialogFragment();
            gameEndDialog.show(getSupportFragmentManager(), "game over");
        }
    }


    public static String getFinalScore(){return finalScore;}

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
        Toast.makeText(getApplicationContext(), "THE SHOW MUST GO ON", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHomeClick(DialogFragment dialog) {
        dialog.dismiss();
        finish();
    }
}