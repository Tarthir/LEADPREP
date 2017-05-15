package ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tylerbrady34gmail.leadprepper.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Quiz;
import model.Utils;

/**
 * Created by tyler on 5/6/2017.
 */

public class QuizChooserActivity extends AppCompatActivity implements EditDialogListener {
    private Spinner mQuizSelector;
    private boolean isSpinnerTouched = false;
    private RecyclerView mQuizRecycler;
    final String TAG = "QUIZCHOOSE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);
        setupQuizes();
        mQuizRecycler = (RecyclerView) findViewById(R.id.quiz_recycler);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        quizzes.addAll(Model.getInstance().getOurQuizzes().values());//converts Colelction to Arraylist
        QuizAdapter filterAdapter = new QuizAdapter(this, quizzes);

        mQuizRecycler.setLayoutManager(new LinearLayoutManager(this));
        mQuizRecycler.setAdapter(filterAdapter);

    }

    /**
     * Setups our quizzes in the model
     */
    private void setupQuizes() {
        //Add default quizzes to the model
        Resources r = getResources();
        try {
            getQuizzes();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        }
        Model.getInstance().addQuiz(new Quiz("Chain of Command quiz", r.getStringArray(R.array.chain_of_command_quiz), r.getStringArray(R.array.chain_of_command_answers)));
        Model.getInstance().addQuiz(new Quiz("Majcom quiz", r.getStringArray(R.array.majcom_quiz), r.getStringArray(R.array.majcom_answers)));
        Model.getInstance().addQuiz(new Quiz("Mission Statement quiz", r.getStringArray(R.array.mission), r.getStringArray(R.array.mission_answers)));
        Model.getInstance().addQuiz(new Quiz("Code of Conduct quiz", r.getStringArray(R.array.code), r.getStringArray(R.array.code_answers)));
        Model.getInstance().addQuiz(new Quiz("Quotes' quiz", r.getStringArray(R.array.Quotes), r.getStringArray(R.array.quotes_answer)));
        Model.getInstance().addQuiz(new Quiz("AF Song quiz", r.getStringArray(R.array.afSong), r.getStringArray(R.array.afSong_answers)));
        Model.getInstance().addQuiz(new Quiz("Airmen's Creed quiz", r.getStringArray(R.array.airmen_creed), r.getStringArray(R.array.creed_answers)));
    }

    private void getQuizzes() throws IOException, ClassNotFoundException {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(Utils.PREFS_NAME, 0);
        Quiz.setCount(settings.getInt("numOfQuizzes",0));
        for(int i = 0; i <= Quiz.getCount(); i++) {
            String fileName = "my_data" + i;//grab all the files containing objects that have been saved
            FileInputStream fis = this.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            Quiz quiz = (Quiz) is.readObject();
            is.close();
            fis.close();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;
            case R.id.new_quiz_button:
                showEditDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean bool = super.onCreateOptionsMenu(menu);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getMenuInflater().inflate(R.menu.mymenu, menu);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // IconDrawable draw = new IconDrawable(this, Iconify.IconValue.fa_angle_double_up).colorRes(R.color.white).sizeDp(40);
        //menu.getItem(0).setIcon(draw);//sets filter item to have the right icon
        return bool;
    }

    /**
     * Shows the new quiz dialog
     */
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NewQuizFragment editDialogFragment = NewQuizFragment.newInstance();
        editDialogFragment.show(fm, "fragment_new_quiz");
    }

    @Override
    public void onFinishedDialogListener(Bundle bundle) {
        //if we are supposed to keep going or if the quiz has been submitted
        if ((boolean) bundle.get("keepgoing")) {
            Quiz newQuiz = new Quiz((String) bundle.get("name"), (String) bundle.get("descriptor"));
            Model.getInstance().setNewQuiz(newQuiz);
            //showEditSlideDialog();
            //Go to the quiz adder activity which will create quizzes
            startActivity(new Intent(this, QuizAdder.class));
        }
    }
}
