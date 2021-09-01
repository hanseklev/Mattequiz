package com.s341872;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

//TODO: maybe remove 'givenAnswer' variable and modify 'answerText' directly
public class GameActivity extends AppCompatActivity {

    private String givenAnswer = "";
    private Question[] questionArray;
    private int questionCount = 0;
    //private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView questionText = findViewById(R.id.txt_question);
        final TextView answerText = findViewById(R.id.txt_answer);

        final Button numPadBtnOne = findViewById(R.id.button_game_one);
        final Button numPadBtnTwo = findViewById(R.id.button_game_two);
        final Button numPadBtnThree = findViewById(R.id.button_game_three);
        final Button numPadBtnFour = findViewById(R.id.button_game_four);
        final Button numPadBtnFive = findViewById(R.id.button_game_five);
        final Button numPadBtnSix = findViewById(R.id.button_game_six);
        final Button numPadBtnSeven = findViewById(R.id.button_game_seven);
        final Button numPadBtnEight = findViewById(R.id.button_game_eight);
        final Button numPadBtnNine = findViewById(R.id.button_game_nine);
        final Button numPadBtnZero = findViewById(R.id.button_game_zero);

        //TODO:
        final Button numPadBtnDelete = findViewById(R.id.button_game_delete);


        final Button submitAnswerBtn = findViewById(R.id.button_submit_answer);

        this.questionArray = new Question[]{
                new Question(getStringArray(R.array.question1)),
                new Question(getStringArray(R.array.question2)),
                new Question(getStringArray(R.array.question3)),
                new Question(getStringArray(R.array.question4)),
                new Question(getStringArray(R.array.question5)),
        };

        questionText.setText(questionArray[questionCount].getQuestion());


        numPadBtnZero.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "0");
            answerText.setText(getGivenAnswer());
        });
        numPadBtnOne.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "1");
            answerText.setText(getGivenAnswer());
        });
        numPadBtnTwo.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "2");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnThree.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "3");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnFour.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "4");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnFive.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "5");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnSix.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "6");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnSeven.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "7");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnEight.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "8");
            answerText.setText(getGivenAnswer());

        });
        numPadBtnNine.setOnClickListener(view -> {
            setGivenAnswer(getGivenAnswer() + "9");
            answerText.setText(getGivenAnswer());
        });

       /* numPadBtnDelete.setOnClickListener(view -> {
            setGivenAnswer();
        });*/


        submitAnswerBtn.setOnClickListener(view -> {
            if (getGivenAnswer().length() > 0) {
                Log.d("ANSWER", getGivenAnswer());
                if (givenAnswer.equals(questionArray[questionCount].getAnswer())) {
                    //score++;
                    System.out.println("Riktig svar!!");
                } else {
                    System.out.println("Feil svar:(Riktig svar er" + questionArray[questionCount].getAnswer());
                }
                setGivenAnswer("");
                answerText.setText("");
                nextQuestion(questionText);
            } else {
                Log.d("Answer", "Answer not submitted");
            }
        });
    }

    private String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }


    private void nextQuestion(TextView questionTxt) {
        if (questionCount < questionArray.length - 1) {
            this.questionCount++;
            questionTxt.setText(questionArray[questionCount].getQuestion());
        } else {
            System.out.println("QUIZ ER FERDIG");
        }
    }

    void setGivenAnswer(String number) {
        this.givenAnswer = number;
    }

    String getGivenAnswer() {
        return givenAnswer;
    }
}