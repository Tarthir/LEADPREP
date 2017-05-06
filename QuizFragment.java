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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quizview, container, false);
        mQuizText = (TextView) v.findViewById(R.id.quiz_textview);
        mQuizText.setText(getArguments().getString("msg"));//set quiz text
        return  v;
    }
    public static QuizFragment newInstance() {

        QuizFragment f = new QuizFragment();
        Bundle b = new Bundle();
        b.putString("msg",Model.getInstance().getCurrQuizObj().getCurrPositionText());
        f.setArguments(b);

        return f;
    }
}
