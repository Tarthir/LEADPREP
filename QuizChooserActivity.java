package ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView mQuizRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);
        setupQuizes();
        mQuizRecycler = (RecyclerView) findViewById(R.id.quiz_recycler);
        ArrayList<Quiz> quizzes =  new ArrayList<>();
        quizzes.addAll(Model.getInstance().getOurQuizzes().values());//converts Colelction to Arraylist
        QuizAdapter filterAdapter = new QuizAdapter(this,quizzes);

        mQuizRecycler.setLayoutManager(new LinearLayoutManager(this));
        mQuizRecycler.setAdapter(filterAdapter);

    }
    /**Setups our quizzes in the model*/
    private void setupQuizes() {
        //Add default quizzes to the model
        Resources r = getResources();
        Model.getInstance().addQuiz(new Quiz("Chain of Command quiz",r.getStringArray(R.array.chain_of_command_quiz),r.getStringArray(R.array.chain_of_command_answers)));
        Model.getInstance().addQuiz(new Quiz("Majcom quiz",r.getStringArray(R.array.majcom_quiz),r.getStringArray(R.array.majcom_answers)));
        Model.getInstance().addQuiz(new Quiz("Mission Statement quiz",r.getStringArray(R.array.mission),r.getStringArray(R.array.mission_answers)));
        Model.getInstance().addQuiz(new Quiz("Code of Conduct quiz",r.getStringArray(R.array.code),r.getStringArray(R.array.code_answers)));
        Model.getInstance().addQuiz(new Quiz("Quotes' quiz",r.getStringArray(R.array.Quotes),r.getStringArray(R.array.quotes_answer)));
        Model.getInstance().addQuiz(new Quiz("AF Song quiz",r.getStringArray(R.array.afSong),r.getStringArray(R.array.afSong_answers)));
        Model.getInstance().addQuiz(new Quiz("Airmen's Creed quiz",r.getStringArray(R.array.airmen_creed),r.getStringArray(R.array.creed_answers)));
    }
}
