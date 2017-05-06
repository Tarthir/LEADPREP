package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tylerbrady34gmail.leadprepper.R;

import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Quiz;

/**
 * Created by tyler on 5/6/2017.
 */

public class QuizChooserActivity extends AppCompatActivity {
    private Spinner mQuizSelector;
    private boolean isSpinnerTouched = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);
        setupSpinner();

    }

    private void setupSpinner() {
        //Add default quizzes to the model
        Model.getInstance().addQuiz(new Quiz("Chain of Command quiz",getResources().getStringArray(R.array.chain_of_command_quiz)));
        Model.getInstance().addQuiz(new Quiz("Majcom quiz",getResources().getStringArray(R.array.majcom_quiz)));
        Model.getInstance().addQuiz(new Quiz("Mission Statement quiz",getResources().getStringArray(R.array.mission)));
        Model.getInstance().addQuiz(new Quiz("Code of Conduct quiz",getResources().getStringArray(R.array.code)));
        Model.getInstance().addQuiz(new Quiz("Quotes quiz",getResources().getStringArray(R.array.Quotes)));
        Model.getInstance().addQuiz(new Quiz("AF Song quiz",getResources().getStringArray(R.array.afSong)));
        Model.getInstance().addQuiz(new Quiz("Airmen's Creed quiz",getResources().getStringArray(R.array.airmen_creed)));

        mQuizSelector = (Spinner) findViewById(R.id.quiz_spinner);
        List<String> list = new ArrayList<>();
        if(Model.getInstance().getCurrQuizObj() != null) {//if there is one already selected
            list.add(Model.getInstance().getCurrQuizObj().getName());//the current quiz is the first in the spinner
        }
        else{
            mQuizSelector.setPrompt("Pick Quiz Here");
        }

        for(String name : Model.getInstance().getOurQuizzes().keySet()){
            if((Model.getInstance().getCurrQuizObj() != null && !Model.getInstance().getCurrQuizObj().getName().equals(name))
                    || Model.getInstance().getCurrQuizObj() == null){//if not the same quiz

                list.add(name);
            }
        }
        //setup adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuizSelector.setAdapter(dataAdapter);

        mQuizSelector.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        mQuizSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("QuizChooserActivity","Item Selected in spinner");
                if (!isSpinnerTouched) return;

                String nameAtPos = (String)parent.getItemAtPosition(position);//the key which is in the spinner
                Model.getInstance().setCurrQuiz(Model.getInstance().getOurQuizzes().get(nameAtPos));//give the model the selected quiz
                Intent intent = new Intent(QuizChooserActivity.this,QuizActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
