package com.mfarag.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
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

        mCurrentQuestion = 0;

        final TextView mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        View.OnClickListener onClickListener = createOnClickListener(mQuestionTextView);
        updateQuestionText(mQuestionTextView);
        mQuestionTextView.setOnClickListener(onClickListener);

        findViewById(R.id.button_true).setOnClickListener(onClickListener);
        findViewById(R.id.button_false).setOnClickListener(onClickListener);
        findViewById(R.id.button_next).setOnClickListener(onClickListener);
        findViewById(R.id.button_previous).setOnClickListener(onClickListener);

    }

    private void updateQuestionText(TextView textView) {
        textView.setText(mQuestionBank[mCurrentQuestion].getTextResourceId());
    }

    private void displayFeedbackInResponseToUserAnswer(boolean userAnsweredTrue) {
        if ((userAnsweredTrue && mQuestionBank[mCurrentQuestion].isAnswerTrue()) || (!userAnsweredTrue && !mQuestionBank[mCurrentQuestion].isAnswerTrue())) {
            displayMessage(R.string.feedback_correct);
        } else {
            displayMessage(R.string.feedback_wrong);
        }
    }

    private void displayMessage(int displayTextResourceId) {
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
                switch (v.getId()) {
                    case R.id.button_previous:
                        if (mCurrentQuestion <= 0) {
                            displayMessage(R.string.feedback_no_more_questions_message);
                        } else {
                            mCurrentQuestion -= 1;
                            updateQuestionText(textView);
                        }
                        break;
                    case R.id.question_text_view:
                        // Intentionally falling through
                    case R.id.button_next:
                        if (mCurrentQuestion >= mQuestionBank.length - 1) {
                            displayMessage(R.string.feedback_no_more_questions_message);
                        } else {
                            mCurrentQuestion += 1;
                            updateQuestionText(textView);
                        }
                        break;
                    case R.id.button_true:
                        displayFeedbackInResponseToUserAnswer(true);
                        break;
                    case R.id.button_false:
                        displayFeedbackInResponseToUserAnswer(false);
                        break;
                    default:
                        // nothing to be done...
                        break;
                }

            }
        };
    }

}
