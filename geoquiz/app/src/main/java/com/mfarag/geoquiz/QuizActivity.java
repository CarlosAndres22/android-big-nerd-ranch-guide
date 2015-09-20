package com.mfarag.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_QUESTION_INDEX = "questionIndex";
    final private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_toronto_population, true),
            new Question(R.string.question_toronto_capital, false),
            new Question(R.string.question_toronto_language, true)
    };
    private int mCurrentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentQuestion = savedInstanceState.getInt(KEY_QUESTION_INDEX,0);
        } else {
        mCurrentQuestion = 0;

        }


        final TextView mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        View.OnClickListener onClickListener = createOnClickListener(mQuestionTextView);
        updateQuestionText(mQuestionTextView);
        mQuestionTextView.setOnClickListener(onClickListener);

        findViewById(R.id.button_true).setOnClickListener(onClickListener);
        findViewById(R.id.button_false).setOnClickListener(onClickListener);
        findViewById(R.id.button_next).setOnClickListener(onClickListener);
        findViewById(R.id.button_previous).setOnClickListener(onClickListener);
        findViewById(R.id.button_show_answer).setOnClickListener(onClickListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUESTION_INDEX, mCurrentQuestion);
    }

    private void updateQuestionText(TextView textView) {
        int textResourceId = mQuestionBank[mCurrentQuestion].getTextResourceId();
        Log.d(TAG, "updateQuestionText " + getString(textResourceId));
        textView.setText(textResourceId);
    }

    private void displayFeedbackInResponseToUserAnswer(boolean userAnsweredTrue) {
        if ((userAnsweredTrue && mQuestionBank[mCurrentQuestion].isAnswerTrue()) || (!userAnsweredTrue && !mQuestionBank[mCurrentQuestion].isAnswerTrue())) {
            displayMessage(R.string.feedback_correct);
        } else {
            displayMessage(R.string.feedback_wrong);
        }
    }

    private void displayMessage(int displayTextResourceId) {
        Log.d(TAG, "displayMessage " + getString(displayTextResourceId));
        Toast.makeText(getApplicationContext(), displayTextResourceId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener createOnClickListener(final TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Something was clicked");
                switch (v.getId()) {
                    case R.id.button_previous:
                        Log.d(TAG, "previous button clicked");
                        if (mCurrentQuestion <= 0) {
                            Log.d(TAG, "First question can not fine any previous questions");
                            displayMessage(R.string.feedback_no_more_questions_message);
                        } else {
                            mCurrentQuestion -= 1;
                            Log.d(TAG, "Moving to previous question, new question index: " + mCurrentQuestion);
                            updateQuestionText(textView);
                        }
                        break;
                    case R.id.question_text_view:
                        Log.d(TAG, "Question text clicked");
                        // Intentionally falling through
                    case R.id.button_next:
                        Log.d(TAG, "Next button clicked");
                        if (mCurrentQuestion >= mQuestionBank.length - 1) {
                            Log.d(TAG, "Last question can not fine any next questions");
                            displayMessage(R.string.feedback_no_more_questions_message);
                        } else {
                            mCurrentQuestion += 1;
                            Log.d(TAG, "Moving to next question, new question index: " + mCurrentQuestion);
                            updateQuestionText(textView);
                        }
                        break;
                    case R.id.button_true:
                        Log.d(TAG, "User decided the answer is correct");
                        displayFeedbackInResponseToUserAnswer(true);
                        break;
                    case R.id.button_false:
                        Log.d(TAG, "User decided the answer is wrong");
                        displayFeedbackInResponseToUserAnswer(false);
                        break;
                    case R.id.button_show_answer:
                        Log.d(TAG, "User wants to see the answer for this question");
                        startActivity(AnswerActivity.newIntent(QuizActivity.this,mQuestionBank[mCurrentQuestion].isAnswerTrue()));
                        break;
                    default:
                        // nothing to be done...
                        break;
                }

            }
        };
    }

}
