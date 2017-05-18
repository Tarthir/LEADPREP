package ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tylerbrady34gmail.leadprepper.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Set;

import model.Model;
import model.Quiz;
import model.Utils;

/**
 * Created by tyler on 5/15/2017.
 * Handles editing of quizzes and quiz creation
 */


public class QuizAdder extends AppCompatActivity implements EditDialogListener {
    /**
     * The textfield where the user enters a question
     */
    private EditText mQuestion;
    /**
     * The textfield where the user enters an answer to a question
     */
    private EditText mAnswer;
    /**
     * The button to push to adda question and answer to the textfield
     */
    private Button mAddButton;
    /**
     * The button to push to submit a quiz nad end the activity
     */
    private Button mSubmitQuizButton;
    /**
     * The the array of answers entered
     */
    private ArrayList<String> answers;
    /**
     * The array of questions entered
     */
    private ArrayList<String> questions;
    /**
     * My preference file with quiz data saved
     */
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizadder);
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        mQuestion = (EditText) findViewById(R.id.enter_question_edit);
        mAnswer = (EditText) findViewById(R.id.enter_answer_edit);
        mAddButton = (Button) findViewById(R.id.add_button_slide);
        mSubmitQuizButton = (Button) findViewById(R.id.submit_quiz_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if they have entered in something
                if (mQuestion.getText().length() > 0 && mAnswer.getText().length() > 0) {
                    answers.add(mAnswer.getText().toString());
                    questions.add(mQuestion.getText().toString());
                    //reset text
                    mAnswer.setText("");
                    mQuestion.setText("");
                }
            }
        });
        mSubmitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if they have entered in something
                if (mQuestion.getText().length() > 0 && mAnswer.getText().length() > 0) {
                    answers.add(mAnswer.getText().toString());
                    questions.add(mQuestion.getText().toString());
                }
                showEditDialog();

            }
        });
    }

    /**
     * Saves our quiz object to internal storage
     */
    private void saveQuiz(Quiz newQuiz) {
        try {
            Utils.saveQuiz(this,Quiz.getCount(),newQuiz);
        } catch (IOException i) {
            i.printStackTrace();
            Toast.makeText(this, "Error saving Quiz, please try again", Toast.LENGTH_SHORT).show();
            ;
        }
    }

    @Override
    public void onFinishedDialogListener(Bundle bundle) {
        if (bundle.getBoolean("isYes")) {
            String descrip = Model.getInstance().getNewQuiz().getDescription();
            String name = Model.getInstance().getNewQuiz().getName();
            Quiz newQuiz = new Quiz(name, descrip, questions, answers);
            Model.getInstance().getOurQuizzes().put(name, newQuiz);
            saveQuiz(newQuiz);

            Intent intent = new Intent(getApplicationContext(), QuizChooserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Shows the new quiz dialog
     */
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        CheckSubmitFrag editDialogFragment = CheckSubmitFrag.newInstance();
        editDialogFragment.show(fm, "fragment_checksubmit");
    }

}
