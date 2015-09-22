package com.mfarag.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.mfarag.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.mfarag.geoquiz.answer_shown";
    public static final String KEY_ANSWER_SHOWN = "answer shown";
    public static boolean sAnswerWasShown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        if (savedInstanceState != null) {
            sAnswerWasShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);
            setResult(Activity.RESULT_OK, new Intent().putExtra(EXTRA_ANSWER_SHOWN, sAnswerWasShown));
        }

        final boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        findViewById(R.id.button_answer_show_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sAnswerWasShown = true;
                ((TextView) findViewById(R.id.answer_text)).setText(String.valueOf(answerIsTrue));
                setResult(Activity.RESULT_OK, new Intent().putExtra(EXTRA_ANSWER_SHOWN, sAnswerWasShown));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN,sAnswerWasShown);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
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

    public static Intent newIntent(Context context, boolean answerIsTrue) {
        return new Intent(context, AnswerActivity.class).putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
    }

    public static boolean isAnswerDisplayed(Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
