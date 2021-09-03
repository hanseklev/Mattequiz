package com.s341872;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    private static final int NUMBER_OF_QUESTIONS = 5;
    private List<Question> questionArray;
    private int currentQuestion = 0;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView questionText = findViewById(R.id.txt_question);
        final TextView answerText = findViewById(R.id.txt_answer);

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


        int totalQuestions = PreferencesActivity.getTotalQuestions();
        int[] includedQuestions = new int[totalQuestions];
        int iqCounter = 0;
        this.questionArray = new Question[totalQuestions];
        for (int i = 0;i<this.questionArray.length;i++){

            boolean valid = true;
            do {
                int randomNumber = ThreadLocalRandom.current().nextInt(1, 15);
                valid = true;

                for (int j = 0; j < includedQuestions.length; j++) {
                    if (randomNumber == includedQuestions[j]) {
                        valid = false;
                    }
                }
                if (valid) {
                    this.questionArray[i] = getRandomQuestion(randomNumber);
                    includedQuestions[iqCounter] = randomNumber;
                    iqCounter++;
                }
            }
            while (!valid);
        }
        //Initializes question-array and writes the first question to the screen
        questionArray = initializeQuestions();
        Log.d("ARRAY", questionArray.toString());

        questionText.setText(questionArray.get(currentQuestion).getQuestion());


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
                nextQuestion(questionText);
            } else {
                Log.d("Answer", "Answer not submitted");
            }
        });
    }

    private String updateAnswerText(TextView answer, String newInput) {
        String oldText = String.valueOf(answer.getText());
        return oldText + newInput;
    }



    private String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }


    private void nextQuestion(TextView questionTxt) {
        if (currentQuestion < questionArray.size() - 1) {
            currentQuestion++;
            questionTxt.setText(questionArray.get(currentQuestion).getQuestion());
        } else {
            questionTxt.setText(String.format("Score:%s/%s", score, NUMBER_OF_QUESTIONS));
        }
    }


    private Question getRandomQuestion(int randomNumber){
        Question q;
        switch (randomNumber){
            case 14:
                q = new Question(getStringArray(R.array.question15));
                break;
            case 13:
                q = new Question(getStringArray(R.array.question14));
                break;
            case 12:
                q = new Question(getStringArray(R.array.question13));
                break;
            case 11:
                q = new Question(getStringArray(R.array.question12));
                break;
            case 10:
                q = new Question(getStringArray(R.array.question11));
                break;
            case 9:
                q = new Question(getStringArray(R.array.question10));
                break;
            case 8:
                q = new Question(getStringArray(R.array.question9));
                break;
            case 7:
                q = new Question(getStringArray(R.array.question8));
                break;
            case 6:
                q = new Question(getStringArray(R.array.question7));
                break;
            case 5:
                q = new Question(getStringArray(R.array.question6));
                break;
            case 4:
                q = new Question(getStringArray(R.array.question5));
                break;
            case 3:
                q = new Question(getStringArray(R.array.question4));
                break;
            case 2:
                q = new Question(getStringArray(R.array.question3));
                break;
            case 1:
                q = new Question(getStringArray(R.array.question2));
                break;
            default:
                q = new Question(getStringArray(R.array.question1));
        }
        return q;
    }
}