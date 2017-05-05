package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tylerbrady34gmail.leadprepper.R;

import java.util.ArrayList;
import java.util.List;

import model.Model;

/**
 * Created by tyler on 5/4/2017.
 * Our activity where the user chooses what quiz to do
 */

public class QuizChooserActivity extends AppCompatActivity {
    private Spinner mQuizSelector;
    private Fragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_quiz);
        // “Create a new fragment transaction, include one add operation in it, and then commit it.”
        if(fragment == null){
            fragment = new QuizFragment();
            fm.beginTransaction().add(R.id.fragment_spot,fragment).commit();
        }
        setupSpinner();

    }

    private void setupSpinner() {
        mQuizSelector = (Spinner) findViewById(R.id.quiz_selector);
        List<String> list = new ArrayList<>();
        list.add(Model.getInstance().getCurrQuiz().getName());//the current quiz is the first in the spinner

        for(String name : Model.getInstance().getOurQuizzes().keySet()){
            if(!Model.getInstance().getCurrQuiz().getName().equals(name)){//if not the same quiz
                list.add(name);
            }
        }
        //setup adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuizSelector.setAdapter(dataAdapter);
        mQuizSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameAtPos = (String)parent.getItemAtPosition(position);//the key which is in the spinner
                Model.getInstance().setCurrQuiz(Model.getInstance().getOurQuizzes().get(nameAtPos));//give the model the selected quiz
            }
        });
    }


}
