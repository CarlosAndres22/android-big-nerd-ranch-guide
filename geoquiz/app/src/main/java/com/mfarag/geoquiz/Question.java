package com.mfarag.geoquiz;


import java.io.Serializable;

public class Question implements Serializable {
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

    public enum AnswerStatus {
        NOT_ANSWERED(0, "not answered"),
        CORRECT(1, "answered correctly"),
        WRONG(2, "answred wrong"),
        VIEWED_ANSWER(3, "answer viewed");

        private int val;
        private String message;

        AnswerStatus(int val, String message) {
            this.val = val;
            this.message = message;
        }

        public int getVal() {
            return val;
        }

        public static AnswerStatus getStatus(int val) {
            switch (val) {
                case 0:
                    return NOT_ANSWERED;
                case 1:
                    return CORRECT;
                case 2:
                    return WRONG;
                case 3:
                    return VIEWED_ANSWER;
                default:
                    return NOT_ANSWERED;
            }
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
