package com.s341872;

public class Question {

    private final String question;
    private final String answer;

    public Question(String[] array) {
        this.question = array[0] + " + " + array[1] + " =";
        this.answer = array[2];
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
