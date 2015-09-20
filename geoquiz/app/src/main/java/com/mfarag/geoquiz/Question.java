package com.mfarag.geoquiz;


public class Question {
    private final int mTextResourceId;
    private final boolean mAnswerTrue;

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
