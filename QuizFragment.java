package ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.tylerbrady34gmail.leadprepper.R;

import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Quiz;

/**
 * Created by tyler on 5/4/2017.
 * Our quiz fragment
 */

public class QuizFragment extends Fragment {
    private TextView mQuizText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Add default quizzes to the model
        Model.getInstance().addQuiz(new Quiz("Chain of Command quiz",getResources().getStringArray(R.array.chain_of_command_quiz)));
        Model.getInstance().addQuiz(new Quiz("Majcom quiz",getResources().getStringArray(R.array.majcom_quiz)));
        Model.getInstance().addQuiz(new Quiz("Mission Statement quiz",getResources().getStringArray(R.array.mission)));
        Model.getInstance().addQuiz(new Quiz("Code of Conduct quiz",getResources().getStringArray(R.array.code)));
        Model.getInstance().addQuiz(new Quiz("Quotes quiz",getResources().getStringArray(R.array.Quotes)));
        Model.getInstance().addQuiz(new Quiz("AF Song quiz",getResources().getStringArray(R.array.afSong)));
        Model.getInstance().addQuiz(new Quiz("Airmen's Creed quiz",getResources().getStringArray(R.array.airmen_creed)));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mQuizText = (TextView) view.findViewById(R.id.quiz_textview);
        mQuizText.setText(Model.getInstance().getCurrQuiz().getCurrPositionText());//set quiz text
        return  view;
    }
}
