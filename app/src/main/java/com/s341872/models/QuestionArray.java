package com.s341872.models;

import android.content.res.Resources;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;

import com.s341872.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionArray implements Serializable {
    private ArrayList<Question> questionList;

    public QuestionArray() {
        questionList = new ArrayList<>();
    }

    //Returns an array with randomized questions and desired length
    public void seedArray(Resources res, int size) {
        TypedArray typedArrQuestions = res.obtainTypedArray(R.array.questions_container);

        for (int i = 0; i < typedArrQuestions.length(); i++) {
            int id = typedArrQuestions.getResourceId(i, 0);
            questionList.add(new Question(res.getStringArray(id)));
        }
        typedArrQuestions.recycle();

        Collections.shuffle(questionList);

        questionList = new ArrayList<>(questionList.subList(0, size));
    }

    public void addAll(QuestionArray arr) {
        questionList.addAll(arr.questionList);
    }

    public String getQuestion(int i) {
        return questionList.get(i).getQuestion();
    }

    public String getAnswer(int i) {
        return questionList.get(i).getAnswer();
    }

    public int size() {
        return questionList.size();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Question q : questionList) {
            sb.append("Question: ").append(q.getQuestion()).append(q.getAnswer()).append("\n");
        }
        return sb.toString();
    }
}
