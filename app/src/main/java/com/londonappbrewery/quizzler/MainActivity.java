package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private int mIndex;
    private Question mQuestion;
    private int score = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, true),
            new Question(R.string.question_6, false),
            new Question(R.string.question_7, true),
            new Question(R.string.question_8, false),
            new Question(R.string.question_9, true),
            new Question(R.string.question_10, true),
            new Question(R.string.question_11, false),
            new Question(R.string.question_12, false),
            new Question(R.string.question_13, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestion = mQuestionBank[mIndex];
        mQuestionTextView.setText(mQuestion.getQuestionId());

    }

    public void trueButtonPressed(View view) {
        checkAnswer(true);
        updateQuestion();
    }

    public void falseButtonPressed(View view) {
        checkAnswer(false);
        updateQuestion();
    }

    private void checkAnswer(boolean enteredAnswer) {
        if (enteredAnswer == mQuestion.isAnswer()) {
            Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(getApplicationContext(), "The answer was not correct!", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateQuestion() {
        mIndex = mIndex + 1;
        mQuestion = mQuestionBank[mIndex];
        mQuestionTextView.setText(mQuestion.getQuestionId());
    }
}
