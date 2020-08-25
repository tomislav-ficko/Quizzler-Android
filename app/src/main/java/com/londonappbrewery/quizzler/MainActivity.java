package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

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

    final int NUMBER_OF_QUESTIONS = mQuestionBank.length;
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / NUMBER_OF_QUESTIONS);

    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private ProgressBar mProgressBar;
    private Question mQuestion;
    private int mIndex;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState.isEmpty()) {
            mIndex = 0;
            mScore = 0;
        } else {
            mIndex = savedInstanceState.getInt("Index");
            mScore = savedInstanceState.getInt("Score");
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex];
        mQuestionTextView.setText(mQuestion.getQuestionId());

    }

    public void trueButtonPressed(View view) {
        checkAnswer(true);
        updateProgress();
        updateQuestion();
    }

    public void falseButtonPressed(View view) {
        checkAnswer(false);
        updateProgress();
        updateQuestion();
    }

    private void checkAnswer(boolean enteredAnswer) {
        if (enteredAnswer == mQuestion.isAnswer()) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }


    private void updateQuestion() {
        mIndex++;
        // We could also put mIndex = (mIndex + 1) % NUMBER_OF_QUESTIONS, instead of the if statement, in order to loop the questions
        if (mIndex <= NUMBER_OF_QUESTIONS - 1) {
            mQuestion = mQuestionBank[mIndex];
            mQuestionTextView.setText(mQuestion.getQuestionId());
        } else {
            showDialogAndExit();
        }
    }

    private void updateProgress() {
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score " + mScore + "/" + NUMBER_OF_QUESTIONS);
    }

    private void showDialogAndExit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("End of game");
        alert.setMessage("You have scored " + mScore + " points!");
        alert.setCancelable(false);
        alert.setPositiveButton("Close application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alert.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Index", mIndex);
        outState.putInt("Score", mScore);
    }
}
