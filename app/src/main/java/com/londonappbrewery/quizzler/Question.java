package com.londonappbrewery.quizzler;

public class Question {
    private int mQuestionId;
    private boolean mAnswer;

    public Question(int questionResourceId, boolean trueOrFalse) {
        mQuestionId = questionResourceId;
        mAnswer = trueOrFalse;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
