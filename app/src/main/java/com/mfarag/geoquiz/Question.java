package com.mfarag.geoquiz;


public class Question {
    private int mTextResourceId;
    private boolean mAnswerTrue;

    public Question(int textResourceId, boolean answerTrue) {
        mTextResourceId = textResourceId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResourceId() {
        return mTextResourceId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
