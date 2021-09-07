package com.s341872;

import java.io.Serializable;

public class Question implements Serializable {

    private final String question;
    private final String answer;

    /**
     * @param strArray holds an array with length equal to 3.
     *                 The two first indexes are the addends and are mapped to the question string
     *                 The last index holds the answer
     *
     */

    public Question(String[] strArray) {
        if (strArray.length != 3)
            throw new IndexOutOfBoundsException("Array length does not match required size (3)");
        this.question = strArray[0] + " + " + strArray[1] + " = ";
        this.answer = strArray[2];
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
