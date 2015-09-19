package com.mfarag.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_toronto_population, true),
            new Question(R.string.question_toronto_capital, false),
            new Question(R.string.question_toronto_language, true)
    };
    private int mCurrentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mCurrentQuestion = 0;
        mQuestionTextView.setText(mQuestionBank[mCurrentQuestion].getTextResourceId());

        mTrueButton = (Button) findViewById(R.id.button_true);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestionBank[mCurrentQuestion].isAnswerTrue()){
                    Toast.makeText(getApplicationContext(), R.string.feedback_correct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, R.string.feedback_wrong, Toast.LENGTH_SHORT).show();
                }
            }
        });


        mFalseButton = (Button) findViewById(R.id.button_false);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestionBank[mCurrentQuestion].isAnswerTrue()){
                    Toast.makeText(QuizActivity.this, R.string.feedback_wrong, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.feedback_correct, Toast.LENGTH_SHORT).show();
                }            }
        });

        mNextButton = (Button) findViewById(R.id.button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentQuestion >= mQuestionBank.length -1){
                    Toast.makeText(QuizActivity.this, R.string.feedback_no_more_questions_message, Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentQuestion += 1;
                    mQuestionTextView.setText(mQuestionBank[mCurrentQuestion].getTextResourceId());
                }
            }
        });
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
}
