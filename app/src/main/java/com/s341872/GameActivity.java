package com.s341872;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private int totalQuestions = 5;
    private List<Question> questionArray;
    private int currentQuestion = 0;
    private int score;
    private static String finalScore;
    private final String gameProgressString = currentQuestion + "/" + totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView questionText = findViewById(R.id.txt_question);
        final TextView answerText = findViewById(R.id.txt_answer);
        final TextView gameProgressText = findViewById(R.id.txt_game_progress);

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

        final Button numPadBtnDelete = findViewById(R.id.button_game_delete);
        final Button submitAnswerBtn = findViewById(R.id.button_submit_answer);

        //Initializes question-array and writes the first question to the screen
        questionArray = initializeQuestions();
        Log.d("ARRAY", questionArray.toString());

        questionText.setText(questionArray.get(currentQuestion).getQuestion());
        gameProgressText.setText(gameProgressString);


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

        submitAnswerBtn.setOnClickListener(view -> {
            if (answerText.getText().length() > 0) {
                if (answerText.getText().equals(questionArray.get(currentQuestion).getAnswer())) {
                    score++;
                    System.out.println("Riktig svar!!");
                } else {
                    System.out.println("Feil svar:(Riktig svar er" + questionArray.get(currentQuestion).getAnswer());
                }
                answerText.setText("");
                gameProgressText.setText(updateProgressText());
                nextQuestion(questionText);
            } else {
                Log.d("Answer", "Answer not submitted");
            }
        });
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

    private String updateProgressText() {
        int actualCount = currentQuestion + 1;
        return actualCount + "/" + totalQuestions;
    }

    //Returns an array with randomized questions and desired length
    private List<Question> initializeQuestions() {
        List<Question> array = new ArrayList<>();
        TypedArray typedArrQuestions = getResources().obtainTypedArray(R.array.questions_container);

        for (int i = 0; i < typedArrQuestions.length(); i++) {
            int id = typedArrQuestions.getResourceId(i, 0);
            array.add(new Question(getResources().getStringArray(id)));
        }
        typedArrQuestions.recycle();

        Collections.shuffle(array);

        totalQuestions = PreferencesActivity.getTotalQuestions();

        return array.subList(0, totalQuestions);
    }


    private void nextQuestion(TextView questionTxt) {
        if (currentQuestion < questionArray.size() - 1) {
            currentQuestion++;

            questionTxt.setText(questionArray.get(currentQuestion).getQuestion());

        } else {
            //questionTxt.setText(String.format("Score:%s/%s", score, this.totalQuestions));
            finalScore = String.format("Score:%s/%s", score, this.totalQuestions);
            Intent intent = new Intent(this, GameEndActivity.class);
            startActivity(intent);
        }
    }

    public static String getFinalScore() {
        return finalScore;
    }
}