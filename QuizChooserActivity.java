package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tylerbrady34gmail.leadprepper.R;

import model.Quiz;

/**
 * Created by tyler on 5/4/2017.
 * Our activity where the user chooses what quiz to do
 */

public class QuizChooserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);
        initialize();
    }

    private void initialize() {
        final String quiz = "quiz_type";
        Button chainOfCommand = (Button) findViewById(R.id.chain_button);
        Button majCom = (Button) findViewById(R.id.majcom_button);
        Button mission = (Button) findViewById(R.id.mission_button);
        Button code = (Button) findViewById(R.id.code_button);
        Button quotes = (Button) findViewById(R.id.quotes_button);
        Button song = (Button) findViewById(R.id.song_button);
        Button creed = (Button) findViewById(R.id.creed_button);
        Button myQuiz = (Button) findViewById(R.id.quiz_button);
        chainOfCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.CHAINOFCOMMAND));
            }
        });
        majCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.MAJCOM));
            }
        });

        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.MISSION));
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.CODE));
            }
        });
        quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.QUOTES));
            }
        });
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.SONG));
            }
        });
        creed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.CREED));
            }
        });
        myQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizChooserActivity.this,QuizActivity.class).putExtra(quiz,Quiz.MyQuiz));
            }
        });
    }
}
