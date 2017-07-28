package ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.tylerbrady34gmail.leadprepper.BuildConfig;
import com.tylerbrady34gmail.leadprepper.R;

import java.io.IOException;

import model.Model;
import model.Quiz;
import model.Utils;

/**
 * Created by tyler on 5/4/2017.
 * Our main activity which is our title screen
 */

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    boolean isFirstTimeAppRun = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button leadButton = (Button) findViewById(R.id.lead_button);
        Button quizButton = (Button) findViewById(R.id.quiz_button);
        checkFirstRun();

        leadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Lead Manual button clicked");
                startActivity(new Intent(MainActivity.this,MyPdfViewActivity.class));
            }
        });

        quizButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Quiz button clicked");
                Intent intent = new Intent(MainActivity.this, QuizChooserActivity.class);
                startActivity(new Intent(intent));
            }
        });
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            Log.d(TAG,"Normal run, no upgrade/first time ran");
            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            Log.d(TAG,"New install/First time");
            setupIntialQuizzes();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY,1).apply();
            // TODO This is a new install (or the user cleared the shared preferences)
            return;

        } else if (currentVersionCode > savedVersionCode) {
            Log.d(TAG,"Upgraded verson");
            // TODO This is an upgrade
            return;
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

    /**Puts the initial quizzes into the model and saving them*/
    private void setupIntialQuizzes() {
        //Add default quizzes to the model
        Resources r = getResources();
        Model.getInstance().addQuiz(this,new Quiz("Chain of Command quiz", r.getStringArray(R.array.chain_of_command_quiz), r.getStringArray(R.array.chain_of_command_answers)));
        Model.getInstance().addQuiz(this,new Quiz("Majcom quiz", r.getStringArray(R.array.majcom_quiz), r.getStringArray(R.array.majcom_answers)));
        Model.getInstance().addQuiz(this,new Quiz("Mission Statement quiz", r.getStringArray(R.array.mission), r.getStringArray(R.array.mission_answers)));
        Model.getInstance().addQuiz(this,new Quiz("Code of Conduct quiz", r.getStringArray(R.array.code), r.getStringArray(R.array.code_answers)));
        Model.getInstance().addQuiz(this,new Quiz("Quotes' quiz", r.getStringArray(R.array.Quotes), r.getStringArray(R.array.quotes_answer)));
        Model.getInstance().addQuiz(this,new Quiz("AF Song quiz", r.getStringArray(R.array.afSong), r.getStringArray(R.array.afSong_answers)));
        Model.getInstance().addQuiz(this,new Quiz("Airmen's Creed quiz", r.getStringArray(R.array.airmen_creed), r.getStringArray(R.array.creed_answers)));
    }

}
